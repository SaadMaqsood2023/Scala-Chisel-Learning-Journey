package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Up_Down_Counter_Test extends FreeSpec with ChiselScalatestTester
{
    "Up Down counte with updown pin" in {
        test(new Up_Down_Counter){ a =>   // Write the data type and width of inputs, not the data itself
            
            
            a.io.up_down.poke(0.B)
            a.clock.step(11)
            a.io.out.expect("h0".U)

        }
    }
}