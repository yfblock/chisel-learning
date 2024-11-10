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
    ))

    io.out := w_out
    io.cmp_out := false.B
//
//    // Shift
//    val op2_inv: UInt = Mux(isSub(io.alu_op), ~io.op2, io.op2).asUInt
//    val sum: UInt = io.op1 + op2_inv + isSub(io.alu_op)
//    val shamt: UInt = io.op2(4,0).asUInt
//    val shin: UInt = Mux(io.alu_op === ALU_SRA || io.alu_op === ALU_SRL,io.op1,Reverse(io.op1))
//    val shift_r: UInt = (Cat(isSub(io.alu_op) & shin(31), shin).asSInt >> shamt)(31, 0)
//    val shift_l: UInt = Reverse(shift_r)
//    val slt: Bool = Mux(io.op1(31) === io.op2(31), sum(31), Mux(isCmpU(io.alu_op), io.op2(31), io.op1(31)))
//
//    val cmp: Bool = isCmpI(io.alu_op) ^ Mux(isCmpEq(io.alu_op), (io.op1 ^ io.op2) === 0.U, slt)
//
////    val w_out = MuxLookup(io.alu_op, io.op2, Seq(
////        ALU_ADD     -> (io.op1 + io.op2),
////        ALU_SLL     -> (io.op1 << shamt),
////        ALU_SEQ     -> (io.op1 === io.op2),
////        ALU_SNE     -> (io.op1 =/= io.op2),
////        ALU_XOR     -> (io.op1 ^ io.op2),
////        ALU_SRL     -> (io.op1 >> shamt),
////        ALU_OR      -> (io.op1 | io.op2),
////        ALU_AND     -> (io.op1 & io.op2),
////        ALU_SUB     -> (io.op1 - io.op2),
////        ALU_SRA     -> (io.op1.asSInt >> shamt).asUInt,
////        ALU_SLT     -> (io.op1.asSInt < io.op2.asSInt),
////        ALU_SGE     -> (io.op1.asSInt >= io.op2.asSInt),
////        ALU_SLTU    ->  (io.op1 < io.op2),
////        ALU_SGEU    ->  (io.op1 >= io.op2),
////        ALU_COPY1   -> io.op1,
////        ALU_COPY2   -> io.op2
////    )).asUInt
//
//    val w_out = 3.U
//    io.out := w_out
//    io.cmp_out := cmp
}
