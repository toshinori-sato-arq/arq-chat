package jp.androbo.quick.chat.domain.room

import java.time.LocalDateTime

import play.api.libs.json.{Format, Json}

case class Room(
               id: RoomId,
               name: RoomName,
               description: RoomDescription,
               createAt: LocalDateTime,
               updateAt: LocalDateTime,
               deleted: Boolean,
               )

object Room {
  implicit val format: Format[Room] = Json.format[Room]
}
