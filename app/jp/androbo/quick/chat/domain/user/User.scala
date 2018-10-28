package jp.androbo.quick.chat.domain.user

import java.time.LocalDateTime

import jp.androbo.quick.chat.domain.Password
import play.api.libs.json.{Format, Json}

case class User(
               id: UserId,
               name: UserName,
               password: Password,
               createAt: LocalDateTime,
               updateAt: LocalDateTime,
               )

object User {
  implicit val format: Format[User] = Json.format[User]
}