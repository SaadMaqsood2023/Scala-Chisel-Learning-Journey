package Lab1

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Counter5_Tester extends FreeSpec with ChiselScalatestTester
{
    "Counter5 Class" in {
        test(new Counter5(10)) { a =>
            a.clock.step(30)
            //a.io.result.expect(true.B)

        }
    }
}