package jp.androbo.quick.chat.web.controller

import play.api.libs.json.{Format, Json}

case class ErrorResponse(message: String)

object ErrorResponse {
  implicit val format: Format[ErrorResponse] = Json.format[ErrorResponse]
}
