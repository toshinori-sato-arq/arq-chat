package jp.androbo.quick.chat.domain.message

import jp.androbo.quick.chat.domain.room.RoomId

trait MessageRepository {
  def list(roomId: RoomId): Seq[Message]
  def add(message: Message): Unit
}
