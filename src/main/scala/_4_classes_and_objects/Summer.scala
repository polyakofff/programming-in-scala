package _4_classes_and_objects

import ChecksumAccumulator.calculate

object Summer {

  def main(args: Array[String]): Unit =
    for arg <- args do
      println(arg + ": " + calculate(arg))

}
