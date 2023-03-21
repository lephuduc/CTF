from pyquaternion import Quaternion
with open('kalmar-fun.txt','r') as f:
    ls = f.readlines()
    raw_data_list = []
    for s in ls:
        t = s.replace(')\n',"").replace('(','').split(',')
        raw_data_list.append([float(i) for i in t])

orientation_list = []
for raw_data in raw_data_list:
    q = Quaternion(raw_data[0], raw_data[1], raw_data[2])
    orientation_list.append(q)
accumulated_q = Quaternion()
absolute_orientation_list = []
for q in orientation_list:
    accumulated_q *= q
    absolute_orientation_list.append(accumulated_q)
print(absolute_orientation_list)