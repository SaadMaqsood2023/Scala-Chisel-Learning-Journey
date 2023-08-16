package Lab3

import chisel3 . _
import chisel3 . util . _
// import chisel3.iotesters. {
//     ChiselFlatSpec, Driver, PeekPokeTester
// }

class LM_IO_Interface_decoder_with_valid extends Bundle {

    val in = Flipped(Valid( UInt (2. W ) ) )
    val out = Valid ( UInt (4. W ) ) 
}
class Decoder_Wrap extends Module {
    val io = IO (new LM_IO_Interface_decoder_with_valid )
// Start coding here
// End coding here

    io.out := 0.U

    switch(io.in.valid)
    {
        is("b00".U)
        {
            io.out := RegNext("b0001".U)
        }
        is("b01".U)
        {
            io.out := RegNext("b0010".U)
        }
        is("b10".U)
        {
            io.out := RegNext("b0100".U)
        }
        is("b11".U)
        {
            io.out := RegNext("b1000".U)
        }

    }
}