package _4_classes_and_objects

import scala.collection.mutable

class ChecksumAccumulator:
  private var sum = 0

  def add(a: Byte): Unit = sum += a

  def checksum(): Int = ~(sum & 0xFF) + 1


object ChecksumAccumulator:
  private val cache = mutable.Map.empty[String, Int]

  def calculate(s: String): Int =
    if cache.contains(s) then
      cache(s)
    else
      val acc = new ChecksumAccumulator
      for c <- s do
        acc.add((c >> 8).toByte)
        acc.add(c.toByte)
      val cs = acc.checksum()
      cache += s -> cs
      cs
