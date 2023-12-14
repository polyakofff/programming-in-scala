package _20_abstract_members

abstract class CurrentZone {
  
  type Currency <: AbstractCurrency
  def make(x: Long): Currency

  abstract class AbstractCurrency {
    val amount: Long
    def designation: String

    def +(that: Currency): Currency =
      make(this.amount + that.amount)

    def *(x: Double): Currency =
      make((this.amount * x).toLong)

    def -(that: Currency): Currency =
      make(this.amount - that.amount)

    def /(x: Double): Currency =
      make((this.amount / x).toLong)

    def from(other: CurrentZone#AbstractCurrency): Currency =
      make(math.round(
        other.amount.toDouble * Converter.exchangeRate
          (other.designation)(this.designation)
      ))
      
    private def decimals(n: Long): Int =
      if (n == 1) 0 else 1 + decimals(n / 10)

    override def toString: String =
      ((amount.toDouble / CurrencyUnit.amount.toDouble)
        .formatted(s"%.${decimals(CurrencyUnit.amount)}f")
        + " " + designation)
  }
  
  val CurrencyUnit: Currency
}

object US extends CurrentZone {
  abstract class Dollar extends AbstractCurrency:
    override def designation = "USD"

  override type Currency = Dollar

  override def make(cents: Long) =
    new Dollar:
      override val amount = cents
      
  val Cent = make(1)
  val Dollar = make(100)
  
  override val CurrencyUnit = Dollar
}

object Europe extends CurrentZone {
  abstract class Euro extends AbstractCurrency:
    override def designation = "EUR"

  override type Currency = Euro

  override def make(cents: Long) = 
    new Euro:
      override val amount = cents
      
  val Cent = make(1)
  val Euro = make(100)
  
  override val CurrencyUnit = Euro
}

object Converter {
  val exchangeRate =
    Map(
      "USD" -> Map("USD" -> 1.0, "EUR" -> 0.92),
      "EUR" -> Map("USD" -> 1.09, "USD" -> 1.0)
    )
}
