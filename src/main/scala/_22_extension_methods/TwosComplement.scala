package _22_extension_methods

trait TwosComplement[N] {

  def equalsMinValue(n: N): Boolean
  def absOf(n: N): N
  def negationOf(n: N): N
}

object TwosComplement {

  given tcOfByte: TwosComplement[Byte] with
    override def equalsMinValue(n: Byte) = n == Byte.MinValue
    override def absOf(n: Byte) = n.abs
    override def negationOf(n: Byte) = (-n).toByte

  given tcOfShort: TwosComplement[Short] with
    override def equalsMinValue(n: Short) = n == Short.MinValue
    override def absOf(n: Short) = n.abs
    override def negationOf(n: Short) = (-n).toShort

  given tcOfInt: TwosComplement[Int] with
    override def equalsMinValue(n: Int) = n == Int.MinValue
    override def absOf(n: Int) = n.abs
    override def negationOf(n: Int) = -n

  given tcOfLong: TwosComplement[Long] with
    override def equalsMinValue(n: Long) = n == Long.MinValue
    override def absOf(n: Long) = n.abs
    override def negationOf(n: Long) = -n
}
