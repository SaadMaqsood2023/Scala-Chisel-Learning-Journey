package Lab4

import chisel3._
import chisel3.tester._
import chisel3.util._

import org.scalatest._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
import chiseltest.experimental.TestOptionBuilder._

import scala.util.Random


class Imm_Gen_Test extends FreeSpec with ChiselScalatestTester {

    "Imm Generation Test Lab4" in {

        test ( new Lab3.Imm_Gen ) { c =>  

            for ( i <- 0 until 10) {
                // val ins32 = Random.nextInt ()    
                val ins32 = (Random.nextLong () & 0xFFFFFFFFL)
                
                // Getting last 12 Bits of Instruction for getting immediate (Was getting LSB)
                // val ranImm = ins32 & 0xFFFL

                // Now Getting MSB 12 Bits
                val ranImm = (ins32 >> 20) & 0xFFFL

                // We were selecting the last Bit or Least Signifant Number
                // val lastbitnum  = (ranImm & 1) // Select the last bit of a number

                // Now selecting last bit from 32 bit number (as an example)
                // val number: Long = 0x12345678L // Example number

                // Calculate the mask for the most significant bit
                // val msbMask: Long = 1L << 31 // For a 32-bit number

                // Use bitwise AND to select the most significant bit
                // val msb = (number & msbMask) != 0

                // println("Most Significant Bit: " + msb)


                val msbMask = 1L << 11 // Masking for a 12 Bit number

                // val msbMask = (ranImm >> 11) & 1 // Another way to Extract MSB

                val lastbitnum1 = (ranImm & msbMask) != 0 // Getting MSB of 12 bit in boolean
                val lastbitnum = if(lastbitnum1) 1 else 0 // Converting boolean to Int
                // val lastbit : Boolean = (lastbitnum != 0)

                // Extending the last bit 20 times  (This method is not working)
                // val ext_num : Long= if(lastbitnum1) {
                    // (lastbitnum.toLong << 20-1  )
                // }
                // else{
                        // lastbitnum.toLong << 20 
                    // }

                // Extending the last bit
                val ext_num = if (lastbitnum1) {
                (ranImm | 0xFFFFF000L) // Set the upper bits to 1
                } else {
                ranImm
                }

                // This method also not working
                // val result = ext_num.toString + ranImm.toString  // concatenating
                // val result = (ext_num + ranImm).toString
                // val result1 = (result.toLong & 0xFFFFFFFFL).toInt
                // val result1 = result.toInt

                c.io.instr.poke(ins32.U)
                c.clock.step(2)
                c.io.immd_se.expect( ext_num.U)
            }
            c.clock.step(2)
        }
    }
}
