package jp.androbo.quick.chat.domain.model.room.operation

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.domain.model.event.error.ErrorEvent
import jp.androbo.quick.chat.domain.model.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.model.room._
import jp.androbo.quick.chat.domain.model.user.User

@Singleton
class RoomOperationsImpl @Inject()(
                               roomFactory: RoomFactory,
                               roomRepository: RoomRepository
                               ) extends RoomOperations {

  override def updateRoomName(name: RoomName, room: Room, updater: User): Either[ErrorEvent, Unit] = {
    operation(room, updater, Set(RoomPrivilege.RoomNameRenamable)) { () =>
      roomRepository.update(room.copy(name = name))
      Right(())
    }
  }

  override def updateRoomDescription(desc: RoomDescription, room: Room, updater: User): Either[ErrorEvent, Unit] = {
    operation(room, updater, Set(RoomPrivilege.RoomDescriptionEditable)) { () =>
      roomRepository.update(room.copy(description = desc))
      Right(())
    }
  }

  override def leave(room: Room, leaver: User): Either[ErrorEvent, Unit] = {
    operation(room, leaver, Set(RoomPrivilege.RoomLeavable)) { () =>
      roomRepository.leave(room.id, leaver)
      Right(())
    }
  }

  override def join(room: Room, updater: User, newComer: User, newComerPrivileges: Set[RoomPrivilege]): Either[ErrorEvent, Unit] = {
    operation(room, updater, Set(RoomPrivilege.RoomInvitable)) { () =>
      roomRepository.join(room.id, newComer, newComerPrivileges)
      Right(())
    }
  }

  override def addMyPage(user: User): Unit = {
    val r = roomFactory.create(user, RoomPrivilege.Preset.MyPage, RoomName("マイページ"), RoomDescription(""))
    roomRepository.add(r)
  }

  override def addRoom(name: RoomName, desc: RoomDescription, owner: User): Unit = {
    val r = roomFactory.create(owner, RoomPrivilege.Preset.Owner, name, desc)
    roomRepository.add(r)
  }
}
