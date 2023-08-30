package Lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Adder_Test extends FreeSpec with ChiselScalatestTester
{
    "Adder Test " in {
        test(new Adder(4)){ a =>  
            a.io.in0.poke(2.U)
            a.io.in1.poke(3.U)
            
            a.io.sum.expect(5.U)
        }
    }
}