package _12_packages_imports_exports

import _12_packages_imports_exports.p.Sub

object Main extends App {

  val s = new Sub

  println("--------------------------")

  val x = PosInt(13)
  println(x)
  println(x shl 1)
  println(x shr 1)

}
