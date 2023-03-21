import matplotlib.pyplot as plt
import numpy as np
from mpl_toolkits.mplot3d import Axes3D
from matplotlib.animation import FuncAnimation

with open('kalmar-fun.txt','r') as f:
    ls = f.readlines()
    raw_data_list = []
    for s in ls:
        t = s.replace(')\n',"").replace('(','').split(',')
        raw_data_list.append([float(i) for i in t])
x = [0]
y =[0]
z =[0]
for a in raw_data_list:
    x.append(x[-1] + 0.5*((a[0])**2) *0.01)
    y.append(y[-1] + 0.5*((a[1])**2) *0.01)
    z.append(z[-1] + 0.5*((a[2])**2) *0.01)

# create 3D plot
fig = plt.figure()
ax = fig.add_subplot(projection='3d')

# create initial plot
line, = ax.plot(x, y, z)

# animation function
def update(i):
    line.set_data(x[:i], y[:i])
    line.set_3d_properties(z[:i])
    return line,

# create animation
ax.set_xlabel('X Label')
ax.set_ylabel('Y Label')
ax.set_zlabel('Z Label')

ani = FuncAnimation(fig, update, frames=5500, interval=100, blit=False)
# show animation
plt.show()