package Lab3

import chisel3 . _
import chisel3 . util . _

class LM_IO_Interface_ImmdValGen extends Bundle {

    val instr = Input ( UInt (16. W ) )
    val immd_se = Output ( UInt (16. W ) )
}

class Imm_Gen extends Module {

    val io = IO (new LM_IO_Interface_ImmdValGen )

    val imm_12 = io.instr(15, 8)
    val last_bit = Fill(8, imm_12(7))   // last bit extended
    io.immd_se := Cat(last_bit, imm_12)  //
    // Start coding here
    // End your code here
    // Well , you can actually write classes too . So , technically you have no limit ; )
}
