HD# Chisel learning

## Build and export system verilog

```shell
./tic.py burn -b -c
```

`-c` indicates compile

`-b` indicates build

Burn Sequence:

`Compile(Scala Files)` -> `Build(Verilog)` -> `Burn(To Board)`

> Alternative, you can use the single commands

```shell
./tic.py compile
./tic.py build
./tic.py burn
```
