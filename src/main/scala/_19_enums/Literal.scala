package _19_enums

enum Literal[T] {

  case IntLit(value: Int) extends Literal[Int]
  case LongLit(value: Long) extends Literal[Long]
  case CharLit(value: Char) extends Literal[Char]
  case FloatLit(value: Float) extends Literal[Float]
  case DoubleLit(value: Double) extends Literal[Double]
  case BooleanLit(value: Boolean) extends Literal[Boolean]
  case StringLit(value: String) extends Literal[String]
}