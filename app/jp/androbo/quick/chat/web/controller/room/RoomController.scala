package jp.androbo.quick.chat.web.controller.room

import javax.inject.{Inject, Singleton}
import jp.androbo.quick.chat.domain.error.{ErrorEvent, ErrorMessageGenerator}
import jp.androbo.quick.chat.domain.message.{MessageFactory, MessageRepository}
import jp.androbo.quick.chat.domain.privilege.RoomPrivilege
import jp.androbo.quick.chat.domain.room.{RoomId, RoomRepository}
import jp.androbo.quick.chat.domain.room.operation.RoomOperations
import jp.androbo.quick.chat.domain.user.UserRepository
import jp.androbo.quick.chat.web.controller.{ApiActions, ErrorResponse}
import play.api.libs.json.Json
import play.api.mvc.{AbstractController, AnyContent, ControllerComponents}

import scala.concurrent.{ExecutionContext, Future}
import play.api.mvc._

@Singleton
class RoomController @Inject()(
                                  cc: ControllerComponents,
                                  actions: ApiActions,
                                  roomRepository: RoomRepository,
                                  roomOperations: RoomOperations,
                                  errorMessageGenerator: ErrorMessageGenerator,
                                  userRepository: UserRepository,
                                  messageFactory: MessageFactory,
                                  messageRepository: MessageRepository,
                                  implicit val ec: ExecutionContext,
                                ) extends AbstractController(cc) {
  def rooms(): Action[AnyContent] = actions.authenticate.async { request =>
    Future {
      val rooms = roomRepository.list(request.user.values.id).map { r =>
        RoomResponse(
          r.id,
          r.name,
          r.description,
          r.users.get(request.user.values.id).map(_.privileges).getOrElse(Set.empty[RoomPrivilege])
        )
      }
      Ok(Json.toJson(RoomsResponse(rooms)))
    }
  }

  def addRoom(): Action[AddRoomRequest] = actions.authenticate.async(parse.json[AddRoomRequest]) { request =>
    Future {
      roomOperations.addRoom(request.body.name, request.body.description, request.user.values)
      Ok
    }
  }

  def deleteRoom(roomId: String): Action[AnyContent] = actions.authenticate.async { request =>
    Future {
      roomOperations.deleteRoom(RoomId(roomId), request.user.values).fold(
        e => BadRequest(Json.toJson(ErrorResponse(errorMessageGenerator.generate(e)))),
        _ => Ok
      )
    }
  }

  def users(roomId: String): Action[AnyContent] = actions.authenticate.async { request =>
    Future {
      (for {
        room <- roomRepository.findById(RoomId(roomId)).toRight(ErrorEvent.NotFound)
        _ <- room.users.get(request.user.values.id).toRight(ErrorEvent.UnauthorizedOperation)
      } yield room.users.values.map(u => UserResponse(u.values.id.email, u.values.name))).fold({ e =>
        BadRequest(Json.toJson(ErrorResponse(errorMessageGenerator.generate(e))))
      }, { users =>
        Ok(Json.toJson(users))
      })
    }
  }

  def addUser(roomId: String): Action[AddUserRequest] = actions.authenticate.async(parse.json[AddUserRequest]) { request =>
    Future {
      (for {
        room <- roomRepository.findById(RoomId(roomId)).toRight(ErrorEvent.NotFound)
        newComer <- userRepository.findById(request.body.userId).toRight(ErrorEvent.NotFound)
        _ <- roomOperations.join(room, request.user.values, newComer, RoomPrivilege.Preset.BeInvited)
      } yield ()).fold(e => BadRequest(Json.toJson(ErrorResponse(errorMessageGenerator.generate(e)))), _ => Ok)
    }
  }

  def leave(roomId: String): Action[AnyContent] = actions.authenticate.async { request =>
    Future {
      (for {
        room <- roomRepository.findById(RoomId(roomId)).toRight(ErrorEvent.NotFound)
        user <- userRepository.findById(request.user.values.id).toRight(ErrorEvent.NotFound)
        _ <- roomOperations.leave(room, user)
      } yield ()).fold(e => BadRequest(Json.toJson(ErrorResponse(errorMessageGenerator.generate(e)))), _ => Ok)
    }
  }

  def messages(roomId: String): Action[AnyContent] = actions.authenticate.async { request =>
    Future {
      (for {
        room <- roomRepository.findById(RoomId(roomId)).toRight(ErrorEvent.NotFound)
        _ <- room.users.get(request.user.values.id).toRight(ErrorEvent.UnauthorizedOperation)
      } yield messageRepository.list(room.id)).fold({ e =>
        BadRequest(Json.toJson(ErrorResponse(errorMessageGenerator.generate(e))))
      }, { messages =>
        Ok(Json.toJson(messages))
      })
    }
  }

  def postMessage(roomId: String): Action[PostMessageRequest] = actions.authenticate.async(parse.json[PostMessageRequest]) { request =>
    Future {
      (for {
        room <- roomRepository.findById(RoomId(roomId)).toRight(ErrorEvent.NotFound)
        u <- room.users.get(request.user.values.id).toRight(ErrorEvent.UnauthorizedOperation)
      } yield {
        val message = messageFactory.create(request.body.text, room.id, u.values.id)
        messageRepository.add(message)
      }).fold({ e =>
        BadRequest(Json.toJson(ErrorResponse(errorMessageGenerator.generate(e))))
      }, { _ =>
        Ok
      })
    }
  }

}
