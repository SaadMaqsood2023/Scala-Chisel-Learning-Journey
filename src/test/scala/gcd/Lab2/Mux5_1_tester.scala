package Lab2 

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Mux5_1_tester extends FreeSpec with ChiselScalatestTester
{
    "Mux 5_1 Tester" in {
        test(new Mux5_1_1){ a =>
            a.io.s0.poke(1.B)
            a.io.s1.poke(0.B)
            a.io.s2.poke(0.B)
            a.clock.step(3)

            a.io.out.expect(32.U)

        }
    }
}