import angr
import claripy

FLAG_LEN = 32
project = angr.Project("./xva") 

flag_chars = [claripy.BVS('flag%d' % i, 8) for i in range(FLAG_LEN)]
flag = claripy.Concat( *flag_chars)
start_address = 0x1ABF+0x400000
state = project.factory.entry_state(
        add_options = { angr.options.SYMBOL_FILL_UNCONSTRAINED_MEMORY,
                    angr.options.SYMBOL_FILL_UNCONSTRAINED_REGISTERS},
        stdin=angr.SimFileStream(name='stdin', content=flag, has_end=False),
)

for k in flag_chars:
    state.solver.add(k >= 0x20)
    state.solver.add(k <= 0x7f)

simulation = project.factory.simgr(state)
find_addr  = 0x1AFF+0x400000
avoid_addr = 0x1B10+0x400000
simulation.explore(find=find_addr, avoid=[avoid_addr])
print(simulation.found[0].solver.eval(flag, cast_to = bytes))
