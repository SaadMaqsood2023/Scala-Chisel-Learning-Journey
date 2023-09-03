package Lab6

// Counter with XOR example
import chisel3 . _
import chisel3 . util . _

class Counter_with_xor ( val max : Int = 1) extends Module {
val io = IO (new Bundle {
val out = Output ( UInt (( log2Ceil ( max ) . W ) ) )
})
    // Start Coding here

    val count = RegInit(0.U( log2Ceil(max).W ) )
    // val count_buffer = RegInit(0.U ( log2Ceil(max).W ) )

    //Comparing the last bit with 1.U, so that whenever MSB is 1.U counter is again set to 0.U
    val count_buffer = Mux(count( log2Ceil(max) - 1 ) === 1.B , 0.U , count + 1.U)

    count := count_buffer
    io.out := count

}