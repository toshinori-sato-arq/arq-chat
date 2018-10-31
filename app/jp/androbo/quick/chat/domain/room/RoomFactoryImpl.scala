package jp.androbo.quick.chat.domain.room

import java.time.LocalDateTime

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.domain.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.user.User

@Singleton
class RoomFactoryImpl @Inject() (
                                idGenerator: RoomIdGenerator,
                                ) extends RoomFactory {
  override def create(user: User, privileges: Set[RoomPrivilege], name: RoomName, description: RoomDescription): Room = {
    Room(
      idGenerator.generate(),
      name,
      description,
      Map(user.id -> UserInRoom(user, privileges)),
    )
  }
}
