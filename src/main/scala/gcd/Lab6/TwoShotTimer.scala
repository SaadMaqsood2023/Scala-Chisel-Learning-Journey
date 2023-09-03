package Lab6

import chisel3._
import chisel3.util._
// one shot timer implementation
class TwoShotTimer  extends Module {

    val io = IO (new Bundle {
        val din = Input(UInt(8.W))
        val out = Output(Bool())
        // val reload2 = Output(Bool())
        // val out = Output ( UInt ( log2Ceil ( max ).W ) )
    })


val timer_count = RegInit (0.U (8. W ) )

val done = timer_count === 0.U
val max = timer_count === io.din

val next = WireInit (0. U )

val reload = done
val reload2 = RegInit(false.B)  // Use Register other your value will remain same in the next cycle
                                // Also wire the register 
io.out := false.B
// when ( reload || !max ) {    // This logic further improved below
// when ( !max ) {              // But with this logic it will rotate b/w max and max-1
when ( !reload2) {
    next := timer_count + 1.U
    
    // when(done)                   // the timer_count has not been updated here till this line 
    // when(timer_count === 0.U)    // and we are trying to use updated timer_count with done
    when(reload)
    {
        io.out := true.B
    }
    when(next === io.din)
    {           
        reload2 := true.B           // with := "cannot reassign variable" error will come
    }
}
// .elsewhen (max || !reload) {     // Logic further improved below
// .elsewhen ( !reload ) {          // But with this logic it will rotate b/w max and max-1
.otherwise{
    next := timer_count - 1. U   // decrement the timer
    // done := timer_count === 0.U
    // when(max)                        // the timer_count has not been updated here till this line
    // when(timer_count === io.din)     // and we are trying to use updated timer_count with max
                                        // upper condition was using previous value of timer_count
    when(max)
    {
        io.out := true.B
    }
    when(next === 0.U)                  
    {
        reload2 := false.B               // with := "cannot reassign variable" error will come
    }
}

    timer_count := next // update the timer

}