package Lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Emux_Inc_Typecast_Test extends FreeSpec with ChiselScalatestTester
{
    "Emux" in {
        test(new Emux_Inc_Typecast(4)){ a =>   // Write the data type and width of inputs, not the data itself
            a.io.input1.poke(6.U)
            a.io.input2.poke(12.U)
            a.io.select.poke(1.B)
            a.io.output.expect(6.S)
        }
    }
}