package jp.androbo.quick.chat.web.controller.login

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.application.{SessionIdGenerator, SessionManager}
import jp.androbo.quick.chat.domain.Password
import jp.androbo.quick.chat.domain.model.email.EmailAddress
import jp.androbo.quick.chat.domain.model.event.error.ErrorEvent
import jp.androbo.quick.chat.domain.model.user.{UserId, UserRepository}
import jp.androbo.quick.chat.web.controller.ApiActions
import play.api.libs.json.Json
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class LoginController @Inject()(
                                  cc: ControllerComponents,
                                  actions: ApiActions,
                                  userRepository: UserRepository,
                                  sessionManager: SessionManager,
                                  sessionIdGenerator: SessionIdGenerator,
                                  implicit val ec: ExecutionContext,
                                ) extends AbstractController(cc) {
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
}