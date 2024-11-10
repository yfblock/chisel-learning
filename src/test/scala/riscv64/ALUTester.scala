package riscv64

import chisel3._
import chisel3.experimental.BundleLiterals._
import chisel3.simulator.EphemeralSimulator._
import gcd.DecoupledGcd
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

class ALUTester extends AnyFreeSpec with Matchers {
    "Gcd should calculate proper greatest common denominator" in {
        simulate(new ALU(64)) { dut => {
            dut.clock.step()
            dut.io.op1.poke(0x8.U)
            dut.io.op2.poke(0x7.U)
            dut.io.alu_op.poke(ALU.ALU_ADD)
            dut.clock.step()
            println(s"ALU OP ${dut.io.out.peek()}")
            dut.io.alu_op.poke(ALU.ALU_SUB)
            println(s"ALU OP ${dut.io.out.peek()}")

        }}
    }
}
