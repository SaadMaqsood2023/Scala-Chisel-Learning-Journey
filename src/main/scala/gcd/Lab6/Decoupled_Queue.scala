package Lab6

import chisel3._
import chisel3.util._

class Decoupled_Queue extends Module {

    val io = IO ( new Bundle {
    
    val in = Flipped ( Decoupled ( UInt (8. W ) ) ) // valid = Input , ready =
    // Output , bits = Input
    
    val out = Decoupled ( UInt (8. W ) ) // valid = Output , ready =
    // Input , bits = Output

    val out1 = Decoupled( UInt (8.W ) )

    })

    



    val queue = Queue ( io . in , 2) // 5 - element queue
    io.out <> queue
    io.out1 <> queue
}