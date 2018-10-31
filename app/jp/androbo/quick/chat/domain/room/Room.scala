package jp.androbo.quick.chat.domain.room

import jp.androbo.quick.chat.domain.user.UserId
import play.api.libs.json.{Json, Writes}

case class Room(
                 id: RoomId,
                 name: RoomName,
                 description: RoomDescription,
                 users: Map[UserId, UserInRoom],
               )

object Room {
  implicit val format: Writes[Room] = Json.writes[Room]
}