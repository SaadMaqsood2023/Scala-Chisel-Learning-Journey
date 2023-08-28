// package Lab4 

// import chisel3._
// import chisel3.util._


// object States_TX_RX
// {
//     val idle = 0.U
//     val tx = 1.U
//     val Valid1 = 2.U
// }


// class TX_Interface extends Bundle 
// {
//     val ready = Input ( Bool () )
//     val valid = Output ( Bool () )

//     val data = Output ( UInt(4.W) )
//     val tx = Input(UInt(1.W))

// }

// class RX_Interface extends Bundle 
// {
//     val ready = Output( Bool() )
//     val valid = Input( Bool() )

//     val data = Input( UInt(4.W) )
//     val busy = Input(UInt(1.W))
// }

// // class TX_Module extends Module
// // {
// //     val io = IO(new Bundle { 
// //         val TX_int = new TX_RX_Interface
// //         val tx = Input(UInt(1.W))
// //     })

// // }

// // class RX_Module extends Module
// // {
// //     val io = IO(new Bundle { 
// //         val RX_int = Flipped(new TX_RX_Interface)
// //         val busy = Input(UInt(1.W))
// //     })

// // }

// import States_TX_RX._

// class TX_RX_Module extends Module
// {
//     //  val ready = Output( Bool() )
//     // val valid = Input( Bool() )

//     // val data = Input( UInt(4.W) )
//     // val state = RegInit(0.U(2.W))

//     val io = IO(new Bundle {
//         val tx_in = new TX_Interface
//         val rx_in = new RX_Interface
//     })

//     val state = RegInit(0.U(2.W))

//     // var temp_ready = false.B(Bool())  
//     // var temp_valid = false.B(Bool())
//     // var temp_data = (0.U(7.W))

//     // var temp_ready = RegInit(false.B )
//     // var temp_valid = RegInit(false.B)
//     // var temp_data = RegInit(0.U(7.W))

//     io.tx_in.valid := 0.B
//     io.rx_in.ready := 0.B  
//     io.tx_in.data := 0.U 

//     // We will give the input to the inputs and output 
//     when(state === idle )
//     { 
//         io.tx_in.valid := 0.B
//         io.rx_in.ready := 0.B     // define the outputs and use them as inputs

//         // temp_ready := io.rx_in.ready
//         // io.tx_in.ready := temp_ready

//         io.rx_in.ready := io.tx_in.ready   // opposite written


//         // temp_valid := (io.tx_in.valid)
//         // io.rx_in.valid := temp_valid
//         // io.rx_in.valid := io.tx_in.valid

//         io.tx_in.valid := io.rx_in.valid

//         io.tx_in.data := 0.U               // in this state no data transferred 
//         // temp_data := (io.tx_in.data)
//         // io.rx_in.data := temp_data
//         // io.rx_in.data := io.tx_in.data

//         // io.tx_in.data := io.rx_in.data


//         state := state + 1.U          // changing the states
        
//     }.elsewhen(state === tx)
//     { 
        
//         io.tx_in.valid := 0.B
//         io.rx_in.ready := 0.B     // define the outputs and use them as inputs

//         // temp_ready := (io.rx_in.ready)
//         // io.tx_in.ready := temp_ready
//         // io.tx_in.ready := io.rx_in.ready
//         io.rx_in.ready := io.tx_in.ready
        
//         // temp_valid := (io.tx_in.valid)
//         // io.rx_in.valid := temp_valid
//         // io.rx_in.valid := io.tx_in.valid
//         io.tx_in.valid := io.rx_in.valid

//         io.tx_in.data := 0.U               // in this state no data transferred 
//         // temp_data := (io.tx_in.data)
//         // io.rx_in.data := temp_data
//         // io.rx_in.data := io.tx_in.data

//         // io.tx_in.data := io.rx_in.data

//         when(io.tx_in.tx === 1.U)      
//         {
//             // io.tx_in.tx := 0.U    //error
//             state := state + 1.U
//         }
        
//     }.elsewhen(state === Valid1)
//     {
//         io.rx_in.ready := 0.B
//         when(io.tx_in.valid === 1.B )
//         {
//             io.rx_in.ready := 1.B       // at first clock cycle the valid pin will be low then at the second cycle
//         }                               // valid pin will be high and ready pin will also become high

//         io.tx_in.valid := 1.B           // define the outputs and use them as inputs

//         // temp_ready := (io.rx_in.ready)
//         // io.tx_in.ready := temp_ready
//         // io.tx_in.ready := io.rx_in.ready

//         // io.rx_in.ready := io.tx_in.ready  // correcting while running
        
//         // temp_valid := (io.tx_in.valid)
//         // io.rx_in.valid := temp_valid
//         // io.rx_in.valid := io.tx_in.valid

//         // when(io.tx_in.valid =/= 1.B)    // correcting while running
//         // {
//         //     io.tx_in.valid := io.rx_in.valid
//         // }

       
//         when(io.tx_in.valid === 1.B && io.rx_in.ready === 1.B)
//         {
//             when(!io.rx_in.busy)
//             {
//                 io.tx_in.data := 7.U               // in this state data is not transferred if the rx module is busy
//                 // temp_data := (io.tx_in.data)
//                 // io.rx_in.data := temp_data
//                 // io.rx_in.data := io.tx_in.data
//                 io.tx_in.data := io.rx_in.data
               
//             }
//             // io.tx_in.valid := io.rx_in.valid 
//             // io.rx_in.ready := io.tx_in.ready
//             state := state - 1.U        // U = unsigned should be given
//         }
        
//     }
    
    
// }

