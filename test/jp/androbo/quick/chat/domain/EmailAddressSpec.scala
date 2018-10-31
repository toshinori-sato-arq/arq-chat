package jp.androbo.quick.chat.domain

import jp.androbo.quick.chat.domain.email.EmailAddress
import org.scalatest.FreeSpec
import play.api.libs.json.{Json, OFormat}

case class WrapEmailAddress(email: EmailAddress)
object WrapEmailAddress {
  implicit val format: OFormat[WrapEmailAddress] = Json.format[WrapEmailAddress]
}

class EmailAddressSpec extends FreeSpec {
  "EmailAddress" - {
    "isValidEmailAddress" in {
      assert(EmailAddress.isValidEmailAddress("a@a") === true)
      assert(EmailAddress.isValidEmailAddress("@a") === false)
      assert(EmailAddress.isValidEmailAddress("a@") === false)
      assert(EmailAddress.isValidEmailAddress("@@") === false)
      assert(EmailAddress.isValidEmailAddress("a@a@") === false)
      assert(EmailAddress.isValidEmailAddress("@a@a") === false)
      assert(EmailAddress.isValidEmailAddress("@@") === false)
    }

    "Json parse" in {
      val emailString = "a@a"
      val email = EmailAddress(emailString)
      assert(Json.parse(s"""{"email":"$emailString"}""").validate[WrapEmailAddress].get === WrapEmailAddress(email))
    }

    "to Json" in {
      val s = Json.stringify(Json.toJson(WrapEmailAddress(EmailAddress("test@domain"))))
      println(s"WrapEmailAddress to Json string is [$s]")
      assert(s === """{"email":"test@domain"}""")
    }
  }
}
