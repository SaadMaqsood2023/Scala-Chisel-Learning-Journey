package Lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Mux_Type_Parameter_Test extends FreeSpec with ChiselScalatestTester
{
    "Mux Type Parameter with Bundles" in {
        test(new Mux_Type_Parameter(UInt(4.W))){ a =>   // Write the data type and width of inputs, not the data itself
            a.io.in1.poke(6.U)
            a.io.in2.poke(12.U)
            a.io.sel.poke(1.B)
            a.io.out.expect(6.U)
        }
    }
}