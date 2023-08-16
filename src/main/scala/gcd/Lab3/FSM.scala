// package Lab3 

// import chisel3._
// import chisel3.util._

// // Inputs and Outputs
// class FSM_Input extends Bundle
// {  
//     val opcode  = Input(UInt(3.W))
//     val phase  = Input(UInt(3.W))
//     val zero    = Input(Bool())
//     val rst     = Input(Bool())

//     val mem_rd  = Output(Bool())
//     val load_ir = Output(Bool())
//     val halt    = Output(Bool())
//     val inc_pc  = Output(Bool())
//     val load_ac = Output(Bool())
//     val load_pc = Output(Bool())
//     val mem_wr  = Output(Bool())

// }

// // defining states
// object FSM_States_Ins
// {
//     val INST_ADDR   = 0.U(3.W)
//     val INST_FETCH  = 1.U(3.W)
//     val INST_LOAD   = 2.U(3.W)
//     val IDLE        = 3.U(3.W)
//     val OP_ADDR     = 4.U(3.W)
//     val OP_FETCH    = 5.U(3.W)
//     val ALU_OP      = 6.U(3.W)
//     val STORE       = 7.U(3.W)

//     val hlt     = 0.U(3.W)
//     val skz     = 1.U(3.W)
//     val add     = 2.U(3.W)
//     val and     = 3.U(3.W)
//     val xor     = 4.U(3.W)
//     val lda     = 5.U(3.W)
//     val sto     = 6.U(3.W)
//     val jmp     = 7.U(3.W)
// }
// import FSM_States_Ins._

// class FSM extends Module 
// {
//     val io = IO(new FSM_Input) 

//     val mem_rd  = 0.B
//     val load_ir = 0.B
//     val halt    = 0.B
//     val inc_pc  = 0.B
//     val load_ac = 0.B
//     val load_pc = 0.B
//     val mem_wr  = 0.B

//     when(io.phase === INST_ADDR)  // comparing the states
//     {
//         val mem_rd  = 0.B
//         val load_ir = 0.B
//         val halt    = 0.B
//         val inc_pc  = 0.B
//         val load_ac = 0.B
//         val load_pc = 0.B
//         val mem_wr  = 0.B
//     }.elsewhen(io.phase === INST_FETCH)
//     {
//         val mem_rd  = 1.B
//         val load_ir = 0.B
//         val halt    = 0.B
//         val inc_pc  = 0.B
//         val load_ac = 0.B
//         val load_pc = 0.B
//         val mem_wr  = 0.B
//     }.elsewhen(io.phase === INST_LOAD)
//     {
//         val mem_rd  = 1.B
//         val load_ir = 0.B
//         val halt    = 0.B
//         val inc_pc  = 0.B
//         val load_ac = 0.B
//         val load_pc = 0.B
//         val mem_wr  = 0.B
//     }.elsewhen(io.phase === IDLE)
//     {
//         val mem_rd  = 1.B
//         val load_ir = 0.B
//         val halt    = 0.B
//         val inc_pc  = 0.B
//         val load_ac = 0.B
//         val load_pc = 0.B
//         val mem_wr  = 0.B
//     }.elsewhen(io.phase === OP_ADDR)
//     {
//         val mem_rd  = 0.B
//         val load_ir = 0.B
//         val halt    = 0.B
//         val inc_pc  = 0.B
//         val load_ac = 0.B
//         val load_pc = 0.B
//         val mem_wr  = 0.B
//     }.elsewhen(io.phase === OP_FETCH)
//     {
//         when(io.opcode === add || io.opcode === and || io.opcode === xor || io.opcode === lda)
//         {
//             val mem_rd  = 1.B
//         }

//         val load_ir = 0.B
//         val halt    = 0.B
//         val inc_pc  = 0.B
//         val load_ac = 0.B
//         val load_pc = 0.B
//         val mem_wr  = 0.B
//     }.elsewhen(io.phase === ALU_OP)
//     {
//         // val mem_rd  = 0.B
//         when(io.opcode === add || io.opcode === and || io.opcode === xor || io.opcode === lda)
//         {
//             val mem_rd  = 1.B
//         }
//         val load_ir = 0.B
//         val halt    = 0.B
//         val inc_pc  = 0.B
//         val load_ac = 0.B
//         val load_pc = 0.B
//         val mem_wr  = 0.B
//     }.elsewhen(io.phase === STORE)
//     {
//         // val mem_rd  = 0.B
//         when(io.opcode === add || io.opcode === and || io.opcode === xor || io.opcode === lda)
//         {
//             val mem_rd  = 1.B
//         }
//         val load_ir = 0.B
//         val halt    = 0.B
//         val inc_pc  = 0.B
//         val load_ac = 0.B
//         val load_pc = 0.B
//         val mem_wr  = 0.B
//     }





    
    
// }