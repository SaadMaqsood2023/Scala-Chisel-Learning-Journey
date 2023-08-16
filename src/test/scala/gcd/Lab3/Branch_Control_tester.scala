package Lab3

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Branch_Control_tester extends FreeSpec with ChiselScalatestTester
{
    "Branch Tester" in {
        test(new Branch_Control) { a => 
            a.io.branch.poke(1.B)
            a.io.fnct3.poke("b000".U)
            a.io.arg_x.poke(24.U)
            a.io.arg_y.poke(24.U)
            a.clock.step(5)

            a.io.br_taken.expect(1.B)
        }
    }
}