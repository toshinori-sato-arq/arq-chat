package jp.androbo.quick.chat.web.controller.debug

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.application.SessionManager
import jp.androbo.quick.chat.domain.model.room.RoomRepository
import jp.androbo.quick.chat.domain.model.user.UserRepository
import jp.androbo.quick.chat.web.controller.ApiActions
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class DebugController @Inject()(
                                 cc: ControllerComponents,
                                 actions: ApiActions,
                                 userRepository: UserRepository,
                                 roomRepository: RoomRepository,
                                 sessionManager: SessionManager,
                                 implicit val ec: ExecutionContext,
                               ) extends AbstractController(cc) {

  def users(): Action[AnyContent] = actions.noCache.async { _ =>
    Future {
      Ok(Json.toJson(userRepository.list))
    }
  }

  def authenticatedUsers(): Action[AnyContent] = actions.noCache.async { _ =>
    Future {
      Ok(Json.toJson(sessionManager.list))
    }
  }

  def rooms(): Action[AnyContent] = actions.noCache.async { _ =>
    Future {
      val r = userRepository.list.map { u =>
        u.id -> roomRepository.list(u.id)
      }
      Ok(Json.toJson(r))
    }
  }
}
