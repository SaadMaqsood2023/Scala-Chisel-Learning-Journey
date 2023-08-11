package Lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Mux8_1_tester extends FreeSpec with ChiselScalatestTester
{
    "Nested Mux Look up" in {
        test(new Mux8_1){ a =>
            a.io.in0.poke(0.B)
            a.io.in1.poke(0.B)
            a.io.in2.poke(0.B)
            a.io.in3.poke(0.B)
            a.io.in4.poke(1.B)
            a.io.in5.poke(0.B)
            a.io.in6.poke(1.B)
            a.io.in7.poke(1.B)

            a.io.sel1.poke(2.U)
            a.io.sel2.poke(1.U)
            // a.io.sel3.poke(1.B)
            a.io.out.expect(1.B)
        }
    }
}