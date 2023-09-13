package DataPathSSP

// Optimized counter example
import chisel3._
import chisel3.util._

object var_size
{
    val counter_size = 256
    val load_B = 0.U
    val load_H = 1.U
    val load_W = 2.U
    val load_BU = 4.U
    val load_HU = 5.U
    val store_B = 0.U
    val store_H = 1.U
    val store_W = 2.U
    val lui     = "h37".U
    val auipc   = "h17".U
    val unb_ins   = "h6f".U
}
import var_size._
class Top extends Module {
    val io = IO(new Bundle {
        val ins = Input (UInt(32.W))   // taking input for the instruction in the IMEM
        val inst_out = Output(UInt(32.W))
        val out = Output(SInt(32.W))
    })

    // val imem_module = Module(new IMEM("D:/Semester lll/CO&AL(P)/assemblyHexcode.txt"))
    val imem_module     = Module(new IMEM())
    val pc_module       = Module(new PC(counter_size))
    val register_module = Module(new RegisterFile)
    val alu_module      = Module(new ALU)
    val dmem_module     = Module(new DMEM)
    val cu_module       = Module(new CU)
    val branch_module   = Module(new Branch) 

    imem_module.io.wen := cu_module.io.imem_en        // Setting enable pin for IMEM
    
    imem_module.io.address := (pc_module.io.out).asUInt
    
    io.inst_out := io.ins  
    imem_module.io.dataIn := io.inst_out  // assigning the output to dataIn of IMEM
    
    cu_module.io.ins := imem_module.io.dataOut        // Giving whole instruction to CU

    register_module.io.raddr1 := cu_module.io.rs1     // Input of address of source register
    register_module.io.raddr2 := cu_module.io.rs2

    alu_module.io.alu_Op := cu_module.io.func3_7    // assigning opcode from CU

    alu_module.io.in_A := 0.U  // Default value to ALU
    alu_module.io.in_B := 0.U

    when(imem_module.io.dataOut(6,0) === "h33".U) 
    {
        alu_module.io.in_A := register_module.io.rdata1  // Inserting data from register file to ALU
        alu_module.io.in_B := register_module.io.rdata2
    }.elsewhen(imem_module.io.dataOut(6, 0) === "h13".U)
    {
        alu_module.io.in_A := register_module.io.rdata1  // Inserting data from register file to ALU
        alu_module.io.in_B := cu_module.io.immediate
    }

    


    //Data Memory code
    dmem_module.io.addr := 0.U    // default value to data memory address
    dmem_module.io.rd_enable := cu_module.io.r_en 
    when(cu_module.io.ins(6,0) === 3.U) // calculating address in load instruction
    {
        // Using ALU to calculate load address
        alu_module.io.in_A := cu_module.io.immediate
        alu_module.io.in_B := register_module.io.rdata1
        dmem_module.io.addr :=  alu_module.io.out
    }
    .elsewhen(cu_module.io.ins(6,0) === "h23".U)  // Calculating address in store instruction
    {
        // Using ALU to calculate store address
        alu_module.io.in_A := cu_module.io.immediate
        alu_module.io.in_B := register_module.io.rdata1  // write according to S-format
        val temp_addr   := alu_module.io.out
        alu_module.io.in_A := temp_addr                  // Again using ALU to calculate 
        alu_module.io.in_B := 2.U                        // Address using SRL
        alu_module.io.alu_Op := 5.U
        dmem_module.io.addr := alu_module.io.out

    }
    register_module.io.wen := cu_module.io.w_en   // write enable register
    register_module.io.waddr := cu_module.io.rd  //rd address
    register_module.io.wdata := alu_module.io.out  // write data from alu module

