from base64 import b64decode as b64


label1 = b64(b64(bytes.fromhex('576B64736131677A62485A6B55543039')))
label2 = b64(b64(bytes.fromhex('57444E57656C70574F44303D')))
label3 = b64(b64(bytes.fromhex('57565935565646575453383D')))
prefix = b64(b64(b'UkZWRFZFWT0='))
flag = b64(b64(b"ZXc9PQ=="))
galf = b64(b64(b"ZlE9PQ=="))
near_end = b64(b"_ZGVhZGIzM2ZjYWZl")
print(prefix+flag+label1+label2+label3+near_end+galf)
