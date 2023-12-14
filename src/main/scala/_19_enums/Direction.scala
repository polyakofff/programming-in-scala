package _19_enums

enum Direction(val degrees: Int) {

  def invert: Direction =
    this match
      case North => South
      case East => West
      case South => North
      case West => East

  case North extends Direction(0)
  case East extends Direction(90)
  case South extends Direction(180)
  case West extends Direction(270)
}

object Direction {
  
  def nearestTo(degrees: Int): Direction =
    val rem = degrees % 360
    val angle = if rem < 0 then rem + 360 else rem
    val (ne, se, sw, nw) = (45, 135, 225, 315)
    angle match
      case a if a > nw || a <= ne => North
      case a if a > ne && a <= se => East
      case a if a > se && a <= sw => South
      case a if a > sw && a <= nw => West
}