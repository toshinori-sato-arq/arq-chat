package jp.androbo.quick.chat.domain.model.room.operation

import jp.androbo.quick.chat.domain.model.event.error.ErrorEvent
import jp.androbo.quick.chat.domain.model.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.model.room._
import jp.androbo.quick.chat.domain.model.user.User

trait RoomOperations {

  protected def operation[P <: RoomPrivilege, E >: ErrorEvent, T](room: Room, updater: User, requiredPrivileges: Set[P])(op: () => T): Either[E, T] = {
    if (room.users.get(updater.id).exists { u =>
      requiredPrivileges.forall(u.privileges.contains)
    }) {
      Right(op())
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
