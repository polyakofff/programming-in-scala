package _21_givens

class PrefferedPrompt(val preference: String)
class PrefferedDrink(val preference: String)

object Greater {
  def greet(name: String)(using prompt: PrefferedPrompt, 
                          drink: PrefferedDrink) =
    println(s"Welcome, $name. The system is ready.")
    print("But while you work, ")
    println(s"why not enjoy a cup of ${drink.preference}?")
    println(prompt.preference)
}

object JillsPrefs {
  given prompt: PrefferedPrompt =
    PrefferedPrompt("Your wish> ")
  given drink: PrefferedDrink =
    PrefferedDrink("tea")
}

object TomsPrefs {
  val favoriteColor = "blue"
  def favoriteFood = "steak"
  given prompt: PrefferedPrompt =
    PrefferedPrompt("enjoy> ")
  given drink: PrefferedDrink =
    PrefferedDrink("red wine")
  given prefPromptOrd: Ordering[PrefferedPrompt] with
    override def compare(x: PrefferedPrompt, y: PrefferedPrompt): Int =
      x.preference.compareTo(y.preference)
  given prefDrinkOrd: Ordering[PrefferedDrink] with
    override def compare(x: PrefferedDrink, y: PrefferedDrink): Int =
      x.preference.compareTo(y.preference)
  
}
