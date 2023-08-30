package Lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Router_Test extends FreeSpec with ChiselScalatestTester
{
    "Router Test" in {
        test(new Router(UInt(4.W))){ a =>  
            // a.io.in.poke(4.U)
            // a.io.address_in.poke(4.U)
            // a.io.valid_in.poke(1.B)

            // a.clock.step(1)
            // a.ib.out.expect(4.U)
            // a.ib.address.expect(4.U)
            // a.ib.valid.expect(1.B)
            a.io.transactionIn.in.poke(4.U)
            a.io.transactionIn.address_in.poke(4.U)
            a.io.transactionIn.valid_in.poke(1.B)

            a.clock.step(1)
            a.io.transactionOut.out.expect(4.U)
            a.io.transactionOut.address.expect(4.U)
            a.io.transactionOut.valid.expect(1.B)

            
            
        }
    }
}