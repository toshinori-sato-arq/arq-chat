package jp.androbo.quick.chat.domain.user

import jp.androbo.quick.chat.domain.error.ErrorEvent

trait UserFactory {
  def create(email: String, name: String, password: String): Either[ErrorEvent, User]
}
