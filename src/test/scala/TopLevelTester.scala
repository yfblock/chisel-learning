import chiseltest._
import org.scalatest.freespec.AnyFreeSpec

class TopLevelTester extends AnyFreeSpec with ChiselScalatestTester {
    "TopLevel test" in {
        test(new TopLevel()) { dut => {
            dut.clock.step()

            for(i <- 0 until 14) {
                dut.clock.step()
            }
        }}
    }
}
