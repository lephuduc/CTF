#tshark.exe -r .\cards.pcap -T fields -e tcp.stream -Y "frame.len == 85 or frame.len == 67 and ftp.request"
stream = [3,37,38,50,33,61,26,10,11,19,43,71,13,36,55,60,23,39,63,78,42,68,47,65,16,69,56,72,6,21,5,27,62,75,22,32,73,14,18,34,76,77,41,4,12,49,45,64,1,35,51,29,20,9,52,30,31,70,66,67,0,2,57,59,15,48,7,25,53,40,46,17,58,44,28,54,74,8,24]
import subprocess
import os
ls = []
for i in range(len(stream)):
    n = stream[i]
    with open("cwddata.txt", 'w') as f:
        subprocess.run(f"C:\\Program Files\\Wireshark\\tshark.exe -r cards.pcap -z follow,tcp,ascii,{n}", stdout=f)
    with open("cwddata.txt", 'r') as f:
        d = f.readlines()
        ls.append(int(d[-44].split()[-1].strip('\n')) - 342)
    print(ls,len(ls))