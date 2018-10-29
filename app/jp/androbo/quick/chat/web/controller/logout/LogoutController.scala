package jp.androbo.quick.chat.web.controller.logout

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.application.SessionManager
import jp.androbo.quick.chat.domain.model.user.UserRepository
import jp.androbo.quick.chat.web.controller.ApiActions
import play.api.mvc._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class LogoutController @Inject()(
                                  cc: ControllerComponents,
                                  actions: ApiActions,
                                  userRepository: UserRepository,
                                  sessionManager: SessionManager,
                                  implicit val ec: ExecutionContext,
                                ) extends AbstractController(cc) {
  def logout(): Action[AnyContent] = actions.authenticate.async { request =>
    Future {
      sessionManager.delete(request.user.sessionId)
      Ok
    }
  }
}