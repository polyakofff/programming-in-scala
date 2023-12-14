package _13_pattern_matching

import _10_composition_and_inheritance.Element
import _10_composition_and_inheritance.Element.elem

sealed trait Expr
case class Var(name: String) extends Expr
case class Num(number: Double) extends Expr
case class UnOp(operator: String, arg: Expr) extends Expr
case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

class ExprFormatter {

  private val opGroups = Vector(
    Set("+", "-"),
    Set("*")
  )
  private val prior = {
    val opsToGroups =
      for i <- opGroups.indices
          op <- opGroups(i)
        yield op -> i
    opsToGroups.toMap
  }
  private val unaryPrior = prior.size
  private val fractionPrior = -1

  private def format(e: Expr, p: Int): Element =
    e match
      case Var(name) =>
        elem(name)
      case Num(number) =>
        def withoutDot(s: String) =
          if s.endsWith(".0") then s.substring(0, s.length - 2)
          else s

        elem(withoutDot(number.toString))
      case UnOp(op, arg) =>
        elem(op) beside format(arg, unaryPrior)
      case BinOp("/", left, right) =>
        val top = format(left, fractionPrior)
        val bottom = format(right, fractionPrior)
        val line = elem('-', top.width.max(bottom.width), 1)
        val frac = top above line above bottom
        if p != fractionPrior then frac
        else elem(" ") beside frac beside elem(" ")
      case BinOp(op, left, right) =>
        val opPrior = prior(op)
        val l = format(left, p)
        val r = format(right, p + 1)
        val oper = l beside elem(" " + op + " ") beside r
        if opPrior >= p then oper
        else elem("(") beside oper beside elem(")")

  def format(e: Expr): Element = format(e, 0)
}

object ExprTest {

  def main(args: Array[String]): Unit =
    val f = new ExprFormatter

    val e1 = BinOp("*", BinOp("/", Num(1), Num(2)),
      BinOp("+", Var("x"), Num(1)))
    val e2 = BinOp("+", BinOp("/", Var("x"), Num(2)),
      BinOp("/", Num(1.5), Var("x")))
    val e3 = BinOp("/", e1, e2)

    println(s"${f.format(e1)}\n\n")
    println(s"${f.format(e2)}\n\n")
    println(s"${f.format(e3)}\n\n")

    val e4 = BinOp("+", BinOp("+", Var("x"), Var("y")), Var("z"))
    println(s"${f.format(e4)}\n\n")


}
