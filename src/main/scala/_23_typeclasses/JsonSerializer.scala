package _23_typeclasses

trait JsonSerializer[T] {
  def serialize(o: T): String
  
  extension (a: T)
    def toJson: String = serialize(a)
}

object JsonSerializer {
  given stringSerializer: JsonSerializer[String] with
    override def serialize(s: String) = s"\"$s\""
    
  given intSerializer: JsonSerializer[Int] with
    override def serialize(n: Int) = n.toString

  given longSerializer: JsonSerializer[Long] with
    override def serialize(n: Long) = n.toString

  given booleanSerializer: JsonSerializer[Boolean] with
    override def serialize(b: Boolean) = b.toString
    
  given listSerializer[T](using JsonSerializer[T]): JsonSerializer[List[T]] with
    override def serialize(ts: List[T]) =
      s"[${ts.map(t => t.toJson).mkString(", ")}]"
}

object ToJsonMethods {
  extension [T](a: T)(using jser: JsonSerializer[T])
    def toJson: String = jser.serialize(a)
}
