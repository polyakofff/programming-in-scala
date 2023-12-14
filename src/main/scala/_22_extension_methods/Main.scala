package _22_extension_methods

object Main extends App {

  extension (s: String)
    def singleSpace: String =
      s.trim.split("\\s+").mkString(" ")

  val s = "Hello man"
  val t = " Hello\tman "
  println(s.singleSpace == t.singleSpace)

  println("-----------------------------------")

  extension [T](xs: List[T])
    def tailOption: Option[List[T]] =
      if xs.nonEmpty then Some(xs.tail) else None

  println(List.empty[String].tailOption)

  println("-----------------------------------------")

  extension (n: Int)
    def isMinValue: Boolean = n == Int.MinValue
    def absOption: Option[Int] =
      if !isMinValue then Some(n.abs) else None
    def negateOption: Option[Int] =
      if !isMinValue then Some(-n) else None

  println(1.negateOption)
  println(Int.MinValue.negateOption)

  println("----------------------------------------")

  extension [N](n: N)(using tc: TwosComplement[N])
    def isMinValue: Boolean = tc.equalsMinValue(n)
    def absOption: Option[N] =
      if !isMinValue then Some(tc.absOf(n)) else None
    def negateOption: Option[N] =
      if !isMinValue then Some(tc.negationOf(n)) else None

  println(Byte.MinValue.negateOption)
  println(Byte.MaxValue.negateOption)
  println(Long.MinValue.negateOption)
  println(Long.MaxValue.negateOption)

  println("------------------------------------")

  // методы расширения в трейте Ord
  import _21_givens.Ord
  def insert[T](x: T, xs: List[T])(using Ord[T]): List[T] =
    if xs.isEmpty || x <= xs.head then x :: xs
    else xs.head :: insert(x, xs.tail)
  

}
