package jp.androbo.quick.chat.web.controller.room

import jp.androbo.quick.chat.domain.email.EmailAddress
import jp.androbo.quick.chat.domain.user.UserName
import play.api.libs.json.{Format, Json}

case class UserResponse(email: EmailAddress, name: UserName)

object UserResponse {
  implicit val format: Format[UserResponse] = Json.format[UserResponse]
}