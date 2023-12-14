package _14_lists

import scala.util.Random

object Main extends App {

  val abcde = List('a', 'b', 'c', 'd', 'e')
  println(abcde.head)
  println(abcde.tail)
  println(abcde.last)
  println(abcde.init)
  println(abcde.take(2))
  println(abcde.drop(2))
  println(abcde.splitAt(2))

  println("-----------------------------------------")

  val xss = List(List(1, 2), List(3), List(), List(4, 5))
  println(xss.flatten)

  println("-----------------------------------------")

  val zipped = abcde.zip(List(1, 2, 3, 4))
  println(zipped)
  println(abcde.zipWithIndex)
  println(zipped.unzip)

  println("---------------------------------------")

  // сортировка слиянием
  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] =

    def merge(xs: List[T], ys: List[T]): List[T] =
      (xs, ys) match
        case (_, Nil) => xs
        case (Nil, _) => ys
        case (x :: xs1, y :: ys1) =>
          if less(x, y) then x :: merge(xs1, ys)
          else y :: merge(xs, ys1)

    val n = xs.length / 2
    if n == 0 then xs
    else
      val (ys, zs) = xs.splitAt(n)
      merge(msort(less)(ys), msort(less)(zs))

  println(msort((x: Int, y: Int) => x < y)(List(4, 3, 2, 1)))

  println("--------------------------------------")

  println(List.range(1, 5) flatMap (i => List.range(1, i) map (j => (i, j))))

  println("----------------------------------------")

  println(List(1, 2, 3, 4, 5).filter(_ % 2 == 0))
  println(List(1, 2, 3, 4, 5).partition(_ % 2 == 0))
  println(List(1, 2, 3, 4, 5).find(_ % 2 == 0))
  println(List(1, 2, 3, 4, 5).takeWhile(_ < 3))
  println(List(1, 2, 3, 4, 5).dropWhile(_ < 3))
  println(List(1, 2, 3, 4, 5).span(_ < 3))

  println("-----------------------------------")

  // свертка списков

  def flattenLeft[T](xss: List[List[T]]) =
    xss.foldLeft(List[T]())(_ ::: _)

  def flatternRight[T](xss: List[List[T]]) =
    xss.foldRight(List[T]())(_ ::: _)

  println(flattenLeft(xss)) // не эффективно за O(n^2)!
  println(flatternRight(xss))

  println("----------------------------------------------")

  def reverseLeft[T](xs: List[T]) =
    xs.foldLeft(List[T]()){(ys, y) => y :: ys}

  println(reverseLeft(List(1, 2, 3, 4, 5)))

  println("-------------------------------------------")

  println(List(3, 2, 1, 5, 4).sortWith(_ < _))

  println("-----------------------------------------")

  println(List.tabulate(5, 5)(_ * _))
  println("------------------------------------------------")

  println(List(10, 20).lazyZip(List(3, 4, 5)).map(_ * _))

  println("-------------------------------------------")

  // быстрая сортировка
  val rand = new Random
  def qsort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] =
    xs match
      case Nil => Nil
      case pivot :: rest =>
        qsort(less)(rest.filter(less(_, pivot))) ::: pivot :: qsort(less)(rest.filter(!less(_, pivot)))

  println(qsort((x: Int, y: Int) => x < y)(List(4, 3, 2, 1)))

}
