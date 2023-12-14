package data_structures

import scala.annotation.tailrec
import scala.util.Random

final class Node(val x: Int, val y: Double, val value: Int, var left: Node = null, var right: Node = null) {
  var sum: Int = value
}

class Treap {

  private var root: Node = null
  private val rand = new Random

  private def sum(t: Node) =
    if t != null then t.sum else 0

  private def pulled(t: Node): Node =
    t.sum = t.value + sum(t.left) + sum(t.right)
    t

  private def merge(l: Node, r: Node): Node =
    if l == null then r
    else if r == null then l
    else if l.y > r.y then
      l.right = merge(l.right, r)
      pulled(l)
    else
      r.left = merge(l, r.left)
      pulled(r)

  private def split(t: Node, x: Int): (Node, Node) =
    if t == null then (null, null)
    else if t.x <= x then
      val (l, r) = split(t.right, x)
      t.right = l
      (pulled(t), r)
    else
      val (l, r) = split(t.left, x)
      t.left = r
      (l, pulled(t))

  @tailrec private def max(t: Node): Int =
    if t.right == null then t.x
    else max(t.right)

  def insert(x: Int, value: Int): Boolean =
    val (l, r) = split(root, x)
    if l != null && max(l) == x then
      false
    else
      val node = Node(x, rand.nextDouble(), value)
      root = merge(merge(l, node), r)
      true

  def erase(x: Int): Boolean =
    val (m, r) = split(root, x)
    val (l, node) = split(m, x - 1)
    root = merge(l, r)
    node != null

  @tailrec private def find(t: Node, x: Int): Node =
    if t == null then null
    else
      if x < t.x then find(t.left, x)
      else if x > t.x then find(t.right, x)
      else t

  def get(x: Int): Option[Int] =
    find(root, x) match
      case null => None
      case node => Some(node.value)

  def sumOn(x1: Int, x2: Int): Int =
    val (lm, r) = split(root, x2)
    val (l, m) = split(lm, x1 - 1)
    val res = sum(m)
    root = merge(merge(l, m), r)
    res

}
