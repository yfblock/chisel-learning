package riscv64

import org.scalatest.freespec.AnyFreeSpec
import chiseltest._

class RVCpuTester extends AnyFreeSpec with ChiselScalatestTester {
    "Test RVCpu" in {
        test(new RVCpu("a.bin.txt")) { dut => {
            for(i <- 0 until 3) {
                dut.clock.step()
                println(s"PC: ${dut.io.pc.peek()}: ins: ${dut.io.ins.peek()}")
            }
        }}
    }
}
