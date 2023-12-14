package _5_basic_types_and_operations

import scala.language.experimental
import scala.language.postfixOps

object Main extends App {

  val name = "Lev"
  println(s"Hello, $name")
  println(s"The answer is ${6 * 7}")
  println(raw"No\\\\escape")
  println(f"${math.Pi}%.9f")


  val a = 1.+(2)
  println(a)

  val b = 10 toLong;
  println(b)

  val c = - 10
  println(c)

  val d = 2.0.unary_-
  println(d)

//  val inv = ~(-3)
  val neg = 3 unary_-;
  println(neg)
  val inv = neg unary_~;
  println(inv)

  val toBe = true
  val notToBe = toBe unary_!;
  println(notToBe)

  val max = 2 max 3
  println(max)

}
