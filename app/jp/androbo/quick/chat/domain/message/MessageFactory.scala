package jp.androbo.quick.chat.domain.message

import jp.androbo.quick.chat.domain.room.RoomId
import jp.androbo.quick.chat.domain.user.UserId

trait MessageFactory {
  def create(text: MessageText, roomId: RoomId, userId: UserId): Message
}
