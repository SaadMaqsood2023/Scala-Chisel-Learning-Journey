package Lab3

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Imm_Gen_tester extends FreeSpec with ChiselScalatestTester
{
    "ALU Tester" in {
        test(new Imm_Gen) { a => 
            a.io.instr.poke("b0000010100000000".U)
            // a.io.in_B.poke("b0100".U)
            // a.io.alu_Op.poke(0.U)
            a.clock.step(4)

            // a.io.out.expect("b1100".U)
            a.io.immd_se.expect("b0000000000000101".U)
        }
    }
}