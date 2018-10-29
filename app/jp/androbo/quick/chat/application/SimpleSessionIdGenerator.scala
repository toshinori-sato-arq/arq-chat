package jp.androbo.quick.chat.application

import java.util.UUID

import javax.inject.{Inject, Singleton}

import scala.annotation.tailrec

@Singleton
class SimpleSessionIdGenerator @Inject() (val manager: SessionManager) extends SessionIdGenerator {
  override def generate(): SessionId = {
    @tailrec
    def inner(): SessionId = {
      val id = SessionId(UUID.randomUUID().toString)
      if (manager.findUserBySessionId(id).isEmpty)
        id
      else
        inner()
    }
    inner()
  }
}
