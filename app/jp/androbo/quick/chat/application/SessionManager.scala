package jp.androbo.quick.chat.application

import jp.androbo.quick.chat.domain.user.User

trait SessionManager {
  def findUserBySessionId(s: SessionId): Option[User]
  def add(s: SessionId, u: User): Unit
  def delete(s: SessionId): Unit
  def list: Map[SessionId, User]
}
