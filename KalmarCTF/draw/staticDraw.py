import matplotlib.pyplot as plt
import numpy as np
with open('kalmar-fun.txt','r') as f:
    ls = f.readlines()
    raw_data_list = []
    for s in ls:
        t = s.replace(')\n',"").replace('(','').split(',')
        raw_data_list.append([float(i) for i in t])
x = [0]
y =[0]
z =[0]
v0 = 0
v1 = 0
v2 = 0
for a in raw_data_list:
    x.append(x[-1] +v0*0.1 + 0.5*(a[0]) *0.01)
    v0 = 0.1*a[0]
    y.append(y[-1] +v1*0.1 + 0.5*(a[1]) *0.01)
    v1 = 0.1*a[1]
    z.append(z[-1] +v2*0.1 + 0.5*(a[2]) *0.01)
    v2 = 0.1*a[2]
fig = plt.figure()
ax = fig.add_subplot(projection='3d')
ax.plot(z, y, x)
ax.set_xlabel('X Label')
ax.set_ylabel('Y Label')
ax.set_zlabel('Z Label')
plt.show()