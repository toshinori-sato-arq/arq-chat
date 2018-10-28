package jp.androbo.quick.chat.domain.user

import java.time.LocalDateTime

import jp.androbo.quick.chat.domain.{EmailAddress, Password}
import org.scalatest.FreeSpec
import play.api.libs.json.Json

class UserSpec extends FreeSpec {
  "User" - {
    "Json parse" in {
      val jsonString = """
        |{
        |   "id": "test@domain.com",
        |   "name": "abc",
        |   "password": "ppp",
        |   "createAt": "2018-10-28T03:10:20",
        |   "updateAt": "2018-10-28T03:10:30"
        |}
      """.stripMargin
      val fromBuild = User(
        UserId(EmailAddress("test@domain.com")),
        UserName("abc"),
        Password("ppp"),
        LocalDateTime.of(2018, 10, 28, 3, 10, 20),
        LocalDateTime.of(2018, 10, 28, 3, 10, 30)
      )
      println(s"build object parse string[${Json.stringify(Json.toJson(fromBuild))}]")
      val fromParse = Json.parse(jsonString).as[User]
      assert(fromParse === fromBuild)
    }

  }
}
