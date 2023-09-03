package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Optimized_Counter_Test extends FreeSpec with ChiselScalatestTester
{
    "Optimized Counter" in {
        test(new Optimized_Counter(4)){ a =>   // Write the data type and width of inputs, not the data itself
            
            
            // a.io.in.poke(3.U)
            a.clock.step(9)
            a.io.out.expect("b01".U)

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