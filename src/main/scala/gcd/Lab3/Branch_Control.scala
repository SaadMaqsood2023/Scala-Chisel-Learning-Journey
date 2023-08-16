package Lab3

import chisel3 . _
import chisel3 . util . _

class LM_IO_Interface_BranchControl extends Bundle {

    val fnct3 = Input ( UInt (3. W ) )
    val branch = Input ( Bool () )
    val arg_x = Input ( UInt (32. W ) )
    val arg_y = Input ( UInt (32. W ) )
    val br_taken = Output ( Bool () )
}
object Branch_Op
{
    val eql = 0.U(3.W)
    val n_eql = 1.U(3.W)
    val br_blt = 2.U(3.W)
    val br_bge = 3.U(3.W)
    val br_bltu = 4.U(3.W)
    val br_bgeu = 5.U(3.W)
}

import Branch_Op._

class Branch_Control extends Module {
    val io = IO (new LM_IO_Interface_BranchControl )
    // Start Coding here
    // End your code here
    // Well , you can actually write classes too . So , technically you have no limit ; )
    

    // Will start when the branch instruction will come
    io.br_taken := 0.U
    
    when(io.branch)
    {

        switch(io.fnct3)
        {
            is(eql)
            {
                when(io.arg_x === io.arg_y)
                {
                    io.br_taken := 1.U  // if the condition is true so branch will be executed
                }
            }
            is(n_eql)
            {
                when(io.arg_x =/= io.arg_y)
                {
                    io.br_taken := 1.U  // if the condition is true so branch will be executed
                }
            }
            is(br_blt)
            {
                when(io.arg_x < io.arg_y)
                {
                    io.br_taken := 1.U  // if the condition is true so branch will be executed
                }
            }
            is(br_bge)
            {
                when(io.arg_x >= io.arg_y)
                {
                    io.br_taken := 1.U  // if the condition is true so branch will be executed
                }
            }
            is(br_bltu)
            {
                when(io.arg_x < io.arg_y)
                {
                    io.br_taken := 1.U  // if the condition is true so branch will be executed
                }
            }
            is(br_bgeu)
            {
                when(io.arg_x >= io.arg_y)
                {
                    io.br_taken := 1.U  // if the condition is true so branch will be executed
                }
            }


        }  //Switch ending


    }


}