package Lab6

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Decoupled_Queue_Test extends FreeSpec with ChiselScalatestTester
{
    "Decoupled Queue Test" in {
        test(new Decoupled_Queue){ a =>
            
            a.io.in.valid.poke(true.B)
            a.io.in.bits.poke(5.U)
            
            a.clock.step(1)
            
            // a.io.in.ready.expect(true.B)
            // a.io.out.bits.expect(5.U)
            
            a.io.in.valid.poke(true.B)
            a.io.in.bits.poke(7.U)
            
            a.clock.step(1)
            //litToBoolean converts literal scala numbers to boolean type.
            while (!a.io.out.valid.peek().litToBoolean) {
                a.clock.step(1) // Advance the simulation by 1 clock cycle
            }
            val firstElement = a.io.out.bits.peek().litValue().toInt
            a.io.out.ready.poke(true.B)

            // a.io.in.ready.expect(true.B)
            // a.io.out.bits.expect(7.U)
            a.io.out.bits.expect(firstElement.U)

            while (!a.io.out.valid.peek().litToBoolean) {
                a.clock.step(1) // Advance the simulation by 1 clock cycle
            }
            val SecondEle = a.io.out.bits.peek().litValue().toInt
            a.io.out.ready.poke(true.B)
            a.io.out.bits.expect(SecondEle.U)
            
        

        }
    }
}