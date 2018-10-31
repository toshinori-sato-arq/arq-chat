package jp.androbo.quick.chat.domain.room

trait RoomIdGenerator {
  def generate(): RoomId
}
