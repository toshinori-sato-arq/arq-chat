package jp.androbo.quick.chat.infra.storage.in_memory

import jp.androbo.quick.chat.domain.model.email.{EmailAddress, EmailAddressFactoryImpl}
import jp.androbo.quick.chat.domain.model.event.error.ErrorEvent
import jp.androbo.quick.chat.domain.model.user.{UserFactoryImpl, UserId}
import org.scalatest.FreeSpec

class InMemoryUserRepositorySpec extends FreeSpec {
  private val userFactory = new UserFactoryImpl(
    new EmailAddressFactoryImpl()
  )
  private val userA = userFactory.create("a@domain", "a", "aa")
  private val userDuplicatedEmailOfA = userFactory.create("a@domain", "b", "bb")
  private val userB = userFactory.create("b@domain", "b", "bb")
  "InMemoryUserRepository" - {
    "add" - {
      "add into empty and get record" - {
        val r = new InMemoryUserRepository()
        userA.flatMap { a =>
          val ret1 = r.add(a)
          assert(r.findById(a.id).get === a)
          assert(r.findById(UserId(EmailAddress("c@domain"))) === None)
          assert(ret1 === Right(()))
          userB.map { b =>
            val ret2 = r.add(b)
            assert(ret2 === Right(()))
          }

        }.getOrElse(assert(true === false))
      }
      "add duplicate email entry into empty" - {
        val r = new InMemoryUserRepository()
        userA.flatMap { a =>
          val ret1 = r.add(a)
          assert(ret1 === Right(()))
          userDuplicatedEmailOfA.map { b =>
            val ret2 = r.add(b)
            assert(ret2 === Left(ErrorEvent.DuplicatedEmailAddress))
          }
        }.getOrElse(assert(true === false))
      }
    }
  }
}
