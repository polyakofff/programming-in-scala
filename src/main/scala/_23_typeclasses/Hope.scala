package _23_typeclasses

enum Hope[+T] {
  case Glad(o: T)
  case Sad
}

object HopeUtils {

  given hopeOrdering[T](using ord: Ordering[T]): Ordering[Hope[T]] with
    def compare(lh: Hope[T], rh: Hope[T]): Int =
      import Hope.{Glad, Sad}
      (lh, rh) match
        case (Sad, Sad) => 0
        case (Sad, _) => 1
        case (_, Sad) => -1
        case (Glad(lhv), Glad(rhv)) => ord.compare(lhv, rhv)
        
}

