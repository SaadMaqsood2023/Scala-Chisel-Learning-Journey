package Lab3

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Decoder_Wrap_tester extends FreeSpec with ChiselScalatestTester
{
    "ALU Tester" in {
        test(new Decoder_Wrap) { a => 
            // a.io.in.poke("b0000010100000000".U)
            a.io.in.poke("b01".U)
            // a.io.alu_Op.poke(0.U)
            a.clock.step(4)

            // a.io.out.expect("b1100".U)
            a.io.out.expect("b0010".U)
        }
    }
}