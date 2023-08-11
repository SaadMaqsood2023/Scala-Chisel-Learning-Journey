package Lab2

import chisel3._
import chisel3.util._

class Encoder4_2 extends Module {

    val io = IO(new Bundle {
        val in1 = Input( Bool())
        val in2 = Input( Bool())
        val in3 = Input( Bool())
        val in4 = Input( Bool())
        val sel1 = Input( UInt(2.W))
        val sel2 = Input( UInt(2.W))

        val out1 = Output( Bool())
        val out2 = Output( Bool())


    })
    //Select bits width should be larger otherwise it will give error
    io.out1 := Mux1H(io.sel1, Seq(io.in1, io.in2) )
    io.out2 := Mux1H(io.sel2, Seq(io.in3, io.in4) )
}