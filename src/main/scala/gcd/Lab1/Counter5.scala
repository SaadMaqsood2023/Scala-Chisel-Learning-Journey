package Lab1

import chisel3._
import chisel3.util._
import java.io.File

class Counter5 ( n : Int ) extends Module {
    val io = IO (new Bundle {
    val data_in = Input ( UInt ( n.W ) )
    // val reload = Input( Bool())
    val out = Output( Bool())
    })
    io.out := 0.B
    // val counter = RegInit (0.U(n.W))
    var count = RegInit(0.U(n.W))
    val max_count = RegInit(n.U(n.W))
    var reload = RegInit(false.B)
    

    def genCounter(n : Int , max : UInt, reload1 : Bool) = {

        
        var reload2 = reload1
        
        when(reload2 === 0.B)
        {
            when(count === max )
            {
                io.out :=  true.B
                count := count - 1.U
                reload2 := true.B
            }.otherwise{
                io.out := false.B
                count := count + 1.U
            }
            
        }.elsewhen(reload2 === 1.B)
        {
            when(count === 0.U)
            {
                io.out:= true.B
                count := count + 1.U
                reload2 := false.B
            }.otherwise{
                io.out := false.B
                count := count - 1.U
            }

            // io.out := true.B
            // count := count + 1.U
            // reload := true.U
        }
        
    }
    genCounter(n, max_count, reload)

}

// package PracticeChiselCode

// import chisel3._
// import chisel3.util._
// import java.io.File

// class Counter5 ( n : Int ) extends Module {
//   val io = IO (new Bundle {
//     val data_in = Input ( UInt ( n.W ) )
//     val out = Output( Bool())
//   })

//   io.out := false.B // Initialize output to false

//   val count = RegInit(0.U(n.W))
//   val max_count = RegInit(n.U(n.W))
//   val reload = RegInit(false.B)


//   when(!reload) {
//     when(count === max_count) {
//       io.out :=  true.B
//       count := count - 1.U
//       reload := true.B
//     }.otherwise {
//       io.out := false.B
//       count := count + 1.U
//     }
//   }.otherwise {
//     when(count === 0.U) {
//       io.out := true.B
//       count := count + 1.U
//       reload := false.B
//     }.otherwise {
//       io.out := false.B
//       count := count - 1.U
//     }
//   }
// }
