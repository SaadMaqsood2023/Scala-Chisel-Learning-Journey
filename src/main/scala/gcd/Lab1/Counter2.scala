package Lab1

import chisel3._
import chisel3.util._
import scala.language.postfixOps

class Counter2( counterBits : UInt) extends Module {

        val io = IO(new Bundle {
            val result = Output ( Bool() )
        })
        val max = (1.U << counterBits) - (1.U << (counterBits - 1.U))
        val count = RegInit (0.S (16.W))

        when( count === max.asSInt ){
            count := 0.S
        }.otherwise {
            count := count + 1.S
        }
        io.result := count
        println(s" Counter created with max value $max ")
}



