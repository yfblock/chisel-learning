#!/bin/bash

riscv64-linux-gnu-gcc -static -nostartfiles -ffreestanding -O2 main.c
