package jp.androbo.quick.chat.web.controller.authentication

import play.api.libs.json.{Format, Json}

case class SignUpRequest(name: String, email: String, password: String)

object SignUpRequest {
  implicit val format: Format[SignUpRequest] = Json.format[SignUpRequest]
}