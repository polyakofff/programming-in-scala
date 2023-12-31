package _18_types_parameterization

class Queue[+T] private(private val leading: List[T],
                       private val trailing: List[T]) {
  private def mirror =
    if leading.isEmpty then
      new Queue(trailing.reverse, Nil)
    else
      this

  def head = mirror.leading.head

  def tail =
    val q = mirror
    new Queue(q.leading.tail, q.trailing)

  // нижний ограничитель
  def enqueue[U >: T](x: U) =
    new Queue(leading, x :: trailing)
}

object Queue {
  def apply[T](xs: T*) = new Queue[T](xs.toList, Nil)
}
