package Lab1

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Counter_Tester extends FreeSpec with ChiselScalatestTester
{
    "Counter Class" in {
        test(new Counter(4.U)) { a =>
            a.clock.step(14)
            a.io.result.expect(true.B)
            

        }
    }
}