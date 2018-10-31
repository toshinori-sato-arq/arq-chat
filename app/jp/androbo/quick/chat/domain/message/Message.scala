package jp.androbo.quick.chat.domain.message

import java.time.LocalDateTime

import jp.androbo.quick.chat.domain.room.RoomId
import jp.androbo.quick.chat.domain.user.UserId
import play.api.libs.json.{Format, Json}

case class Message(
                  id: MessageId,
                  text: MessageText,
                  roomId: RoomId,
                  userId: UserId,
                  createAt: LocalDateTime,
                  updateAt: LocalDateTime,
                  deleted: Boolean
                  )

object Message {
  implicit val format: Format[Message] = Json.format[Message]
}