package jp.androbo.quick.chat.application

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.domain.model.event.error.ErrorEvent
import jp.androbo.quick.chat.domain.model.room.operation.RoomOperations
import jp.androbo.quick.chat.domain.model.user.{User, UserRepository}

@Singleton
class Authentication @Inject()(
                                userRepository: UserRepository,
                                roomOperations: RoomOperations,
                               ) {
  def signUp(user: User): Either[ErrorEvent, Unit] = {
    val ret = userRepository.add(user)
    if (ret.isRight) {
      roomOperations.addMyPage(user)
    }
    ret
  }
}
