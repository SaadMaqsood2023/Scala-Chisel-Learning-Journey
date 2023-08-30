package Lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Vector_op_Output_Test extends FreeSpec with ChiselScalatestTester
{
    "Vector Output Putting Resultant" in {
        test(new Vector_op_Output(4, UInt(4.W) ) (_ + _) ){ a =>  
            a.io.in(0).poke(2.U)
            a.io.in(1).poke(3.U)
            a.io.in(2).poke(4.U)
            a.io.in(3).poke(5.U)
            // a.io.sel.poke(1.B)
            a.clock.step(1)
            a.io.out(2).expect(9.U)
            a.io.out(1).expect(7.U)
            a.io.out(0).expect(5.U)
        }
    }
}