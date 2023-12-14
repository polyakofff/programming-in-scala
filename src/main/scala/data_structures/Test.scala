package data_structures

import scala.collection.mutable

object Test extends App {

  val treap = new Treap

  println(treap.insert(3, 30))
  println(treap.insert(2, 20))
  println(treap.insert(1, 10))
  println(treap.sumOn(1, 2))
  
  val map = mutable.TreeMap.empty[Int, Int]
  
  

}
