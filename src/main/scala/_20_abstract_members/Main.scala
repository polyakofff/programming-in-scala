package _20_abstract_members

object Main extends App {

  trait Abstract {
    type T
    def transform(x: T): T
    val initial: T
    var current: T
  }

  class Concrete extends Abstract {
    type T = String
    def transform(x: String) = x + x
    val initial = "hi"
    var current = initial
  }

  // инициализация абстрактных val-переменных
  trait RationalTrait {
    val numerArg: Int
    val denomArg: Int
    require(denomArg != 0)
  }

  // ошибка (denomArg = 0)
  val x = 1
//  val r = new RationalTrait:
//    val numerArg: Int = 2
//    val denomArg: Int = 3

  // параметрические поля трейтов
  trait RationalTrait2(val numerArg: Int, val denomArg: Int) {
    require(denomArg != 0)
  }

  new RationalTrait2(1 * x, 2 * x) {}

  object TwoThirds extends RationalTrait2(2, 3)

  class RationalClass(n: Int, d: Int) extends RationalTrait2(n, d)

  // ленивые val-переменные
  object Demo {
    val x = { println("initializing x..."); "done" }
  }

  println(Demo)
  println(Demo.x)

  object DemoLazy {
    lazy val x = { println("initializing x..."); "done" }
  }

  println(DemoLazy)
  println(DemoLazy.x)

  println("------------------------------------------")

  trait LazyRationalTrait {

    val numerArg: Int
    val denomArg: Int

    lazy val numer = numerArg / g
    lazy val denom = denomArg / g

    private lazy val g =
      require(denomArg != 0)
      gcd(numerArg, denomArg)

    private def gcd(a: Int, b: Int): Int =
      if b == 0 then a else gcd(b, a % b)

    override def toString: String = s"$numer/$denom"
  }

  val y = 2
  val r = new LazyRationalTrait:
    override val numerArg: Int = 1 * y
    override val denomArg: Int = 2 * y

  println(r)

  println("----------------------------------------")

  // абстрактные типы
  class Food

  abstract class Animal {
    type SuitableFood <: Food

    def eat(food: SuitableFood): Unit
  }

  class Grass extends Food

  class Cow extends Animal {
    type SuitableFood = Grass

    override def eat(food: Grass) = {}
  }

  class Fish extends Food

//  val bessy: Animal = new Cow
//  bessy.eat(new Fish)
//  bessy.eat(new Grass)

  // типы, зависящие от пути
  class DogFood extends Food
  class Dog extends Animal {
    type SuitableFood = DogFood

    override def eat(food: DogFood) = {}
  }

  val bessy = new Cow
  val lassie = new Dog
  // ошибка
//  lassie.eat(new bessy.SuitableFood)

  val bootsie = new Dog
  // норм
  lassie.eat(new bootsie.SuitableFood)

  class Outer:
    class Inner

  val o1 = new Outer
  val o2 = new Outer

  // можно
  val i1 = new o1.Inner
  // нельзя
//  val i2 = new Outer#Inner

  // уточняющие типы
  class Pasture {
    var animals: List[Animal { type SuitableFood = Grass }] = Nil
  }

  println("---------------------------------------")

  val dollars = US.Dollar.from(US.Dollar * 100)
  val euros = Europe.Euro.from(dollars)
  println(dollars)
  println(euros)


}
