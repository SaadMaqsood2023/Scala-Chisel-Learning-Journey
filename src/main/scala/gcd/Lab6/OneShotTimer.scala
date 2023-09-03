package Lab6

import chisel3._
import chisel3.util._
// one shot timer implementation
class OneShotTimer (val max : Int ) extends Module {

    val io = IO (new Bundle {
        val din = Input(UInt(8.W))
        val out = Output(Bool())
        // val out = Output ( UInt ( log2Ceil ( max ).W ) )
    })


val timer_count = RegInit (0.U (8. W ) )

val done = timer_count === 0. U
val next = WireInit (0. U )

val reload = done

io.out := false.B
when ( reload ) {
    next := io.din // load the data from input
    io.out := true.B
}
.elsewhen (! done ) {
    next := timer_count - 1. U // decrement the timer
    // done := timer_count === 0.U
    // when(timer_count === 0.U)
    // {
    //     io.out := true.B
    // }
}
timer_count := next // update the timer

}