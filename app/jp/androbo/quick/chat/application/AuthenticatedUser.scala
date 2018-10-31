package jp.androbo.quick.chat.application

import jp.androbo.quick.chat.domain.user.User

case class AuthenticatedUser(sessionId: SessionId, values: User)
