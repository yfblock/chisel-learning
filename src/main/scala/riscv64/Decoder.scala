package riscv64

import chisel3._
import chisel3.util._

class Decoder extends Module {
    val io = IO(new Bundle {
        val op = Input(UInt(Config.insWidth.W))
    })

    val res = ListLookup(io.op, List(), Array(
        (Instructions.ADD -> List()),
        (Instructions.SUB -> List())
    ));
}
