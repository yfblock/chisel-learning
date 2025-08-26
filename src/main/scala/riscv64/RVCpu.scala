package riscv64

import chisel3._
import chisel3.util._
import chisel3.util.experimental._

class RVCpu(memoryFile: String = "") extends Module {
    val io = IO(new Bundle {
        val ins = Output(UInt(Config.bitWidth.W))
        val pc  = Output(UInt(Config.bitWidth.W))
    })

//    val memory = SyncReadMem(1024, UInt(32.W))
    val memory = Mem(1024, UInt(32.W))
    loadMemoryFromFileInline(memory, memoryFile)

    val pc = Module(new ProgramCounter());
//    val regs = Module(new RegBank());

//    val ins = memory.read(pc.io.PC)
    val ins = memory(pc.io.PC)

    pc.io.write   := false.B
    pc.io.writePC := 0.U

    io.ins := ins
    io.pc  := pc.io.PC

    println(s"ins ${ins}")
//    loadMemoryFromFile(memory, "../../../../c/a.bin")
//    loadMemoryFromFileInline(memory, "d")
}
