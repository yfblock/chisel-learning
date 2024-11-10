#!/bin/env python3

import argparse
import subprocess

fpga_path = "fpga"

method_map = {
    "burn": None
}

def parse_args():
    parser = argparse.ArgumentParser(description="Build FPGA Loader.")
    subparsers = parser.add_subparsers(
        title='Command',
        dest='subCommand',
    )

    burn_parser = subparsers.add_parser(
        name='burn',
        help='Burn the bitstream file to the hardware'
    )
    burn_parser.add_argument(
        '-b',
        '--build',
        action='store_true',
        dest='needBuild',
        help='Build the bitstream file before burning.'
    )
    burn_parser.add_argument(
        '-c',
        '--compile',
        action='store_true',
        dest='needCompile',
        help='Compile the chisel before burning.'
    )
    burn_parser.set_defaults(func=burn)

    build_parser = subparsers.add_parser(
        name='build',
        help='Build the bitstream file'
    )
    build_parser.set_defaults(func=build)

    clean_parser = subparsers.add_parser(
        name='clean',
        help='Clean the build files'
    )
    clean_parser.set_defaults(func=clean)

    compile_parser = subparsers.add_parser(
        name='compile',
        help='Compile the chisel to generate verilog files'
    )
    compile_parser.set_defaults(func=compile)

    args = parser.parse_args()
    if(args.subCommand):
        args.func(args)
    else:
        parser.print_help()

def compile(args):
    return subprocess.run(['./mill', 'chiselLearning.runMain', 'TopLevel', fpga_path])

def clean(args):
    subprocess.run(["rm", "-rf", "impl"], cwd=fpga_path)

def build(args):
    res = subprocess.run(["gw_sh", "tcl/synth.tcl"], cwd=fpga_path)
    subprocess.run(
        [
            "grep", 
            "-A20", 
            "\\. Resource Usage Summary", 
            "impl/pnr/TangImpCore.rpt.txt"
        ], 
        cwd=fpga_path
    )
    return res

def burn(args):
    try:
        if args.needCompile:
            res = compile(args)
            res.check_returncode()
        if args.needBuild:
            res = build(args)
            res.check_returncode()
        subprocess.run(["openFPGALoader", "-b", "tangnano9k", "impl/pnr/TangImpCore.fs"], cwd=fpga_path)
    except:
        pass


if __name__ == "__main__":
    parse_args()
