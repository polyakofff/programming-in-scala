package _6_functional_objects

class Rational(n: Int, d: Int) extends Ordered[Rational]:

  require(d != 0)

  private val g = gcd(n.abs, d.abs)
  val numer: Int = n / g
  val denom: Int = d / g

  def this(n: Int) = this(n, 1)

  def +(that: Rational): Rational =
    Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom
    )

  def +(i: Int): Rational =
    Rational(numer + i * denom, denom)

  def -(that: Rational): Rational =
    Rational(
      numer * that.denom - that.numer * denom,
      denom * that.denom
    )

  def -(i: Int): Rational =
    Rational(numer - i * denom, denom)

  def *(that: Rational): Rational =
    Rational(numer * that.numer, denom * that.denom)

  def *(i: Int): Rational =
    Rational(numer * i, denom)

  def /(that: Rational): Rational =
    Rational(numer * that.denom, denom * that.numer)

  def /(i: Int): Rational =
    Rational(numer, denom * i)

  def lessThan(that: Rational) =
    numer * that.denom < that.numer * denom

  def max(that: Rational): Rational =
    if lessThan(that) then that else this

  override def toString: String = s"$numer/$denom"

  override def compare(that: Rational): Int =
    (this.numer * that.denom) - (that.numer * this.denom)

  private def gcd(a: Int, b: Int): Int =
    if b == 0 then a else gcd(b, a % b)


object Rational:

  def apply(n: Int, d: Int = 1) =
    new Rational(n, d)
