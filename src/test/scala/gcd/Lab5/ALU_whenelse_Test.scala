package Lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class ALU_whenelse_Test extends FreeSpec with ChiselScalatestTester
{
    "ALU with When Else" in {
        test(new ALU_whenelse(4)){ a => 
            a.io.alu_oper.poke(6.U)
            a.io.arg_x.poke(12.U)
            a.io.arg_y.poke(4.U)
            a.io.alu_out.expect(8.U)
        }
    }
}