package _21_givens

trait Ord[T] {
  def compare(x: T, y: T): Int
  def lt(x: T, y: T): Boolean = compare(x, y) < 0
  def lteq(x: T, y: T): Boolean = compare(x, y) <= 0
  def gt(x: T, y: T): Boolean = compare(x, y) > 0
  def gteq(x: T, y: T): Boolean = compare(x, y) >= 0

  extension (lhs: T)
    def <(rhs: T): Boolean = lt(lhs, rhs)
    def <=(rhs: T): Boolean = lteq(lhs, rhs)
    def >(rhs: T): Boolean = gt(lhs, rhs)
    def >=(rhs: T): Boolean = gteq(lhs, rhs)
}

object Ord {
  given intOrd: Ord[Int] with
    override def compare(x: Int, y: Int): Int =
      if x == y then 0 else if x < y then -1 else 1

  given stringOrd: Ord[String] with
    override def compare(x: String, y: String): Int = x.compareTo(y)

  given Ord[Int] with
    override def compare(x: Int, y: Int): Int =
      if x == y then 0 else if x < y then 1 else -1
      
  given Ord[String] with
    override def compare(x: String, y: String): Int = -x.compareTo(y)
}
