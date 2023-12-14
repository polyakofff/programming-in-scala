package _17_scala_hierarchy

object Main extends App {

  val x = new String("abc")
  val y = new String("abc")
  println(x == y)
  println(x eq y)
  println(x ne y)

  println("--------------------------------------------")

  // классы значений
  class Dollars(val amount: Int) extends AnyVal{
    override def toString: String = "$" + amount
  }

  val money = new Dollars(1_000_000)

  println(money)
  println("--------------------------------------------")

  // тип пересечения
  import _11_traits.Main.{BasicIntQueue, Incrementing, Filtering}

  val q = new BasicIntQueue with Incrementing with Filtering
  q.put(-1)
  q.put(0)

  val q2: Filtering & Incrementing = q
  println(q2.get())

  println("----------------------------------------------")

  // тип объединения
  def errorMessage(msg: Int | String): String =
    msg match
      case n: Int => s"Error number: ${n.abs}"
      case s: String => s + "!"

  println(errorMessage(-42))
  println(errorMessage("Oops"))

  println("------------------------------------------")


}
