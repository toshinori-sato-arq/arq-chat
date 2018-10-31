package jp.androbo.quick.chat.domain.model.room

import jp.androbo.quick.chat.domain.model.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.model.user.User
import play.api.libs.json.{Format, Json}

case class UserInRoom(values: User, privileges: Set[RoomPrivilege])

object UserInRoom {
  implicit val format: Format[UserInRoom] = Json.format[UserInRoom]
}