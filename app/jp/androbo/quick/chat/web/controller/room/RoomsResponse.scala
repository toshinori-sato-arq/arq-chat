package jp.androbo.quick.chat.web.controller.room

import play.api.libs.json.{Format, Json}

case class RoomsResponse(rooms: Set[RoomResponse])

object RoomsResponse {
  implicit val format: Format[RoomsResponse] = Json.format[RoomsResponse]
}
