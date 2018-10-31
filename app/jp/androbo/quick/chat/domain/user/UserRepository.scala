package jp.androbo.quick.chat.domain.user

import jp.androbo.quick.chat.domain.error.ErrorEvent

trait UserRepository {
  def add(user: User): Either[ErrorEvent, Unit]
  def findById(userId: UserId): Option[User]
  def list: Seq[User]
}
