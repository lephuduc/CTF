f = open('dump','rb')

d = f.read()
idx = d.index(b'\xAE\x88\x89')
flag = d[idx:idx+0x42]
ls = []
idx2 = idx - 80
s = d[idx2:idx2 +0x42]
print(s)
for i in range(0x42):
	print(chr(((flag[i]-s[i])^s[i])&0xff),end = "")