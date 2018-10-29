package jp.androbo.quick.chat.domain.model.email

import jp.androbo.quick.chat.domain.model.event.error.ErrorEvent

trait EmailAddressFactory {
  def create(s: String): Either[ErrorEvent, EmailAddress]
}
