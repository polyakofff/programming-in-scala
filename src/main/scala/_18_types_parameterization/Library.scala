package _18_types_parameterization

// Publication => String является подтипом Book => AnyRef

class Publication(val title: String)
class Book(title: String) extends Publication(title)

object Library {
  val books: Set[Book] =
    Set(
      Book("Programming in Scala"),
      Book("Walden")
    )

  def printBookList(info: Book => AnyRef) =
    for book <- books do println(info(book))
}

object Customer {
  def getTitle(p: Publication): String = p.title
  
  def main(args: Array[String]): Unit =
    Library.printBookList(getTitle)
}
