package jp.androbo.quick.chat.web.controller.sign_up

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.domain.model.user.{UserFactory, UserRepository}
import jp.androbo.quick.chat.web.controller.ApiActions
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class SignUpController @Inject()(
                                  cc: ControllerComponents,
                                  actions: ApiActions,
                                  userFactory: UserFactory,
                                  userRepository: UserRepository,
                                  implicit val ec: ExecutionContext,
                                ) extends AbstractController(cc) {

  def signUp(): Action[SignUpRequest] = actions.noCache.async(parse.json[SignUpRequest]) { request =>
    Future {
      userFactory.create(request.body.email, request.body.name, request.body.password).flatMap { user =>
        userRepository.add(user)
      }.fold(e => BadRequest(actions.error(e)), _ => Ok)
    }
  }
}
