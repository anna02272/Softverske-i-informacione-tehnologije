# numbers2text.py
# Konvertuje niz brojeva u string

print("Ovaj program konvertuje niz brojeva u string sa")
print("tekstom koga reprezentuju ta slova.\n")

inString = input("Unesite niz brojeva: ")

message = ""
for numStr in inString.split():
    codeNum = eval(numStr)
    message = message + chr(codeNum)

print("\nTekst glasi:", message)
