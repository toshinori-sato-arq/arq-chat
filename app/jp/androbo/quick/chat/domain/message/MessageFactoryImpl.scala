package jp.androbo.quick.chat.domain.message

import java.time.LocalDateTime

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.domain.room.RoomId
import jp.androbo.quick.chat.domain.user.UserId

@Singleton
class MessageFactoryImpl @Inject() (
                                   idGenerator: MessageIdGenerator
                                   ) extends MessageFactory {
  override def create(text: MessageText, roomId: RoomId, userId: UserId): Message = {
    val now = LocalDateTime.now
    Message(
      idGenerator.generate(),
      text,
      roomId,
      userId,
      now,
      now,
      deleted = false,
    )
  }
}
