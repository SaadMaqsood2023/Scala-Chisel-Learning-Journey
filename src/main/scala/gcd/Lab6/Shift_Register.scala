package Lab6

// shift register example
import chisel3._
import chisel3.util._

class Shift_Register extends Module {
    val io = IO (new Bundle {
    // val in = Input ( Bool () )
    val in = Input(UInt(4.W))
    val out1 = Output ( UInt (1.W) )
    val out2 = Output ( UInt (1.W) )
    val out3 = Output ( UInt (1.W) )
    val out4 = Output ( UInt (1.W) )
})
// One logical error was that if we add 0.U to register then 
// in the next cycle new values will be added to the previous values
//  val temp = RegInit( 0.U(1.W))
//  temp := temp + io.in(0)

 val state1 = Reg ( UInt(1. W) )
 state1 := io.in(0) 
//  state1 := state1 + io.in(0) 
 val state2 = Reg( UInt (1. W) ) 
 state2 := io.in(1) 
//  state2 := state2 + io.in(1) 
 val state3 = Reg( UInt (1. W) ) 
 state3 := io.in(2) 
//  state3 := state3 + io.in(2) 
 val state4 = Reg( UInt (1. W) ) 
 state4 := io.in(3) 
//  state4 := state4 + io.in(3) 

// serial data in at LSB
// val nextState = ( state << 1) | io.in

// state := nextState  // Updating the state
// io.out := state


    // Register value cannot be assigned to bool ie io.out(0) := state1

    io.out1 := state1
    io.out2 := state2
    io.out3 := state3
    io.out4 := state4
}