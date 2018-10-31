package jp.androbo.quick.chat.web.controller.room

import jp.androbo.quick.chat.domain.model.room.{RoomDescription, RoomName}
import play.api.libs.json.{Format, Json}

case class AddRoomRequest(name: RoomName, description: RoomDescription)

object AddRoomRequest {
  implicit val format: Format[AddRoomRequest] = Json.format[AddRoomRequest]
}
