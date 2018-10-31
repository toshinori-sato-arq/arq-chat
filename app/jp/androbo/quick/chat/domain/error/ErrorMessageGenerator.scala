package jp.androbo.quick.chat.domain.error

trait ErrorMessageGenerator {
  def generate(e: ErrorEvent): String
}
