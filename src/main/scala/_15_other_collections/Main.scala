package _15_other_collections

import scala.collection.immutable.TreeSet
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object Main extends App {

  // ListBuffer
  val listBuf = new ListBuffer[Int]
  listBuf += 1
  listBuf += 2
  3 +=: listBuf
  println(listBuf)
  println("-----------------------------------------")

  // ArrayBuffer
  val arrayBuf = new ArrayBuffer[Int]()
  arrayBuf += 12
  arrayBuf += 15
  println(arrayBuf)
  println("-------------------------------------------")

  // to
  val colors = List("blue", "yellow", "red", "green")

  val treeSet = colors to TreeSet
  println(treeSet)

  println(treeSet.toList)
  println(treeSet.toArray.mkString(" "))

}
