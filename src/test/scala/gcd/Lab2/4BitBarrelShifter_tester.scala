package Lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class FourBitBarrelShifter_tester extends FreeSpec with ChiselScalatestTester
{
    "4 Bit Barrel Shifter" in {
        test(new FourBitShifter){ a =>

            a.io.i0.poke(1.B)  // 1100
            a.io.i1.poke(1.B)
            a.io.i2.poke(0.B)
            a.io.i3.poke(0.B)

            a.io.sel0.poke(1.B)
            a.io.sel1.poke(0.B)

            a.io.shift_type.poke(0.B)  // this is used to preserve only the sign

            a.io.out1.expect(1.B)
            a.io.out2.expect(0.B)  // doing left shift = 1 will make 1000, with type = 0
            a.io.out3.expect(0.B)
            a.io.out4.expect(0.B)
        }
    }
}