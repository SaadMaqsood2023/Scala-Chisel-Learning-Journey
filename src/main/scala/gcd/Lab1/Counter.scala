package Lab1

import chisel3._
import chisel3.util._
import scala.language.postfixOps

class Counter( counterBits : UInt) extends Module {

        val io = IO(new Bundle {
            val result = Output ( Bool() )
        })
        val max = (1.U << counterBits) - 1.U
        val count = RegInit (0.S (16.W))

        when( count === max.asSInt ){
            count := 0.S
        }.otherwise {
            count := count + 1.S
        }
        io.result := count
        println(s" Counter created with max value $max ")
}



// object Main extends App{
    
//     // val exe_count = Module(new Counter(3.U))
//     // println(new chisel3.stage.ChiselStage)
//     println((new chisel3.stage.ChiselStage).emitVerilog(new Counter(1.U)))
// }

// A plan scala code it will run when you write the command "sbt run" and only the opened file will run
// object Counter extends App {
//     println("Hello Chisel World!")
// }