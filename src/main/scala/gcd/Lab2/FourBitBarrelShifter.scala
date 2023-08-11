package Lab2
import chisel3 . _
import chisel3.util._


class FourBitBarrelShifter extends Bundle {
    val i0 = Input ( Bool () )
    val i1 = Input ( Bool () )
    val i2 = Input ( Bool () )
    val i3 = Input ( Bool () )

    val sel0 = Input ( Bool ())
    val sel1 = Input ( Bool ())

    val shift_type = Input( Bool ())

    val out1 = Output ( Bool () )
    val out2 = Output ( Bool () )
    val out3 = Output ( Bool () )
    val out4 = Output ( Bool () )
}
class FourBitShifter extends Module {
    val io = IO (new FourBitBarrelShifter )
    // Start coding here
    // End your code here

    var select = Cat(io.sel1, io.sel0)
    // val select1 = Cat(select, io.s2)
    io.out1 := MuxLookup(select, false.B, Array(
        0.U -> io.i0,
        1.U -> io.i1, 
        2.U -> io.i2,
        3.U -> io.i3
    ))

    io.out2 := MuxLookup(select, false.B, Array(
        0.U -> io.i1,
        1.U -> io.i2, 
        2.U -> io.i3,
        // 3.U -> io.i3 
        3.U -> MuxLookup(io.shift_type, false.B, Array(
            (0.B) -> 0.B,
            (1.B) -> io.i0
        ))
    ))

    io.out3 := MuxLookup(select, false.B, Array(
        0.U -> io.i2,
        1.U -> io.i3, 
        // 2.U -> io.i2,
        2.U -> MuxLookup(io.shift_type, false.B, Array(
            (0.B) -> 0.B,
            (1.B) -> io.i0
        )),

        // 3.U -> io.i3 
        3.U -> MuxLookup(io.shift_type, false.B, Array(
            (0.B) -> 0.B,
            (1.B) -> io.i1
        ))
    ))

    io.out4 := MuxLookup(select, false.B, Array(
        0.U -> io.i3,
        // 1.U -> io.i1,
        1.U -> MuxLookup(io.shift_type, false.B, Array(
            (0.B) -> 0.U,
            (1.B) -> io.i0
        )), 
        // 2.U -> io.i2,
        2.U -> MuxLookup(io.shift_type, false.B, Array(
            (0.B) -> 0.U,
            (1.B) -> io.i1
        )),
        // 3.U -> io.i3
        3.U -> MuxLookup(io.shift_type, false.B, Array(
            (0.B) -> 0.U,
            (1.B) -> io.i2
        ))
    ))



    // io.out := MuxLookup(select1, false.B, Array(
    //     (0.U) -> 0.U,
    //     (1.U) -> 8.U,
    //     (2.U) -> 16.U,
    //     (3.U) -> 24.U,
    //     (4.U) -> 32.U
    // ))
}