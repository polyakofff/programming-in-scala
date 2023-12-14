package _21_givens

object Main extends App {

  import JillsPrefs.{prompt, drink}

  Greater.greet("Jill")

  println("------------------------------------")

  // параметризованные given-типы
  def isort[T](xs: List[T])(using ord: Ord[T]): List[T] =
    if xs.isEmpty then Nil
    else insert(xs.head, isort(xs.tail))

  def insert[T](x: T, xs: List[T])(using ord: Ord[T]): List[T] =
    if xs.isEmpty || ord.lteq(x, xs.head) then x :: xs
    else xs.head :: insert(x, xs.tail)

  import Ord.{given_Ord_Int, given_Ord_String}
  println(isort(List(3, 2, 1))(using given_Ord_Int))
  println(isort(List("3", "2", "1"))(using given_Ord_String))

  println("------------------------------------")

  // классы типов
  def msort[T](xs: List[T])(using ord: Ordering[T]): List[T] =
    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match
        case (_, Nil) => xs
        case (Nil, _) => ys
        case (x :: xs1, y :: ys1) =>
          if ord.lt(x, y) then x :: merge(xs1, ys)
          else y :: merge(xs, ys1)

    val n = xs.length / 2
    if n == 0 then xs
    else
      val (ys, zs) = xs.splitAt(n)
      merge(msort(ys), msort(zs))

  import Ord.{given_Ord_Int, given_Ord_String}
  println(msort(List(3, 2, 1)))
  println(msort(List("3", "2", "1")))

  println("-----------------------------------------")

  // импорт всего, кроме гивен
  import TomsPrefs.*
  // импорт prompt
  import TomsPrefs.prompt
  // импорт drink
  import TomsPrefs.{given PrefferedDrink}
  // импорт prefPromptOrd и prefDrinkOrd
  import TomsPrefs.{given Ordering[?]}
  // импорт всех гивен
  import TomsPrefs.given



}
