package riscv64

import chisel3._
import chisel3.util._

class RegRDBundle(bitWidth: Int, regNum: Int) extends Bundle {
    val addr   = Input(UInt(log2Ceil(regNum).W))
    val value  = Output(UInt(bitWidth.W))
}

class RegWRBundle(bitWidth: Int, regNum: Int) extends Bundle {
    val addr   = Input(UInt(log2Ceil(regNum).W))
    val write  = Input(Bool())
    val value  = Output(UInt(bitWidth.W))
}

class RegBank extends Module {
    val regNum: Int = 32;
    val io = IO(new Bundle {
        val r0 = new RegRDBundle(Config.bitWidth, regNum)
        val r1 = new RegRDBundle(Config.bitWidth, regNum)
        val w0 = new RegWRBundle(Config.bitWidth, regNum)
    })

    val regs = RegInit(VecInit(Seq.fill(regNum)(0.U(Config.bitWidth.W))))

    regs(0)     := 0.U
    io.r0.value := regs(io.r0.addr)
    io.r1.value := regs(io.r1.addr)

    when(io.w0.write) {
        regs(io.w0.addr) := io.w0.value
    }
}
