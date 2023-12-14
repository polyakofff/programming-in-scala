package _10_composition_and_inheritance

import Element.elem

object Main extends App {

  val el1: Element = elem(Vector("hello", "world"))
  println(el1)

  val el2: Element = elem("abracadabra")
  println(el2)

  val el3: Element = elem('a', 2, 2)
  println(el3)

  println(elem(Vector("hello")) above elem("world!"))

  println(elem(Vector("one", "two")) beside elem("one"))

//  class Kek(set: collection.mutable.HashSet[String]) {
//    def get = set
//    def f = set.size
//    val v = set.size
//    println("end of constructor")
//  }
//
//  val k: Kek = Kek(collection.mutable.HashSet("hey"))
//  k.get += "there"
//
//  println(k.f)
//  println(k.v)

}
