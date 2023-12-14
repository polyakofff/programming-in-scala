package _11_traits

import scala.collection.mutable.ArrayBuffer

object Main extends App {

  abstract class IntQueue {
    def get(): Int
    def put(x: Int): Unit
  }

  class BasicIntQueue extends IntQueue {
    private val buf = ArrayBuffer.empty[Int]
    override def get() = buf.remove(0)
    override def put(x: Int) = buf += x
  }

  val q = new BasicIntQueue
  q.put(1)
  q.put(2)
  println(q.get())
  println(q.get())

  trait Doubling extends IntQueue {
    abstract override def put(x: Int) = super.put(x * 2)
  }

  class MyQueue extends BasicIntQueue, Doubling

  val q2 = new MyQueue
  q2.put(10)
  println(q2.get())

  val q3 = new BasicIntQueue with Doubling
  q3.put(10)
  println(q3.get())

  trait Incrementing extends IntQueue {
    abstract override def put(x: Int) = super.put(x + 1)
  }

  trait Filtering extends IntQueue {
    abstract override def put(x: Int) =
      if x >= 0 then super.put(x)
  }

  val q4 = new BasicIntQueue with Incrementing with Filtering
  q4.put(-1)
  q4.put(0)
  q4.put(1)
  println(q4.get())
  println(q4.get())

  val q5 = new BasicIntQueue with Filtering with Incrementing
  q5.put(-1)
  q5.put(0)
  q5.put(1)
  println(q5.get())
  println(q5.get())
  println(q5.get())

  println("--------------------------------")

  // линеаризация
  class Animal:
    override def toString: String = super.toString + ". Я - животное"
  trait Furry extends Animal:
    override def toString: String = super.toString + ". Я пушистый"
  trait HasLegs extends Animal:
    override def toString: String = super.toString + ". У меня есть лапы"
  trait FourLegged extends HasLegs:
    override def toString: String = super.toString + ". У меня 4 лапы"
  class Cat extends Animal, Furry, FourLegged:
    override def toString: String = super.toString + ". Я - кот"

  val cat = new Cat
  println(cat)

  println("--------------------------------------")

  trait Philosophical(message: String) {
    def philosophize = message
  }

  class Frog extends Animal, Philosophical("Я квакаю, значит, я существую!")
  class Duck extends Animal, Philosophical("Я крякаю, значит, я существую!")

  val frog = new Frog
  println(frog.philosophize)
  val duck = new Duck
  println(duck.philosophize)

  println("-----------------------------------")

  class ProfoundAnimal extends Animal, Philosophical("В начале было дело.")

  // параметры для Philosophical не передаются
  class ProfoundFrog extends ProfoundAnimal, Philosophical

  val profoundFrog = new ProfoundFrog
  println(profoundFrog.philosophize)

  println("----------------------------------")

  trait PhilosophicalAnimal extends Animal with Philosophical

  // не работает
//   class PhilosophicalFrog extends PhilosophicalAnimal("Я квакаю, значит, я существую!")

  // работает
  class PhilosophicalFrog extends
      Philosophical("Я квакаю, значит, я существую!"),
      PhilosophicalAnimal

  val philosophicalFrog = new PhilosophicalFrog
  println(philosophicalFrog.philosophize)


}
