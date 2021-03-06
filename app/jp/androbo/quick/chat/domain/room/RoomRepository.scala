package jp.androbo.quick.chat.domain.room

import jp.androbo.quick.chat.domain.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.user.{User, UserId}

trait RoomRepository {
  def findById(id: RoomId): Option[Room]
  def add(room: Room): Unit
  def delete(room: Room): Unit
  def list(userId: UserId): Set[Room]
  def join(roomId: RoomId, user: User, privileges: Set[RoomPrivilege]): Option[Room]
  def leave(roomId: RoomId, user: User): Option[Room]
  def update(room: Room): Unit
}
