package jp.androbo.quick.chat.web.controller.authentication

import jp.androbo.quick.chat.application.SessionId
import play.api.libs.json.{Format, Json}

case class LoginResponse(sessionId: SessionId)

object LoginResponse {
  implicit val format: Format[LoginResponse] = Json.format[LoginResponse]
}