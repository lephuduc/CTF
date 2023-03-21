import numpy as np
import matplotlib.pyplot as plt
with open('kalmar-fun.txt','r') as f:
    ls = f.readlines()
    raw_data_list = []
    for s in ls:
        t = s.replace(')\n',"").replace('(','').split(',')
        raw_data_list.append([float(i) for i in t])
# Các vector gia tốc
a = np.array(raw_data_list)

# Khởi tạo dãy vector gia tốc

# Tính toán dãy vị trí của vật
v = np.zeros_like(a)
for i in range(len(a)):
    v[i] = np.sum(a[:i+1], axis=0)

# Vẽ biểu đồ đường đi của vật
fig = plt.figure()
ax = fig.add_subplot(projection='3d')
ax.plot(v[:,0], v[:,1], v[:,2])
plt.show()