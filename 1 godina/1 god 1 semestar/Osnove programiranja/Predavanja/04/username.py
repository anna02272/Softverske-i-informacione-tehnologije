# username.py
# Generise korisnicko ime od imena i prezimena korisnika

print("Ovaj program generise username.\n")

# unesi ime i prezime
first = input("Unesite svoje ime (malim slovima): ")
last = input("Unesite svoje prezime (malim slovima): ")

# prvo slovo imena + prvih 7 slova prezimena
uname = first[0] + last[:7]

# ispisi username
print("Vas username je:", uname)
