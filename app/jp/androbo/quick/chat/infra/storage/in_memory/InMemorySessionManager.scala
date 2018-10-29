package jp.androbo.quick.chat.infra.storage.in_memory

import javax.inject.Singleton
import jp.androbo.quick.chat.application.{SessionId, SessionManager}
import jp.androbo.quick.chat.domain.model.user.User

import scala.collection.mutable

@Singleton
class InMemorySessionManager extends SessionManager {
  private val storage = mutable.Map.empty[SessionId, User]
  override def findUserBySessionId(s: SessionId): Option[User] = this.synchronized(storage.get(s))
  override def add(s: SessionId, u: User): Unit = this.synchronized{
    storage.find{case (_, _u) => u.id == _u.id}.foreach{ case (_s, _) => storage -= _s }
    storage += s -> u
  }
  override def delete(s: SessionId): Unit = this.synchronized(storage -= s)

  override def list: Map[SessionId, User] = this.synchronized(storage.toMap)
}
