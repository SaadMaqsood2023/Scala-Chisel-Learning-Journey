package Lab6 

// Up - down counter example
import chisel3 . _
import chisel3 . util . _

class Up_Down_Counter ( val max : Int = 10) extends Module {
    val io = IO (new Bundle {
    val out = Output ( UInt ( log2Ceil ( max ) . W ) )
    val up_down = Input ( Bool () )
    })
    // Start code here

    // When up_down is false count decrements otherwise when true it increments
    // The count will be equal to zero when updown is false and also count is max
    val count = RegInit(0.U ( log2Ceil( max ).W ) )
    val count_buffer =  Mux(io.up_down === false.B && count === 0.U, max.U, 
                        Mux(io.up_down === true.B && count === max.U, 0.U, 
                        Mux(io.up_down === true.B, count + 1.U, count - 1.U) ) )

    count := count_buffer
    io.out := count
    // End your code here
}