    branch_module.io.func3 := cu_module.io.func3_7   // setting branch instruction function 3
    branch_module.io.br_ins := cu_module.io.br_en    // setting that a branch instruction has come
    // Values for branch module inputs
    branch_module.io.inpA := register_module.io.rdata1
    branch_module.io.inpB := register_module.io.rdata2
    pc_module.io.br_imm := 0.S         // default value for branch immediate
    // pc_module.io.en_jump := branch_module.io.br_taken  
    pc_module.io.en_jump := cu_module.io.unbr_en    // This line was not letting to execute the branch instruction
                                                    // so had to write the branch statement inside the condition

    when(cu_module.io.ins(6,0) === lui)    
    {   
        // Assigning upper immediate to register file
        register_module.io.wdata := cu_module.io.immediate
    }.elsewhen(cu_module.io.ins(6,0) === auipc )
    {
        alu_module.io.in_A := (pc_module.io.out).asUInt
        alu_module.io.in_B := cu_module.io.immediate
        register_module.io.wdata := alu_module.io.out
    }.elsewhen(branch_module.io.br_ins)   // checking if there is a branch instruction
    {
        pc_module.io.en_jump := branch_module.io.br_taken
       when(branch_module.io.br_taken)
       {
            pc_module.io.br_imm := (cu_module.io.immediate).asSInt
       }
    }.elsewhen(cu_module.io.ins(6,0) === unb_ins)   // Unconditional branch instruction
    {
        // alu_module.io.in_A := (pc_module.io.out).asUInt
        alu_module.io.in_B := cu_module.io.immediate
        // pc_module.io.br_imm   := (alu_module.io.out).asSInt
        pc_module.io.br_imm   := (cu_module.io.immediate).asSInt
        register_module.io.wdata := cu_module.io.immediate
    }


    dmem_module.io.wr_enable := cu_module.io.dmem_en  // Write enable for DMEM
    
    // From register file to DMEM (store instruction)
    dmem_module.io.dataIn := 0.U
    when(cu_module.io.ins(6,0) === "b0100011".U)
    {
        when(cu_module.io.ins(14,12) === store_B)
        {
            dmem_module.io.dataIn := register_module.io.rdata2(7,0)  
        }.elsewhen(cu_module.io.ins(14,12) === store_H)
        {
            dmem_module.io.dataIn := register_module.io.rdata2(15,0)
        }
        .elsewhen(cu_module.io.ins(14,12) === store_W)
        {
            dmem_module.io.dataIn := register_module.io.rdata2
        }
        
    }

    // Setting data of register according to func3 of Load instruction
    // From DMEM to register file (load instruction)
    when(cu_module.io.ins(6,0) === 3.U)
    {
        when(cu_module.io.func3_7 === load_B)
        {// Loading a 8-bit signed num, so while using it, use asSInt
            val lb_num = (dmem_module.io.dataOut(7,0) ).asSInt
            register_module.io.wdata := lb_num.asUInt
        }
        .elsewhen(cu_module.io.func3_7 === load_H)
        {// Loading a 16-bit signed num, so while using it, use asSInt
            val lh_num = (dmem_module.io.dataOut(15,0) ).asSInt
            register_module.io.wdata := lh_num.asUInt
        }
        .elsewhen(cu_module.io.func3_7 === load_W)
        {// Loading a 32-bit signed num, so while using it, use asSInt
            val lw_num = (dmem_module.io.dataOut).asSInt
            register_module.io.wdata := lw_num.asUInt
        }
        .elsewhen(cu_module.io.func3_7 === load_BU)
        {
            val lbu_num = dmem_module.io.dataOut(7,0)
            register_module.io.wdata := lbu_num.asUInt
        }
        .elsewhen(cu_module.io.func3_7 === load_HU)
        {
            val lhu_num = dmem_module.io.dataOut(15,0)
            register_module.io.wdata := lhu_num.asUInt
        }

        // io.out := ( register_module.io.wdata ).asSInt
        
        
    }
    io.out := (register_module.io.wdata).asSInt   // changing the output to signed
    // io.out := (dmem_module.io.dataIn).asSInt
    // io.out := (pc_module.io.br_imm).asSInt
    
   
}