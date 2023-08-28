package Lab4

import chisel3._
import chisel3.tester._
import chisel3.util._

import org.scalatest._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._
// import chiseltest._
import chiseltest.experimental.TestOptionBuilder._
// import chiseltest.internal.VerilatorBackendAnnotation
import scala.util.Random


// object ALUOP {  // Another bug make ALUOP class of the in this file
// ALU Operations , may expand / modify in future

// case, abstract, trait, object
// study all classes

    // Another bug make ALUOP class of the in this file
    // ALU Operations , may expand / modify in future
import Lab3.ALUOP._

// class TestALU ( c : Lab3.ALU_Switch ) extends FreeSpec with ChiselScalatestTester {
class TestALU extends FreeSpec with ChiselScalatestTester {

    "ALU Test" in {   // Bug number two inverted commas, bug number three space in the hexadecimal numbers
        // test ( new Lab3.ALU_Switch ) . withAnnotations( Seq ( VerilatorBackendAnnotation ) ) { c =>  //write the name of your own class


        test ( new Lab3.ALU_Switch ) { c =>  //write the name of your own class
            // ALU operations
            val array_op = Array ( ALU_ADD , ALU_SUB , ALU_AND , ALU_OR , ALU_XOR , ALU_SLT ,
            ALU_SLL , ALU_SLTU , ALU_SRL , ALU_SRA , ALU_COPY_A , ALU_COPY_B )   // ALU_XXX has been removed 
            // ALU_SLL , ALU_SLTU , ALU_SRL , ALU_SRA , ALU_COPY_A , ALU_COPY_B , ALU_XXX )

            for ( i <- 0 until 100) {
                val src_a = Random.nextLong () & 0xFFFFFFFFL   // long 64 bits -> 32 bits
                val src_b = Random.nextLong () & 0xFFFFFFFFL    // f -> 15 -> 1111
                val opr = Random.nextInt (12)    // 10010110 & 00001111 -> 0110
                val aluop = array_op( opr )   
                // val aluop = array_op( 9 )  // errors = 5,9  because in 5 src_a.toInt was not used, and in 9 src.toInt and .toLong 
                val result = aluop match {
                    case ALU_ADD => src_a + src_b
                    case ALU_SUB => src_a - src_b      // remove spacing from = >
                    case ALU_AND => src_a & src_b
                    case ALU_OR => src_a | src_b   
                    case ALU_XOR => src_a ^ src_b
                    // case ALU_SLT => (src_a.toInt < src_b.toInt) //.asInstanceOf[Int]
                    case ALU_SLT => if(src_a.toInt < src_b.toInt) 1 else 0 //.asInstanceOf[Int]
                    case ALU_SLL => src_a << ( src_b & 0x1F )
                    // case ALU_SLTU => (src_a < src_b ).asInstanceOf [Int]
                    case ALU_SLTU => if(src_a < src_b ) 1 else 0
                    case ALU_SRL => src_a >> ( src_b & 0x1F )   
                    // case ALU_SRA => (src_a) >> ( src_b & 0x1F )   // mistake was converted to int using .toInt and then .toLong
                    case ALU_SRA => (src_a.toInt).toLong >> ( src_b & 0x1F )   // converted to Long data type
                    case ALU_COPY_A => src_a
                    case ALU_COPY_B => src_b
                    case _ => 0
                }


                val result1 : BigInt = if ( result < 0L)        // comparing bigIint with the 0L other wise mismatch
                ( BigInt (0xFFFFFFFFL ) + result +1) & 0xFFFFFFFFL
                else result & 0xFFFFFFFFL    // 1111 + 1011 + 1 = 1011 + 10000 = 11011 & 01111 =1011

                c.io.in_A.poke(src_a.U )
                c.io.in_B.poke(src_b.U )
                c.io.alu_Op.poke(aluop )
                c.clock.step(2)
                c.io.out.expect( result1.asUInt )
            }

            // mistakes of Shehroze
            // write all the cases in ``, second match the opcode written in the poke with above one
            // toInt and toLong not needed in ALU_SRA
            c.clock.step(2)
        }
    }
}
