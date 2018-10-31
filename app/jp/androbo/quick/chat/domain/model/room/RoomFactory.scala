package jp.androbo.quick.chat.domain.model.room

import jp.androbo.quick.chat.domain.model.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.model.user.User

trait RoomFactory {
  def create(user: User, privileges: Set[RoomPrivilege], name: RoomName, description: RoomDescription): Room
}
