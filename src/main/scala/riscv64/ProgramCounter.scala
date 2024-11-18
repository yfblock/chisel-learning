package riscv64

import chisel3._
import chisel3.util._

class ProgramCounter extends Module {
    val io = IO(new Bundle {
        val write = Input(Bool())
        val writePC = Input(UInt(Config.bitWidth.W))
        val PC = Output(UInt(Config.bitWidth.W))
    })

    val PC_R = RegInit(Config.entryPoint.U(Config.bitWidth.W))

    PC_R := Mux(io.write, io.writePC, PC_R + 4.U)
    io.PC := PC_R

    def write(value: UInt): Unit = {
        io.write := true.B
        PC_R     := value
    }
}
