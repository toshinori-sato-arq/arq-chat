package jp.androbo.quick.chat.infra.storage.in_memory

import javax.inject.Singleton
import jp.androbo.quick.chat.domain.model.event.error.ErrorEvent
import jp.androbo.quick.chat.domain.model.user.{User, UserId, UserRepository}

import scala.collection.mutable

/**
 * 永続化ができるまでの間に合わせの実装
 * @note 複数プロセス構成にしたら同期することが困難になるので、それまでには廃止すること
 */
@Singleton
class InMemoryUserRepository extends UserRepository {
  private val memory = mutable.Map.empty[UserId, User]

  override def add(user: User): Either[ErrorEvent, Unit] = this.synchronized {
    if (memory.contains(user.id)) {
      Left(ErrorEvent.DuplicatedEmailAddress)
    } else {
      memory += user.id -> user
      Right(())
    }
  }

  override def findById(userId: UserId): Option[User] = this.synchronized {
    memory.get(userId)
  }

  override def list: Seq[User] = this.synchronized(memory.values.toSeq)
}
