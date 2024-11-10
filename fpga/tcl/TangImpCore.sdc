//Copyright (C)2014-2024 GOWIN Semiconductor Corporation.
//All rights reserved.
//File Title: Timing Constraints file
//Tool Version: V1.9.10.03 
//Created Time: 2024-11-08 20:06:10
create_clock -name clk_50M -period 20 -waveform {0 10} [get_ports {clock}]
