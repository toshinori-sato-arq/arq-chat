package jp.androbo.quick.chat.web.controller.room

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.domain.model.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.model.room.RoomRepository
import jp.androbo.quick.chat.domain.model.room.operation.RoomOperations
import jp.androbo.quick.chat.web.controller.ApiActions
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}
import play.api.mvc._

@Singleton
class RoomController @Inject()(
                                  cc: ControllerComponents,
                                  actions: ApiActions,
                                  roomRepository: RoomRepository,
                                  roomOperations: RoomOperations,
                                  implicit val ec: ExecutionContext,
                                ) extends AbstractController(cc) {
  def rooms(): Action[AnyContent] = actions.authenticate.async { request =>
    Future {
      val rooms = roomRepository.list(request.user.values.id).map { r =>
        RoomResponse(
          r.id,
          r.name,
          r.description,
          r.users.get(request.user.values.id).map(_.privileges).getOrElse(Set.empty[RoomPrivilege])
        )
      }
      Ok(Json.toJson(RoomsResponse(rooms)))
    }
  }

  def addRoom(): Action[AddRoomRequest] = actions.authenticate.async(parse.json[AddRoomRequest]) { request =>
    Future {
      roomOperations.addRoom(request.body.name, request.body.description, request.user.values)
      Ok
    }
  }
}
