package _9_control_abstractions

import java.io.{File, PrintWriter}

object Main extends App {

  // карринг
  def curriedSum(x: Int)(y: Int) = x + y

  val onePlus = curriedSum(1)
  println(onePlus)
  println(onePlus(2))

  println("------------------------------")

  def withPrintWriter(file: File)(op: PrintWriter => Unit) =
    val writer = new PrintWriter(file)
    try op(writer)
    finally writer.close()

  val file = new File(".\\src\\main\\scala\\_9_control_abstractions\\something.txt")
  withPrintWriter(file) { writer =>
    writer.println("writing something into something...")
  }

  println("-------------------------------------")

  // параметры, передаваемые по имени
  var assertionsEnabled = true

  def myAssert(predicate: => Boolean) =
    if assertionsEnabled && !predicate then
      throw new AssertionError

  myAssert(1 + 1 >= 2)


}
