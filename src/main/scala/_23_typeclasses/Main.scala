package _23_typeclasses

object Main extends App {

  // границы контекста
  def maxList[T](elements: List[T])(using ordering: Ordering[T]): T =
    elements match
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList(rest)(using ordering)
        if ordering.gt(x, maxRest) then x
        else maxRest

  println(maxList(List(1, 2, 3)))

  def maxList2[T](elements: List[T])(using ordering: Ordering[T]): T =
    elements match
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList2(rest)
        if summon[Ordering[T]].gt(x, maxRest) then x
        else maxRest

  println(maxList2(List(1, 2, 3)))

  def maxList3[T : Ordering](elements: List[T]): T =
    elements match
      case List() =>
        throw new IllegalArgumentException("empty list!")
      case List(x) => x
      case x :: rest =>
        val maxRest = maxList3(rest)
        if summon[Ordering[T]].gt(x, maxRest) then x
        else maxRest

  println(maxList3(List(1, 2, 3)))

  println("------------------------------------------")

  // многостороннее равенство
  case class Apple(size: Int) derives CanEqual
  // или
//  object Apple:
//    given canEq: CanEqual[Apple, Apple] = CanEqual.derived
  case class Orange(size: Int)

  val appleTwo = Apple(2)
  val appleTwoTwo = Apple(2)
  val appleThree = Apple(3)
  val orangeTwo = Orange(2)

  // не компилируется с derives
//  println(appleTwo == orangeTwo)

  import scala.language.strictEquality

  // норм
  println(appleTwo == appleTwoTwo)
  println(appleTwo == appleThree)

  println("------------------------------------")

  // неявные преобразования
  case class Street(value: String)
  object Street:
    given streetToString: Conversion[Street, String] = _.value

  val street = Street("123 Main St")
  val streetStr: String = street

  println(streetStr)

  println("---------------------------------------------")

  import ToJsonMethods.*

  println("tennis".toJson)
  println(10.toJson)
  println(true.toJson)
//  println(10.0.toJson)

  println("------------------------------------------")

  val addressBook = AddressBook(List(
    Contact(
      "Bob Smith",
      List(
        Address(
          "12345 Main Street",
          "San Francisco",
          "CA",
          94105
        ),
        Address(
          "500 State Street",
          "Los Angeles",
          "CA",
          90007
        )
      ),
      List(
        Phone(
          1,
          5558881234L
        ),
        Phone(
          49,
          5558413323L
        )
      )
    )
  ))

  println(addressBook.toJson)

}
