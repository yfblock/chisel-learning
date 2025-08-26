import circt.stage.ChiselStage
import chisel3._
import chisel3.util._
import riscv64.ALU

class LEDBundle extends Bundle {
    val leds = Vec(8, Bool())
}

class FlowLight extends Module {
    val io = IO(new LEDBundle())
    val leds = RegInit(1.U(8.W))
//    val alu = Module(new ALU(64))

    val (_, counterWrap) = Counter(true.B, 50_000_000)
    when(counterWrap) {
        leds := leds + 1.U
        println(s"leds ${leds}")
    }
//    io.leds := Cat(Seq(alu.io.led) ++ (~leds).asBools).asBools
    io.leds := (~leds).asBools
    // (alu.output, (~leds).asBools)
}

class TopLevel extends Module {
    withReset(reset.asBool) {
        val io = IO(new LEDBundle())
        val flowLight = Module(new FlowLight())
        flowLight.io <> io
    }
}

/**
 * Generate System Verilog Files
 */
object TopLevel extends App {
    var verilogPath = "fpga";
    if (this.args.length > 0) {
        verilogPath = this.args(0);
    }
    ChiselStage.emitSystemVerilogFile(
        new TopLevel,
        Array("--target-dir", verilogPath +"/generated/"),
        firtoolOpts = Array("-disable-all-randomization", "-strip-debug-info")
    )
}
