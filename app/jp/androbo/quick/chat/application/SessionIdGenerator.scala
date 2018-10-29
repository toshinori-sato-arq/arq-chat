package jp.androbo.quick.chat.application

trait SessionIdGenerator {
  def generate(): SessionId
}
