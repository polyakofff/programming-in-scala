package _16_mutable_objects

import _16_mutable_objects.Main.MySimulation.*

object Main extends App {

  object MySimulation extends CircuitSimulation {
    override def InverterDelay: Int = 1
    override def AndGateDelay: Int = 3
    override def OrGateDelay: Int = 5
  }

  val input1, input2, sum, carry = new Wire

  probe("sum", sum)
  probe("carry", carry)

  halfAdder(input1, input2, sum, carry)

  input1 setSignal true

  run()

  input2 setSignal true

  run()

}
