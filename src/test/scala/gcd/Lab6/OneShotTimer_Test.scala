package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class  OneShotTimer_Test extends FreeSpec with ChiselScalatestTester
{
    "One-Shot Timer Test" in {
        test(new OneShotTimer(4)){ a =>   // Write the data type and width of inputs, not the data itself
            
            
            a.io.din.poke(3.U)
            a.clock.step(5)
            a.io.out.expect(0.B)

            

        }
    }
}