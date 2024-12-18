# text2numbers.py
# Konvertuje string u niz brojeva, prema kodnom rasporedu

print("Ovaj program konvertuje uneti tekst u niz brojeva sa kodovima")
print("koji odgovaraju pojedinacnim slovima.\n")

message = input("Unesite tekst: ")

print("\nOvo su kodovi slova:")

for ch in message:
    print(ord(ch), end=" ")
    
print()
