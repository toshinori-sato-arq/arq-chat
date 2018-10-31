package jp.androbo.quick.chat.domain.room

import jp.androbo.quick.chat.domain.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.user.User

trait RoomFactory {
  def create(user: User, privileges: Set[RoomPrivilege], name: RoomName, description: RoomDescription): Room
}
