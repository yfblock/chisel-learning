package riscv64

import chisel3.{util, _}
import chisel3.util._

object ALU {
    def ALU_ADD:    UInt = 0.U(4.W)
    def ALU_SLL:    UInt = 1.U(4.W)
    def ALU_SEQ:    UInt = 2.U(4.W)
    def ALU_SNE:    UInt = 3.U(4.W)
    def ALU_XOR:    UInt = 4.U(4.W)
    def ALU_SRL:    UInt = 5.U(4.W)
    def ALU_OR:     UInt = 6.U(4.W)
    def ALU_AND:    UInt = 7.U(4.W)
    def ALU_COPY1:  UInt = 8.U(4.W)
    def ALU_COPY2:  UInt = 9.U(4.W)
    def ALU_SUB:    UInt = 10.U(4.W)
    def ALU_SRA:    UInt = 11.U(4.W)
    def ALU_SLT:    UInt = 12.U(4.W)
    def ALU_SGE:    UInt = 13.U(4.W)
    def ALU_SLTU:   UInt = 14.U(4.W)
    def ALU_SGEU:   UInt = 15.U(4.W)

    def ALU_X:      UInt = 0.U(4.W) // BitPat("b????")

    def isSub(op: UInt): Bool = op(3)     // need sub?
    def isCmp(op: UInt): Bool = op >=ALU_SLT        // Compare op?
    def isCmpU(op: UInt): Bool = op >= ALU_SLTU     // Compare unsigned?
    def isCmpI(op: UInt): Bool = op(0)              // need inverse for compare?
    //noinspection ScalaStyle
    def isCmpEq(op: UInt): Bool = !op(3)            // EQ or NEQ compare operation?
}

import ALU._

//noinspection ScalaStyle
class ALU(bitWidth: Int) extends Module {
    val io = IO(new Bundle {
        val op1 = Input(UInt(bitWidth.W))
        val op2 = Input(UInt(bitWidth.W))
        val alu_op = Input(UInt(4.W))
        val out = Output(UInt(bitWidth.W))
        val cmp_out = Output(Bool())
    })

    val w_out = MuxLookup(io.alu_op, io.op2)(Seq(
        ALU_ADD     -> (io.op1 + io.op2),
        ALU_SUB     -> (io.op1 - io.op2),
        ALU_AND     -> (io.op1 & io.op2),
        ALU_OR      -> (io.op1 | io.op2),
        ALU_XOR     -> (io.op1 ^ io.op2),
        ALU_SEQ     -> (io.op1 === io.op2),
        ALU_SNE     -> (io.op1 =/= io.op2),
        ALU_SLT     -> (io.op1.asSInt <  io.op2.asSInt),
        ALU_SGE     -> (io.op1.asSInt >= io.alu_op.asSInt),
        ALU_SLTU    -> (io.op1 <  io.op2),
        ALU_SGEU    -> (io.op1 >= io.op2),
        ALU_COPY1   -> io.op1,
        ALU_COPY2   -> io.op2
    ))

    io.out := w_out
    io.cmp_out := false.B
}
