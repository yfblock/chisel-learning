package riscv64

//import chisel3._
//import chisel3.simulator.EphemeralSimulator._
//import org.scalatest.freespec.AnyFreeSpec
//import org.scalatest.matchers.must.Matchers
//
//class RVCpuTester extends AnyFreeSpec with Chisel {
//    "Gcd should calculate proper greatest common denominator read memory" in {
//        simulate(new RVCpu("/Users/yfblock/Code/chisel/chisel-learning/c/a.bin")) { dut => {
//            for(i <- 0 until 3) {
//                dut.clock.step()
//                println(s"PC: ${dut.io.pc.peek()}: ins: ${dut.io.ins.peek()}")
//            }
////            dut.clock.step()
//        }}
//    }
//}

import chisel3._
import chiseltest._
import chisel3.experimental.BundleLiterals._
//import chisel3.simulator.EphemeralSimulator._
import org.scalatest.freespec.AnyFreeSpec
//import org.scalatest.matchers.must.Matchers

class RVCpuTester extends AnyFreeSpec with ChiselScalatestTester {
    "Gcd should calculate proper greatest common denominator read memory" in {
        test(new RVCpu("a.bin.txt")) { dut => {
            for(i <- 0 until 3) {
                dut.clock.step()
                println(s"PC: ${dut.io.pc.peek()}: ins: ${dut.io.ins.peek()}")
            }
        }}
    }
}
