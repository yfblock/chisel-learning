import chisel3.simulator.EphemeralSimulator._
import gcd.DecoupledGcd
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers

class TopLevelTester extends AnyFreeSpec with Matchers {
    "Gcd should calculate proper greatest common denominator" in {
        simulate(new TopLevel()) { dut => {
            dut.clock.step()

//            for(i <- 0 until 14) {
//                dut.clock.step()
//            }
        }}
    }
}
