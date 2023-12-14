package _18_types_parameterization

object Main extends App {

  class Cell[T](init: T) {
    private var curr = init
    def get = curr
    def set(x: T) =
      curr = x
  }

  val c1 = new Cell[String]("abc")
  // не работает
  // val c2: Cell[Any] = c1

  println("---------------------------------------")

  trait OutputChannel[-T] {
    def write(x: T): Unit
  }

  // OutputChannel[AnyRef] может быть подтипом OutputChannel[String]

  println("-----------------------------------------")

  trait Function1[-S, +T] {
    def apply(x: S): T
  }

  println("------------------------------------------")

  // верхний ограничитель

  class Person(val name: String) extends Ordered[Person] {
    override def compare(that: Person) =
      this.name.compareTo(that.name)

    override def toString: String = name
  }

  def someSort[T <: Ordered[T]](xs: List[T]): List[T] =
    xs

  println(someSort(List(Person("c"), Person("b"), Person("a"))))


}
