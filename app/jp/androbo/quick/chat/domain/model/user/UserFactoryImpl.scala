package jp.androbo.quick.chat.domain.model.user

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.domain.Password
import jp.androbo.quick.chat.domain.model.email.EmailAddressFactory
import jp.androbo.quick.chat.domain.model.event.error.ErrorEvent

@Singleton
class UserFactoryImpl @Inject() (emailAddressFactory: EmailAddressFactory) extends UserFactory {
  override def create(email: String, name: String, password: String): Either[ErrorEvent, User] = {
    emailAddressFactory.create(email).map { _email =>
      User(
        UserId(_email),
        UserName(name),
        Password(password),
      )
    }
  }
}
