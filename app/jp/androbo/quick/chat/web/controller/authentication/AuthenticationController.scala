package jp.androbo.quick.chat.web.controller.authentication

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.application.{Authentication, SessionIdGenerator, SessionManager}
import jp.androbo.quick.chat.domain.Password
import jp.androbo.quick.chat.domain.email.EmailAddress
import jp.androbo.quick.chat.domain.error.ErrorEvent
import jp.androbo.quick.chat.domain.room.{RoomFactory, RoomRepository}
import jp.androbo.quick.chat.domain.user.{UserFactory, UserId, UserRepository}
import jp.androbo.quick.chat.web.controller.ApiActions
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AuthenticationController @Inject()(
                                  cc: ControllerComponents,
                                  actions: ApiActions,
                                  userFactory: UserFactory,
                                  userRepository: UserRepository,
                                  roomFactory: RoomFactory,
                                  roomRepository: RoomRepository,
                                  authentication: Authentication,
                                  sessionIdGenerator: SessionIdGenerator,
                                  sessionManager: SessionManager,
                                  implicit val ec: ExecutionContext,
                                ) extends AbstractController(cc) {

  def signUp(): Action[SignUpRequest] = actions.noCache.async(parse.json[SignUpRequest]) { request =>
    Future {
      userFactory.create(request.body.email, request.body.name, request.body.password).flatMap { user =>
        authentication.signUp(user)
      }.fold(e => BadRequest(actions.error(e)), _ => Ok)
    }
  }

  def login(): Action[LoginRequest] = actions.noCache.async(parse.json[LoginRequest]) { request =>
    Future {
      val userId = UserId(EmailAddress(request.body.email))
      val password = Password(request.body.password)
      userRepository.findById(userId).map{ user =>
        if (user.password == password) {
          val sessionId = sessionIdGenerator.generate()
          sessionManager.add(sessionId, user)
          Ok(Json.toJson(LoginResponse(sessionId)))
        } else {
          BadRequest(actions.error(ErrorEvent.LoginError))
        }
      }.getOrElse(BadRequest(actions.error(ErrorEvent.LoginError)))
    }
  }

  def logout(): Action[AnyContent] = actions.authenticate.async { request =>
    Future {
      sessionManager.delete(request.user.sessionId)
      Ok
    }
  }
}
