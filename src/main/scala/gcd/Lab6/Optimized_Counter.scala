package Lab6

// Optimized counter example
import chisel3._
import chisel3.util._
class Optimized_Counter (val max : Int , val min : Int = 0) extends Module {

    val io = IO (new Bundle {
    val out = Output ( UInt ( log2Ceil ( max ).W ) )
    })
    val counter = RegInit ( min . U ( log2Ceil ( max ) . W ) )
    // This function calculates the ceiling of the base-2 logarithm of max. It
    // determines the number of bits required to represent the max value, 
    // ensuring that the counter has enough bits to accommodate the specified range.

    // If the max count is of power 2 and the min value = 0 ,
    // then we can skip the comparator and the Mux
    // val count_buffer = if ( isPow2 ( max ) && ( min == 0) ) counter + 1. U
    // else Mux ( counter === max .U , min .U , counter + 1. U )
    // isPow2 tells us if the number is in range of power of 2
    val count_buffer = Mux( (isPow2 ( max ) &&( min == 0)).B , counter + 1.U, 
    Mux( counter === max.U , min.U , counter + 1.U ) )

    counter := count_buffer
    io.out := counter

}