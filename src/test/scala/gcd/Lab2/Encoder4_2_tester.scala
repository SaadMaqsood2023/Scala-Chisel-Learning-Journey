package Lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Encoder4_2_tester extends FreeSpec with ChiselScalatestTester
{
    "Nested Mux Look up" in {
        test(new Encoder4_2){ a =>
            a.io.in1.poke(0.B)
            a.io.in2.poke(0.B)
            a.io.in3.poke(0.B)
            a.io.in4.poke(0.B)
            

            a.io.sel1.poke(1.U)
            a.io.sel2.poke(1.U)
            // a.io.sel3.poke(1.B)
            a.io.out1.expect(0.B)
            a.io.out2.expect(0.B)
        }
    }
}