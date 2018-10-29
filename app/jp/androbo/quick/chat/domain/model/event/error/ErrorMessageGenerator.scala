package jp.androbo.quick.chat.domain.model.event.error

trait ErrorMessageGenerator {
  def generate(e: ErrorEvent): String
}
