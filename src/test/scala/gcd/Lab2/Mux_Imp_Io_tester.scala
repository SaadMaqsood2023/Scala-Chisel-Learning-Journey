package Lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FlatSpec
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Mux_Imp_Io_tester extends FreeSpec with ChiselScalatestTester
{
    "Mux Implementation 2 to 1" in {

        test(new Mux2_1){ a =>
            a.io.in_1.poke(11.U)
            a.io.in_2.poke(10.U)
            a.io.sel.poke(0.B)
            a.clock.step(2)
            a.io.out.expect(11.U)

        }
    }
}