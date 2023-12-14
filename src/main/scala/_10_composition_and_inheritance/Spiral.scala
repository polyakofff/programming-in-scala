package _10_composition_and_inheritance

import Element.elem

object Spiral {

  def spiral(n: Int, dir: Int): Element =
    if n == 1 then
      elem("*")
    else
      val sp = spiral(n - 1, (dir + 1) % 4)
      dir match
        case 0 =>
          elem('-', n, 1) above (elem(' ', 1, n - 1) beside sp)
        case 1 =>
          (elem(' ', n - 1, 1) above sp) beside elem('|', 1, n)
        case 2 =>
          (sp beside elem(' ', 1, n - 1)) above elem('-', n, 1)
        case 3 =>
          elem('|', 1, n) beside (sp above elem(' ', n - 1, 1))

  def main(args: Array[String]) =
    val sp = spiral(20, 3)
    println(sp)

}
