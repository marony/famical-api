package models

case class AuthUser(id: Int, loginId: String, role: AuthRole)

sealed trait AuthRole

object AuthRole {
  case object FamilyOwner extends AuthRole
  case object FamilyMember extends AuthRole
}
