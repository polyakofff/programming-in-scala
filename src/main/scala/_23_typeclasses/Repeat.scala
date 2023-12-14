package _23_typeclasses

val errMsg =
  "Please, enter a word, a positive integer count, and\n" +
  "a mood (one of 'angry', 'surprised', 'neutral')"

enum Mood {
  case Surprised, Angry, Neutral
}

object Mood {

  import scala.util.CommandLineParser.FromString

  given moodFromString: FromString[Mood] with
    override def fromString(s: String): Mood =
      s.trim.toLowerCase match
        case "angry" => Mood.Angry
        case "surprised" => Mood.Surprised
        case "neutral" => Mood.Neutral
        case _ => throw new IllegalArgumentException(errMsg)
}

@main def repeat(word: String, count: Int, mood: Mood) =
  val msg =
    if count > 0 then
      val words = List.fill(count)(word.trim)
      val punc =
        mood match
          case Mood.Angry => "!"
          case Mood.Surprised => "?"
          case Mood.Neutral => ""
      val sep = punc + " "
      words.mkString(sep) + punc
    else errMsg

  println(msg)
