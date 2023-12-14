package _12_packages_imports_exports

case class PosInt(value: Int):
  require(value > 0)
  export value.{<< as shl, >> as shr, >>> as _, *}
