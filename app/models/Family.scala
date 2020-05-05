package models

import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import scalikejdbc._
import skinny.orm._
import skinny.orm.feature._
import skinny.orm.feature.associations.Association

case class Family(id: Int, name: String, insertedAt: DateTime, updatedAt: DateTime)

object Family extends SkinnyMapper[Family] {
  override lazy val tableName = "families"
  override lazy val defaultAlias = createAlias("f")
  override def extract(rs: WrappedResultSet, n: ResultName[Family]): Family = new Family(
    id = rs.get(n.id),
    name = rs.get(n.name),
    insertedAt = rs.get(n.insertedAt),
    updatedAt = rs.get(n.updatedAt))
}
