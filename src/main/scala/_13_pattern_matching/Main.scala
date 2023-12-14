package _13_pattern_matching

object Main extends App {

  sealed trait Expr
  case class Var(name: String) extends Expr
  case class Num(number: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  def simplifyTop(expr: Expr): Expr =
    expr match
      case UnOp("-", UnOp("-", e)) => e
      case BinOp("+", e, Num(0)) => e
      case BinOp("*", e, Num(1)) => e
      case _ => expr

  println(simplifyTop(UnOp("-", UnOp("-", Var("x")))))
  println("----------------------------------")

  // подстановочный паттерн
  def match_(expr: Expr) =
    expr match
      case BinOp(op, left, right) => s"$expr является бинарной операцией"
      case _ => "Это что-то другое"

  println(match_(UnOp("-", Num(1))))
  println("-----------------------------")

  // паттерн-константа
  def describe(x: Any) =
    x match
      case 5 => "пять"
      case true => "правда"
      case "hello" => "привет!"
      case Nil => "пустой список"
      case _ => "что-то другое"

  println(describe(5))
  println(describe(true))
  println(describe("hello"))
  println(describe(Nil))
  println(describe(List(1, 2, 3)))
  println("--------------------------------")

  // паттерн-переменная
  def matchVariable(x: Any) =
    x match
      case 0 => "нуль"
      case somethingElse => s"не нуль $somethingElse"

  println(matchVariable("кек"))
  println("--------------------------------")

  // паттерны-конструкторы
  def matchConstructor(expr: Expr) =
    expr match
      case BinOp("+", e, Num(0)) => "глубокое соответствие"
      case _ => ""

  println(matchConstructor(BinOp("+", Var("x"), Num(0))))
  println("-----------------------------")

  // паттерны-последовательности
  def matchSequence(xs: List[Int]) =
    xs match
      case List(0, _, _*) => "найдено"
      case _ => "не найдено"

  println(matchSequence(List(0, 1)))
  println(matchSequence(List(0)))
  println(matchSequence(List(1, 2)))
  println("--------------------------")

  // паттерны-кортежи
  def matchTuple(obj: Any) =
    obj match
      case (a, b, c) => s"соответствует $a$b$c"
      case _ => ""

  println(matchTuple((1, '+', 2)))
  println("-------------------------------")

  // типизиованные паттерны
  def generalSize(x: Any) =
    x match
      case s: String => s.length
      case m: Map[_, _] => m.size
      case _ => -1

  println(generalSize("abc"))
  println(generalSize(Map(1 -> 'a', 2 -> 'b')))
  println(generalSize(math.Pi))
  println("--------------------------")

  // затирание типов
  def isStringArray(x: Any) =
    x match
      // типизированным может быть только массив!
      case a: Array[String] => "да"
      case _ => "нет"

  println(isStringArray(Array("abc")))
  println(isStringArray(Array(1, 2, 3)))
  println("-------------------------------")

  // привязка переменных
  def matchBinding(expr: Expr) =
    expr match
      case UnOp("abs", e @ UnOp("abs", _)) => e
      case _ =>

  println(matchBinding(UnOp("abs", UnOp("abs", Var("x")))))
  println("-----------------------------")

  // ограждение паттерна
  def simplifyAdd(e: Expr) =
    e match
      case BinOp("+", x, y) if x == y => BinOp("*", x, Num(2))
      case _ => e

  println(simplifyAdd(BinOp("+", Var("x"), Var("x"))))
  println("---------------------------------")

  def simplifyAll(expr: Expr): Expr =
    expr match
      case UnOp("-", UnOp("-", e)) => simplifyAll(e)
      case BinOp("+", e, Num(0)) => simplifyAll(e)
      case BinOp("*", e, Num(1)) => simplifyAll(e)
      case UnOp(op, e) => UnOp(op, simplifyAll(e))
      case BinOp(op, l, r) => BinOp(op, simplifyAll(l), simplifyAll(r))
      case _ => expr

  // запечатанный Expr
  def describe(e: Expr): String =
    (e: @unchecked) match
      case Num(_) => "число"
      case Var(_) => "переменная"

  // паттерны в определениях переменных
  val myTuple = (123, "abc")
  val (number, string) = myTuple
  println(number + ", " + string)

  val exp = BinOp("*", Num(5), Num(2))
  val BinOp(op, left, right) = exp
  println(s"BinOp($op, $left, $right)")

  println("------------------------------------")
  // последовательности вариантов в качестве частичных функций (partial functions)

  val withDefault: Option[Int] => Int =
    case Some(x) => x
    case None => 0

  println(withDefault(Some(10)))
  println(withDefault(None))

  val second: PartialFunction[List[Int],Int] =
    case x :: y :: _ => y

  println(second.isDefinedAt(List(1, 2, 3)))
  println(second.isDefinedAt(List()))

  println("--------------------------------")
  // паттерны в выражениях for

  val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

  for (country, city) <- capitals do
    println(s"Столицей $country является $city")

  val results = List(Some("apple"), None, Some("orange"))
  for Some(fruit) <- results do
    println(fruit)

  println("--------------------------------------")


}
