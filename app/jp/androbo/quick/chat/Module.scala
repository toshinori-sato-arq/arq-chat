package jp.androbo.quick.chat

import com.google.inject.AbstractModule
import jp.androbo.quick.chat.application.{SessionIdGenerator, SessionManager, SimpleSessionIdGenerator}
import jp.androbo.quick.chat.domain.model.email.{EmailAddressFactory, EmailAddressFactoryImpl}
import jp.androbo.quick.chat.domain.model.event.error.{ErrorMessageGenerator, SimpleErrorMessageGenerator}
import jp.androbo.quick.chat.domain.model.user.{UserFactory, UserFactoryImpl, UserRepository}
import jp.androbo.quick.chat.infra.storage.in_memory.{InMemorySessionManager, InMemoryUserRepository}
import net.codingwell.scalaguice.ScalaModule

class Module extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[EmailAddressFactory].to[EmailAddressFactoryImpl]
    bind[ErrorMessageGenerator].to[SimpleErrorMessageGenerator]
    bind[UserFactory].to[UserFactoryImpl]
    bind[UserRepository].to[InMemoryUserRepository]
    bind[SessionManager].to[InMemorySessionManager]
    bind[SessionIdGenerator].to[SimpleSessionIdGenerator]
  }
}
