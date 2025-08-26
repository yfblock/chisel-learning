package riscv64

import org.scalatest.freespec.AnyFreeSpec
import chiseltest._

class RVCpuTester extends AnyFreeSpec with ChiselScalatestTester {
    "Test RVCpu" in {
        val loadFile = "c/a.hex"
        test(new RVCpu(loadFile)) { dut => {
            for(i <- 0 until 3) {
                println(s"PC: ${dut.io.pc.peek()}: ins: ${dut.io.ins.peek()}")
                dut.clock.step()
            }
        }}
    }
}
