package jp.androbo.quick.chat

import com.google.inject.AbstractModule
import jp.androbo.quick.chat.application.{SessionIdGenerator, SessionManager, SimpleSessionIdGenerator}
import jp.androbo.quick.chat.domain.email.{EmailAddressFactory, EmailAddressFactoryImpl}
import jp.androbo.quick.chat.domain.error.{ErrorMessageGenerator, SimpleErrorMessageGenerator}
import jp.androbo.quick.chat.domain.message._
import jp.androbo.quick.chat.domain.room._
import jp.androbo.quick.chat.domain.room.operation.{RoomOperations, RoomOperationsImpl}
import jp.androbo.quick.chat.domain.user.{UserFactory, UserFactoryImpl, UserRepository}
import jp.androbo.quick.chat.infra.storage.in_memory.{InMemoryMessageRepository, InMemoryRoomRepository, InMemorySessionManager, InMemoryUserRepository}
import net.codingwell.scalaguice.ScalaModule

class Module extends AbstractModule with ScalaModule {
  override def configure(): Unit = {
    bind[RoomOperations].to[RoomOperationsImpl]

    bind[SessionIdGenerator].to[SimpleSessionIdGenerator]
    bind[ErrorMessageGenerator].to[SimpleErrorMessageGenerator]
    bind[RoomIdGenerator].to[SimpleRoomIdGenerator]
    bind[MessageIdGenerator].to[SimpleMessageIdGenerator]

    bind[EmailAddressFactory].to[EmailAddressFactoryImpl]
    bind[UserFactory].to[UserFactoryImpl]
    bind[RoomFactory].to[RoomFactoryImpl]
    bind[MessageFactory].to[MessageFactoryImpl]

    bind[UserRepository].to[InMemoryUserRepository]
    bind[SessionManager].to[InMemorySessionManager]
    bind[RoomRepository].to[InMemoryRoomRepository]
    bind[MessageRepository].to[InMemoryMessageRepository]
  }
}
