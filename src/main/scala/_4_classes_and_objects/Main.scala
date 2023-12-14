package _4_classes_and_objects

object Main extends App {
  //  println(ChecksumAccumulator.calculate("Roses are red, violets are blue"))


  var p = Person("lev", 21)
  println(p)

  p = p.appendToName(" Polyakov")
  println(p)
}
