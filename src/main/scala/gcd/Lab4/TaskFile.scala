// is code mai batao error kiya hai...  package Task
// import chisel3._
// import chisel3.util._
//  class Interface extends Bundle {
//     val in_data = Input (UInt(6.W))
//     val valid = Output (Bool())
//     val out_data = Output (UInt(6.W ) )
//     val Ready = Input (Bool())
//    }
//  class tx_interface extends Bundle {
//     val tx_rx = Input ( UInt (6. W ) )
//     val rx_tx = Output ( UInt (6. W ) )
//    }
//  class Top_module extends Module {
//     val io = IO ( new Interface )
//     val TX = Module ( new tx )
//     val RX = Module ( new rx )
// // connecting top with TX = > same direction , same name connects
//     io <> TX.io.top_int
// // connecting TX with RX = > opposite direction , same name connects
//     TX.io.Tx1 <> RX.io
//     }
//   class tx extends Module {
//     val io = IO ( new Bundle {
//     val top_int = new Interface
//     val Tx1 = new tx_interface
//    })
//     io.Tx1.rx_tx := io.top_int.in_data
//     io.top_int.valid := true.B
//     io.top_int.out_data := io.Tx1.tx_rx
//    }
//   class rx extends Module {
//     val io = IO ( Flipped ( new tx_interface ) )
//     val stateReg = RegInit(idle)
//     val dataReg = Reg(UInt(32.W))
//     // val idle = 0.U
//     // val processing = 0.U
//     // val done = 0.U
//     val idle :: processing :: done :: Nil = Enum(3)
  
//      switch(stateReg) {
//        is(idle) {
//        dataReg := io.rx_tx + 16.U
//          when(io.rx_tx === 63.U) {
//           stateReg := processing
//         }
//        }
//        is(processing) {
//          dataReg := dataReg - 1.U
//           when(dataReg === 0.U) {
//            stateReg := done
//           }
//         }
//        is(done) {
//          stateReg := idle
//         }
//       }
  
//      io.tx_rx := dataReg
//   }