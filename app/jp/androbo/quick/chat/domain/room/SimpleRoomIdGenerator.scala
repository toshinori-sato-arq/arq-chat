package jp.androbo.quick.chat.domain.room

import java.util.UUID

import javax.inject.Singleton

@Singleton
class SimpleRoomIdGenerator extends RoomIdGenerator {
  override def generate(): RoomId = RoomId(UUID.randomUUID().toString)
}
