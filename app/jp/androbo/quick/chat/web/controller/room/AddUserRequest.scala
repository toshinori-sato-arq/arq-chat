package jp.androbo.quick.chat.web.controller.room

import jp.androbo.quick.chat.domain.user.UserId
import play.api.libs.json.{Format, Json}

case class AddUserRequest(userId: UserId)

object AddUserRequest {
  implicit val format: Format[AddUserRequest] = Json.format[AddUserRequest]
}
