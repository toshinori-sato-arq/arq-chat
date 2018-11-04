package jp.androbo.quick.chat.domain.message

trait MessageIdGenerator {
  def generate(): MessageId
}
