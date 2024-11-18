#!/bin/bash

riscv64-linux-gnu-gcc -static -nostartfiles -ffreestanding -O2 -nostdlib main.c
rust-objcopy --binary-architecture=riscv64 a.out --strip-all -O binary a.bin
