package _8_functions_and_closures

object Main extends App {

  val increase = (x: Int) => x + 1
  println(increase(10))

  val addTwo = (x: Int) => {
    val increment = 2
    x + increment
  }
  println(addTwo(10))

  println("-------------------------------------------")

  val someNumbers = List(-3, -2, -1, 0, 1, 2)
  println(someNumbers.filter(_ > 0))

  val f = (_: Int) + (_: Int)
  println(f(1, 2))

  println("-----------------------------------------")

  // частично примененные функции
  def sum(a: Int, b: Int, c: Int) = a + b + c

  val a = sum(_, _, _)
  println(a(1, 2, 3))

  val b = sum(1, _, 3)
  println(b(2))

  val c = sum
  println(c(1, 2, 3))

  println("-------------------------------------")

  // замыкания
  var s = 0
  someNumbers.foreach(s += _)
  println(s)

  println("--------------------------------")

  def makeIncreaser(more: Int) = (x: Int) => x + more

  val inc1 = makeIncreaser(1)
  val inc999 = makeIncreaser(999)

  println(inc1(10))
  println(inc999(10))


  println("---------------------------------------")

  def echo(args: String*) =
    for arg <- args do println(arg)

  echo("Hello", "man")

  val seq = Seq("Hello", "man")
  echo(seq*)

  println("----------------------------------------")

  def speed(distance: Float, time: Float) = distance / time

  println(speed(5, 2))
  println(speed(time = 2, distance = 5))

  println("------------------------------------------")

  trait Increaser:
    def increase(x: Int): Int

  def increase1(increaser: Increaser) = increaser.increase(1)

  println(increase1(x => x + 7))

  println("-------------------------------------------------")



}
