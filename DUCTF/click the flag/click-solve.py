from PIL import Image
from pwn import *
word = [28369, 28505, 22748, 1342, 11467, 2037, 24111, 21247,
19254, 6369, 8657, 18213, 1280, 7648, 31798, 32106,
24649, 11568, 22518, 22472, 21713, 10322, 1202, 3073,
4692, 10604, 18024, 15343, 4141, 4158, 24181, 19754,
1496, 17075, 10610, 29515, 10214, 15123, 14929, 27822,
10502, 20326, 31024, 27963, 30019, 5471, 12347, 19574,
17691, 1869, 5009, 21044, 25891, 13305, 13349, 17710,
25354, 12265, 18536, 2831, 31952, 12486, 13268, 28255,
13104, 15700, 24705, 7700, 15163, 6806, 14197, 14007,
5465, 27963, 27138, 3576, 6806, 25979, 9765, 26482,
4952, 6838, 30994, 3585, 6694]

# img_flag = Image.open(BytesIO(open("flag_img.png",'rb').read()))
# flag_img = img_flag.tobytes()
flag_img = open('flag_img.png','rb').read()
# print(len(flag_img))
flag = []
game_stage = 1
while game_stage<=85:   
    print(chr((flag_img[word[game_stage-1]]^0x42 )),end = "")
    game_stage+=1
print(bytes(flag))
# b = b'DUCTF{dId_'

