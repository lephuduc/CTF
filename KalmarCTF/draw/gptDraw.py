import matplotlib.pyplot as plt
import numpy as np

# generate some sample data
t = np.linspace(0, 10, 1000)
x = np.sin(2*np.pi*t)
y = np.cos(2*np.pi*t)
z = t
print(x[0],y[0],z[0])
# create 3D plot
# fig = plt.figure()
# ax = fig.add_subplot(projection='3d')
# ax.plot(x, y, z)
# ax.set_xlabel('X Label')
# ax.set_ylabel('Y Label')
# ax.set_zlabel('Z Label')
# plt.show()
