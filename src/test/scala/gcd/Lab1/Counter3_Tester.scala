package Lab1

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Counter3_Tester extends FreeSpec with ChiselScalatestTester
{
    "Counter3 Class" in {
        test(new Counter3(8, 5.U)) { a =>
            a.clock.step(10)
            //a.io.result.expect(true.B)

        }
    }
}