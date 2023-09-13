package DataPathSSP

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Top_Test extends FreeSpec with ChiselScalatestTester
{
    "Top Test" in {
        test(new Top){ a =>   // Write the data type and width of inputs, not the data itself
            
            a.io.ins.poke("h00500213".U) // Dummy value
            a.clock.step(22)
            a.io.out.expect(5.S)   // Value being expected from the txt file
            
            
        }
    }
}