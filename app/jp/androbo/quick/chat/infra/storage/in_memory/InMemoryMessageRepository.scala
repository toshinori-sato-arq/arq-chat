package jp.androbo.quick.chat.infra.storage.in_memory

import javax.inject.Singleton
import jp.androbo.quick.chat.domain.message.{Message, MessageRepository}
import jp.androbo.quick.chat.domain.room.RoomId

import scala.collection.mutable

@Singleton
class InMemoryMessageRepository extends MessageRepository {
  private val messages = mutable.ArrayBuffer.empty[Message]

  override def list(roomId: RoomId): Seq[Message] = this.synchronized {
    messages.filter(_.roomId == roomId)
  }

  override def add(message: Message): Unit = this.synchronized {
    messages += message
  }
}
