package DataPathSSP

// parameterized memory
import chisel3 . _
import chisel3 . util . _

class DMEM (val size : Int = 256 , val width : Int = 32) extends Module {
    
    val io = IO (new Bundle {
    // val dataIn = Input ( UInt ( width . W ) )
    // val dataOut = Output ( SInt ( width . W ) )
    val addr = Input ( UInt ( log2Ceil ( size ) . W ) )
    val rd_enable = Input ( Bool () )
    val wr_enable = Input ( Bool () )
    val mask   = Input(Vec(4, Bool()) )
    val dataIn = Input(Vec(4, UInt(width.W)) )
    val dataOut = Output(Vec(4, UInt(width.W)) )

    })
    val Sync_memory = SyncReadMem ( size , UInt ( width.W ) )
    // memory write operation
    when ( io.wr_enable ) {
        Sync_memory.write ( io.addr , io.dataIn, io.mask )
    }

    io . dataOut := (Sync_memory.read ( io.addr , io.rd_enable ) )
    // io . dataOut := Sync_memory.read ( io.addr )
}