package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Counter_with_xor_Test extends FreeSpec with ChiselScalatestTester
{
    "Counter with XOR" in {
        test(new Counter_with_xor(12)){ a =>   // Write the data type and width of inputs, not the data itself
            
            
            // a.io.in.poke(3.U)
            a.clock.step(10)
            a.io.out.expect("b01".U)

        }
    }
}