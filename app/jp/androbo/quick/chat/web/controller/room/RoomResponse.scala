package jp.androbo.quick.chat.web.controller.room

import jp.androbo.quick.chat.domain.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.room.{RoomDescription, RoomId, RoomName}
import play.api.libs.json.{Format, Json}

case class RoomResponse(id: RoomId, name: RoomName, description: RoomDescription, privileges: Set[RoomPrivilege])

object RoomResponse {
  implicit val format: Format[RoomResponse] = Json.format[RoomResponse]
}