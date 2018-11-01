package jp.androbo.quick.chat.domain.room.operation

import jp.androbo.quick.chat.domain.error.ErrorEvent
import jp.androbo.quick.chat.domain.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.room._
import jp.androbo.quick.chat.domain.user.User

trait RoomOperations {

  protected def operation[P <: RoomPrivilege, E >: ErrorEvent, T](room: Room, updater: User, requiredPrivileges: Set[P])(op: () => Either[E, T]): Either[E, T] = {
    if (room.users.get(updater.id).exists { u =>
      requiredPrivileges.forall(u.privileges.contains)
    }) {
      op()
    } else {
      Left(ErrorEvent.UnauthorizedOperation)
    }
  }

  def updateRoomName(name: RoomName, room: Room, updater: User): Either[ErrorEvent, Unit]
  def updateRoomDescription(desc: RoomDescription, room: Room, updater: User): Either[ErrorEvent, Unit]
  def leave(room: Room, leaver: User): Either[ErrorEvent, Unit]
  def join(room: Room, updater: User, newComer: User, newComerPrivileges: Set[RoomPrivilege]): Either[ErrorEvent, Unit]
  def addMyPage(user: User): Unit
  def addRoom(name: RoomName, desc: RoomDescription, owner: User): Unit
}
