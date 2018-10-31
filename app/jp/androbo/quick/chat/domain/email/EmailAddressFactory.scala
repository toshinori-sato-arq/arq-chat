package jp.androbo.quick.chat.domain.email

import jp.androbo.quick.chat.domain.error.ErrorEvent

trait EmailAddressFactory {
  def create(s: String): Either[ErrorEvent, EmailAddress]
}
