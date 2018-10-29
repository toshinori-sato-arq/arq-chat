package jp.androbo.quick.chat.web.controller.login

import play.api.libs.json.{Format, Json}

case class LoginRequest(email: String, password: String)

object LoginRequest {
  implicit val format: Format[LoginRequest] = Json.format[LoginRequest]
}
