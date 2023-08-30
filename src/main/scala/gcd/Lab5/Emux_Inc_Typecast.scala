package Lab5

import chisel3._
import chisel3.util._

class Emux_Inc_Typecast ( gen : Int ) extends Module {
    val io = IO (new Bundle {
        val output = Output ( SInt(gen.W) )
        val input1 = Input ( UInt(gen.W) )
        val input2 = Input ( UInt(gen.W) )
        val select = Input ( Bool () )
})
    val out = (Mux2_to_1 ( (io.input2).asSInt , (io.input1).asSInt , io . select ))
    io.output := out.asSInt  // Donot directly type cast

    def Mux2_to_1 ( in_0 :SInt , in_1 :SInt , sel : Bool ) : SInt = {
    Mux( sel, in_1, in_0)
    // Mux( sel, in_1.asSInt, in_0.asSInt)
    }

}
