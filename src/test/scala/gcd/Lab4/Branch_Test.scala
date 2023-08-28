package Lab4

import chisel3._
import chisel3.tester._
import chisel3.util._

import org.scalatest._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
import chiseltest.experimental.TestOptionBuilder._

import scala.util.Random



import Lab3.Branch_Op._

class Branch_Test extends FreeSpec with ChiselScalatestTester {

    "Branch Test Lab4" in {   // Bug number two inverted commas, bug number three space in the hexadecimal numbers
        // test ( new Lab3.ALU_Switch ) . withAnnotations( Seq ( VerilatorBackendAnnotation ) ) { c =>  //write the name of your own class


        test ( new Lab3.Branch_Control ) { c =>  //write the name of your own class
            // ALU operations
            val array_op = Array ( eql , n_eql , br_blt , br_bge , br_bltu , br_bgeu )   // ALU_XXX has been removed 
            // ALU_SLL , ALU_SLTU , ALU_SRL , ALU_SRA , ALU_COPY_A , ALU_COPY_B , ALU_XXX )

            for ( i <- 0 until 10) {
                val src_a = Random.nextLong () & 0xFFFFFFFFL   // long 64 bits -> 32 bits
                val src_b = Random.nextLong () & 0xFFFFFFFFL    // f -> 15 -> 1111
                val opr = Random.nextInt (6)    // 10010110 & 00001111 -> 0110
                val branch_op = array_op( opr )   
                // val branch_op = array_op( 1 )  // errors = 5,9  because in 5 src_a.toInt was not used, and in 9 src.toInt and .toLong 
                val result = branch_op match {
                    case `eql` => if(src_a.toInt === src_b.toInt) 1.B else 0.B
                    case `n_eql` => if(src_a.toInt != src_b.toInt) 1.B else 0.B      // remove spacing from = >, also use != for scala
                    case `br_blt` => if(src_a < src_b) 1.B else 0.B
                    case `br_bge` => if(src_a >= src_b) 1.B else 0.B 
                    case `br_bltu` => if(src_a < src_b) 1.B else 0.B
                    // case ALU_SLT => (src_a.toInt < src_b.toInt) //.asInstanceOf[Int]
                    case `br_bgeu` => if(src_a >= src_b) 1.B else 0.B //.asInstanceOf[Int]
                    case _ => 0.B
                }

                // Our required answer is boolean so does not need to mask the Output to 32 bits or less

                // val result1 : BigInt = if ( result < 0L)        // comparing bigIint with the 0L other wise mismatch
                // ( BigInt (0xFFFFFFFFL ) + result +1) & 0xFFFFFFFFL
                // else result & 0xFFFFFFFFL    // 1111 + 1011 + 1 = 1011 + 10000 = 11011 & 01111 =1011

                c.io.arg_x.poke(src_a.U )
                c.io.arg_y.poke(src_b.U )
                c.io.branch.poke(1.B)     // will work when the branch condition is fullfilled
                c.io.fnct3.poke(branch_op )
                c.clock.step(1)
                c.io.br_taken.expect( result)
                // c.io.br_taken.expect( result1.asUInt )
            }

            // mistakes of Shehroze
            // write all the cases in ``, second match the opcode written in the poke with above one
            // toInt and toLong not needed in ALU_SRA
            c.clock.step(2)
        }
    }
}
