package Lab5

import chisel3._
import chisel3.util._

// class definition with function as parameter
class Vector_op_Output [ T <: Data ]( n : Int , generic : T ) ( op : (T , T ) => T ) extends Module {

    require ( n > 0) // " reduce only works on non - empty Vecs "
    val io = IO (new Bundle {
        val in = Input ( Vec (n , generic ) )
        val out = Output (Vec(n-1 , generic)  )
        // select pin for selecting output
        // val sel = Input(Bool())
    })
    // Assigning resultant Bits to corresponding outputs
    for(i <- 0 until n - 1)
    {
        io.out(i) := op(io.in(i), io.in(i + 1))
    }

}