package jp.androbo.quick.chat.application

import jp.androbo.quick.chat.domain.model.user.User

case class AuthenticatedUser(sessionId: SessionId, values: User)
