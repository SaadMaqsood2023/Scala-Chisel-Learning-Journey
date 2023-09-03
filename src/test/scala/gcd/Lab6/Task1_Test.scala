package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task1_Test extends FreeSpec with ChiselScalatestTester
{
    "Optimized Counter" in {
        test(new Task1(13)){ a =>   // Write the data type and width of inputs, not the data itself
            
            
            // a.io.in.poke(3.U)
            a.clock.step(13)
            a.io.out.expect("b1101".U)

            
        }
    }
}