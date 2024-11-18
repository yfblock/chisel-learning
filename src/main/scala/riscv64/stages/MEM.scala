package riscv64.stages

import chisel3._
import chisel3.util._
import riscv64.MemoryBundle

class MEM(bitWidth: Int) extends Module {
    val io = IO(new Bundle {
        val mem = new MemoryBundle(bitWidth)
    })

    
}
