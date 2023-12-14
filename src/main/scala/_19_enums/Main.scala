package _19_enums

object Main extends App {

  // перечисляемые типы данных (EDT)
  import Direction.*

  println(North.degrees)
  println(North.invert)

  def allButNearset(degrees: Int): List[Direction] =
    val nearest = Direction.nearestTo(degrees)
    Direction.values.toList.filter(_ != nearest)

  println(allButNearset(42))
  println("----------------------------------")

  // алгебраические типы данных (ADT)
  import Eastwood.*

  val eastwood = Good(41)
  println(eastwood.map(n => n + 1))

  println(Good(1))
  println(new Good(1))

  enum Seinfeld[+E] {
    def ::[E2 >: E](o: E2): Seinfeld[E2] = Yada(o, this)
    case Yada(head: E, tail: Seinfeld[E])
    case Nada
  }

  import Seinfeld.*

  val xs = 1 :: 2 :: 3 :: Nada
  println(xs)
  println("----------------------------------------")

  // обобщенные ADT
  import Literal.*

  def valueOfLiteral[T](lit: Literal[T]): T =
    lit match
      case IntLit(n) => n
      case LongLit(m) => m
      case CharLit(c) => c
      case FloatLit(f) => f
      case DoubleLit(d) => d
      case BooleanLit(b) => b
      case StringLit(s) => s

  println(valueOfLiteral(BooleanLit(true)))
  println(valueOfLiteral(IntLit(42)))
  
  

}
