package Lab3

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Encoder4_2_Switch_tester extends FreeSpec with ChiselScalatestTester
{
    "Encoder 4-2 Tester" in {
        test(new Encoder4_2_Switch) { a => 
            a.io.in.poke("b1000".U)
            a.clock.step(2)

            a.io.out.expect("b11".U)
        }
    }
}