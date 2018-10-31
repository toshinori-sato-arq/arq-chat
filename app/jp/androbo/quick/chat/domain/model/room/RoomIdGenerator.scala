package jp.androbo.quick.chat.domain.model.room

trait RoomIdGenerator {
  def generate(): RoomId
}
