#!/bin/bash

riscv64-linux-gnu-gcc -static -nostartfiles -ffreestanding -O2 -nostdlib main.c
rust-objcopy --binary-architecture=riscv64 a.out --strip-all -O binary a.bin
# convert binary to hexfile, 4bytes per-line
xxd -p -c 4 a.bin | sed 's/../& /g'  > a.hex
