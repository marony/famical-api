package services

import models.AuthUser
import models.AuthRole

object AuthenticationService {
  private val users = List[AuthUser](
    AuthUser(1, "loginId:1", AuthRole.FamilyOwner),
    AuthUser(2, "loginId:2", AuthRole.FamilyMember)
  )

  def userOfId(id: Int): Option[AuthUser] = {
    users.find(_.id == id)
  }

  def authenticate(loginId: String, password: String): Option[AuthUser] = {
    users.find(_.loginId == loginId)
  }
}
