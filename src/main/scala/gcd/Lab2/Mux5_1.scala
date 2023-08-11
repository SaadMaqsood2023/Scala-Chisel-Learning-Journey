package Lab2
import chisel3 . _
import chisel3.util._


class Mux5_1 extends Bundle {
    val s0 = Input ( Bool () )
    val s1 = Input ( Bool () )
    val s2 = Input ( Bool () )
    val out = Output ( UInt (32. W ) )
    }
class Mux5_1_1 extends Module {
    val io = IO (new Mux5_1 )
    // Start coding here
    // End your code here

    var select = Cat(io.s0 , io.s1)  // s0 is becoming the last bit
    val select1 = Cat(select, io.s2)
    io.out := MuxLookup(select1, false.B, Array(
        (0.U) -> 0.U,
        (1.U) -> 8.U,
        (2.U) -> 16.U,
        (3.U) -> 24.U,
        (4.U) -> 32.U
    ))
}