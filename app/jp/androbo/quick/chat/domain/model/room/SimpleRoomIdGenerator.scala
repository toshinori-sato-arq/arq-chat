package jp.androbo.quick.chat.domain.model.room

import java.util.UUID

import javax.inject.Singleton

@Singleton
class SimpleRoomIdGenerator extends RoomIdGenerator {
  override def generate(): RoomId = RoomId(UUID.randomUUID().toString)
}
