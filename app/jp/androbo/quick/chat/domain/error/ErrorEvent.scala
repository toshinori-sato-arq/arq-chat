package jp.androbo.quick.chat.domain.error

sealed abstract class ErrorEvent

object ErrorEvent {
  case object DuplicatedEmailAddress extends ErrorEvent
  case object InvalidEmailAddress extends ErrorEvent
  case object LoginError extends ErrorEvent
  case object UnauthorizedOperation extends ErrorEvent
  case object NotFound extends ErrorEvent
}