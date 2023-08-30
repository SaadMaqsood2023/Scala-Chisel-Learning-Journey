package Lab5

import chisel3._
import chisel3.util._

// import chisel3.iotesters.{ ChiselFlatSpec , Driver , PeekPokeTester }

class Transac_in [T <: Data](length : T) extends Bundle 
{
    val in = Input(length)
    val address_in = Input(UInt(10.W))
    val valid_in = Input(Bool())
}

class Transac_out [T <: Data](length : T) extends Bundle 
{
    val out = Output(length)
    val address = Output(UInt(10.W))
    val valid = Output(Bool())
}

class Router [ T <: Data ]( gen : T ) extends Module {
    
    val io = IO( new Bundle {
        val transactionIn = new Transac_in(gen)
        // new Transac_in(gen)
        val transactionOut = new Transac_out(gen)
    }
    )
    // val ib = IO(new Transac_out(gen))

    // ib.out := io.in
    // ib.address := io.address_in
    // ib.valid := io.valid_in
    io.transactionOut.out := io.transactionIn.in
    io.transactionOut.address := io.transactionIn.address_in
    io.transactionOut.valid := io.transactionIn.valid_in

}