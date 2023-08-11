package Lab2

import chisel3._
import chisel3.util._

class Mux_Imp_Io extends Bundle {

    val in_1 = Input(UInt(32.W))
    val in_2 = Input(UInt(32.W))
    val sel = Input(Bool())
    val out = Output(UInt(32.W))

}

class Mux2_1 extends Module {
    val io = IO(new Mux_Imp_Io)

    // io.out := Mux(io.sel, io.in_2, io.in_1)
    val duplicate1 = Fill(32, io.sel)
    // val duplicate2 = Fill(32, sel)
    io.out := (~duplicate1 & io.in_1)  |  (duplicate1 & io.in_2)
}


