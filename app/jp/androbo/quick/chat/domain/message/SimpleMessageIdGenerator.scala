package jp.androbo.quick.chat.domain.message

import java.util.UUID

import javax.inject.Singleton

@Singleton
class SimpleMessageIdGenerator extends MessageIdGenerator {
  override def generate(): MessageId = MessageId(UUID.randomUUID().toString)
}
