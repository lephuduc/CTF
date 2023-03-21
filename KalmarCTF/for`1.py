ls = [3, 35, 36, 43, 45, 62, 26, 10, 12, 19, 41, 71, 13, 39, 55, 61, 27, 40, 58, 76, 44, 66, 46, 67, 16, 70, 57, 72, 6, 21, 5, 24, 63, 78, 22, 28, 74, 15, 17, 42, 77, 75, 38, 4, 11, 48, 50, 64, 1, 37, 54, 29, 20, 9, 51, 30, 31, 65, 69, 68, 0, 2, 56, 59, 14, 52, 7, 23, 49, 33, 53, 18, 60, 47, 32, 34, 73, 8, 25]
c= 'm_tfwr_flf_3eccaykdw_hhuhrld{erae?_onsuo}04afr__ar_u1ut_ksffklas_hsce33f_e3p_hn'
x = ["-" for i in range(99)]
for i in range(len(ls)):
    x[ls[i]] = c[i]
print(x)
print("".join(x))

with open('swallNumber.txt','r') as f:
    ls = [chr(int(i.strip('\n'),16)).replace('\x00','-') for i in f.readlines()]
    s ="".join(ls).replace('\n','')
    for i in range(58):
        for j in range(i,len(ls),60):
            if ls[j]!='-':
                # print(ls[j],end = "")
                break

        

    
