# numbers2text2.py
# Konvertuje niz brojeva u string

inString = input("Unesite niz brojeva: ")

chars = [] 
for numStr in inString.split():
    codeNum = eval(numStr)       # cifre -> broj
    chars.append(chr(codeNum))   # broj -> char; dodaj na kraj

message = "".join(chars)
print("\nPoruka glasi:", message)
