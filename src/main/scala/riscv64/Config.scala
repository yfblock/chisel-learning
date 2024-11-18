package riscv64

import chisel3._
import chisel3.util._

object Config {
    val bitWidth = 64;
    val insWidth = 32;

    val entryPoint = 0x0;
}