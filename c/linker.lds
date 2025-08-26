OUTPUT_ARCH(riscv)

SECTIONS {

    /DISCARD/ : {
        *(.comment) *(.gnu*) *(.note*) *(.eh_frame*)
    }
}