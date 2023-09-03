package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Shift_Register_Test extends FreeSpec with ChiselScalatestTester
{
    "Shift Register" in {
        test(new Shift_Register){ a =>   // Write the data type and width of inputs, not the data itself
            
            
            a.io.in.poke(3.U)
            a.clock.step(1)
            a.io.out1.expect("b1".U)
            a.io.out2.expect("b1".U)
            a.io.out3.expect("b0".U)
            a.io.out4.expect("b0".U)


            
            // Logic for SIPO

            // a.io.in.poke(1.B)
            // a.clock.step(1)
            // a.io.out.expect("b0011".U)

            // a.io.in.poke(0.B)
            // a.clock.step(1)
            // a.io.out.expect("b0110".U)

            // a.io.in.poke(1.B)
            // a.clock.step(1)
            // a.io.out.expect("b1101".U)

            // a.io.in.poke(0.B)
            // a.clock.step(1)
            // a.io.out.expect("b1010".U)
        }
    }
}