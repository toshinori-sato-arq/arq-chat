package jp.androbo.quick.chat.infra.storage.in_memory

import javax.inject.Singleton
import jp.androbo.quick.chat.domain.model.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.model.room.{Room, RoomId, RoomRepository, UserInRoom}
import jp.androbo.quick.chat.domain.model.user.{User, UserId}

import scala.collection.mutable

@Singleton
class InMemoryRoomRepository extends RoomRepository {
  private val rooms = mutable.Map.empty[RoomId, Room]
  private val u2r = mutable.Map.empty[UserId, mutable.Map[RoomId, Room]]

  override def findById(id: RoomId): Option[Room] = this.synchronized(rooms.get(id))

  override def add(room: Room): Unit = this.synchronized {
    rooms += room.id -> room
    room.users.keys.foreach{ userId =>
      u2r.get(userId).fold(u2r += userId -> mutable.Map(room.id -> room))(r => u2r += userId -> (r += room.id -> room))
    }
  }

  override def delete(room: Room): Unit = this.synchronized {
    rooms -= room.id
    u2r.foreach{ case (_, rs) =>
      rs -= room.id
    }
  }

  override def list(userId: UserId): Set[Room] = this.synchronized {
    u2r.getOrElse(userId, mutable.Map.empty[RoomId, Room]).values.toSet
  }

  override def join(roomId: RoomId, user: User, privileges: Set[RoomPrivilege]): Option[Room] = this.synchronized {
    rooms.get(roomId).map { room =>
      u2r.get(user.id).fold(u2r += user.id -> mutable.Map(room.id -> room))(r => u2r += user.id -> (r += room.id -> room))
      room.copy(users = room.users + (user.id -> UserInRoom(user, privileges)))
    }
  }

  override def leave(roomId: RoomId, user: User): Option[Room] = this.synchronized {
    rooms.get(roomId).map { room =>
      u2r.get(user.id).foreach{
        rooms => u2r += user.id -> (rooms -= room.id)
      }
      room.copy(users = room.users - user.id)
    }
  }

  override def update(room: Room): Unit = this.synchronized {
    rooms += room.id -> room
    u2r.values.foreach(m => m.get(room.id).foreach(_ => m += room.id -> room))
  }
}
