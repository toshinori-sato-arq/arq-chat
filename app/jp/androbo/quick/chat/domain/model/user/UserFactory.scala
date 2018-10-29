package jp.androbo.quick.chat.domain.model.user

import jp.androbo.quick.chat.domain.model.event.error.ErrorEvent

trait UserFactory {
  def create(email: String, name: String, password: String): Either[ErrorEvent, User]
}
