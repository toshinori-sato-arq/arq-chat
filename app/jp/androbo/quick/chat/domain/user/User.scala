package jp.androbo.quick.chat.domain.user

import jp.androbo.quick.chat.domain.Password
import play.api.libs.json.{Format, Json}

case class User private[user](
               id: UserId,
               name: UserName,
               password: Password,
               )

object User {
  implicit val format: Format[User] = Json.format[User]
}