package _7_builtin_control_structures

import java.io.File
import java.net.{MalformedURLException, URL}
import scala.io.StdIn.readLine

object Main extends App {


  // цикл while
  var a = 4
  var b = 6
  while a != 0 do
    val temp = a
    a = b % a
    b = temp
  println(b)

  println("----------------------------")

  // цикл do-while
  while
    val line = readLine()
    println(s"Read line: $line")
    line != ""
  do ()

  println("------------------------------")

  // for
  val filesHere = new File(".\\src\\main\\scala\\_7_builtin_control_structures").listFiles

  for file <- filesHere do
    println(file)


  for file <- filesHere
    if file.isFile
    if file.getName.endsWith(".scala")
  do println(file)


  println("----------------------------")

  def fileLines(file: java.io.File) =
    val lines = scala.io.Source.fromFile(file).getLines().toArray
    lines

  def grep(pattern: String) =
    for
      file <- filesHere if file.getName.endsWith(".scala")
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches(pattern)
    do println(s"$file: ${trimmed}")

  grep(".*pattern.*")

  println("------------------------------")

  def scalaFiles =
    for
      file <- filesHere
      if file.getName.endsWith(".scala")
    yield file

  println(scalaFiles.mkString(" "))

  println("--------------------------")

  val forLineLengths =
    for
      file <- filesHere
      if file.getName.endsWith(".scala")
      line <- fileLines(file)
      trimmed = line.trim
      if trimmed.matches(".*for.*")
    yield trimmed.length

  println(forLineLengths.mkString(" "))

  println("---------------------------------------")

  // исключения
  def half(n: Int) =
    if n % 2 == 0 then
      n / 2
    else
      throw new IllegalArgumentException("n must be even")

  def urlFor(path: String) =
    try new URL(path)
    catch case e: MalformedURLException => new URL("https://scala-lang.org")

  def f(): Int = try 1 finally return 2
  def g(): Int = try 1 finally 2

  println(f())
  println(g())

  println("------------------------------")

  // match
  val day = "Tuesday"

  val num =
    day match
      case "Monday" => 1
      case "Tuesday" => 2
      case "Wednesday" => 3
      case "Thursday" => 4
      case "Friday" => 5
      case "Saturday" => 6
      case "Sunday" => 7
      case _ => -1

  println(num)

  println("----------------------------")

  val x = 1
  if x == 1 then
    val x = 2
    println(x)
  println(x)

  println("---------------------------------")

  def makeRowSeq(row: Int) =
    for col <- 1 to 10 yield
      val prod = (row * col).toString
      val padding = " " * (4 - prod.length)
      prod + padding

  def makeRow(row: Int) = makeRowSeq(row).mkString

  def multiTable() =
    val tableSeq =
      for row <- 1 to 10
        yield makeRow(row)

    tableSeq.mkString("\n")

  println(multiTable())

  println("--------------------------------")


}
