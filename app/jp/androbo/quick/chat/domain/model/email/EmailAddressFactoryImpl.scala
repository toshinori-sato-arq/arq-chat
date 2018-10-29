package jp.androbo.quick.chat.domain.model.email

import javax.inject.Singleton
import jp.androbo.quick.chat.domain.model.event.error.ErrorEvent

@Singleton
class EmailAddressFactoryImpl extends EmailAddressFactory {
  override def create(s: String): Either[ErrorEvent, EmailAddress] = {
    if (EmailAddress.isValidEmailAddress(s)) {
      Right(EmailAddress(s))
    } else {
      Left(ErrorEvent.InvalidEmailAddress)
    }
  }
}
