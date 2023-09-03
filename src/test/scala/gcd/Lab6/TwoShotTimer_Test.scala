package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class  TwoShotTimer_Test extends FreeSpec with ChiselScalatestTester
{
    "Two-Shot Timer Test" in {
        test(new TwoShotTimer){ a =>   // Write the data type and width of inputs, not the data itself
            
            a.io.din.poke(4.U)
            a.clock.step(12)
            a.io.out.expect(1.B)
            

        }
    }
}