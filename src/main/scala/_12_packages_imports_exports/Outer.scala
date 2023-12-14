package _12_packages_imports_exports

class Outer {

  class Inner {
    private def f = "f"

    class InnerMost {
      // работает
      println(f)
    }
  }
  // не работает
//  println(f)
}
