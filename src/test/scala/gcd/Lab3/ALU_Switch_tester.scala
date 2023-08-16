package Lab3

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class ALU_Switch_tester extends FreeSpec with ChiselScalatestTester
{
    "ALU Tester" in {
        test(new ALU_Switch) { a => 
            a.io.in_A.poke("b1000".U)
            a.io.in_B.poke("b0100".U)
            a.io.alu_Op.poke(0.U)
            a.clock.step(4)

            a.io.out.expect("b1100".U)
            a.io.sum.expect("b1100".U)
        }
    }
}