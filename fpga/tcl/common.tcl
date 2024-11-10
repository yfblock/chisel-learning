#!/usr/bin/env gw_sh
# A simple gw_sh build script
# by andelf

add_file -type cst "tcl/TangImpCore.cst"
add_file -type sdc "tcl/TangImpCore.sdc"

# NOTE: Tang Nano 9k is a GW1NR-9C device! not GW1NR-9
set_device GW5A-LV25MG121NES -name GW5A-25A
# set_device GW5A-LV25MG121NC1/I0 -device_version A

set_option -verilog_std sysv2017
set_option -synthesis_tool gowinsynthesis
set_option -output_base_name TangImpCore
set_option -cst_warn_to_error 0
set_option -bit_security 0

set_option -use_sspi_as_gpio 1
set_option -use_cpu_as_gpio 1

set_option -use_mspi_as_gpio 1
set_option -use_ready_as_gpio 1
set_option -use_done_as_gpio 1
set_option -use_i2c_as_gpio 1

# set_option -use_reconfign_as_gpio 1

set_option -multi_boot 1
