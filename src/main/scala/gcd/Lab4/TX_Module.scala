// package Lab4

// import chisel3._
// import chisel3.util._

// object States_TX
// {
//     val idle = 0.U
//     val tx = 1.U
//     val Valid1 = 2.U
// }
// import States_TX._

// class TX_Module extends Module
// {
//     val io = IO(new Bundle {
//         val valid = Output(Bool())
//         val ready = Input(Bool())
//         val data = Output(UInt(4.W))
//     })

//     val curr_state = RegInit(0.U)
//     val nxt_state = RegInit(0.U)

//     Switch(curr_state)
//     {
//         is(idle)
//         {
            
//         }
//     }


// }