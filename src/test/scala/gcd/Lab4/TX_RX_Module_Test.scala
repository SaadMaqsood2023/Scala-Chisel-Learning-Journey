// package Lab4

// import chisel3._
// import chisel3.tester._
// import org.scalatest.FreeSpec
// import chisel3.experimental.BundleLiterals._

// class TX_RX_Module_Test extends FreeSpec with ChiselScalatestTester
// {
//     "TX and RX Module" in {
//         test(new TX_RX_Module) { a =>
//             // a.io.tx_in.tx.poke(1.U)
//             a.io.tx_in.ready.poke(0.B)
//             a.io.rx_in.valid.poke(0.B)
//             a.io.tx_in.tx.poke(1.U)
//             a.io.rx_in.busy.poke(0.U)
//             a.io.rx_in.data.poke(7.U)
//             a.clock.step(4)  
//             a.io.tx_in.data.expect(7.U)  // at 4 clock cycle data will be transferred
            

//         }
//     }
// }