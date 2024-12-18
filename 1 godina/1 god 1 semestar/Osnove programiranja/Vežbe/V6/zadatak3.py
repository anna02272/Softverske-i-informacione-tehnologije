'''Zadatak 3.Napiši program za registrovanje korisnika. Program teba da omogući korisniku da
unese korisničko ime i lozinku. Informacije o korisniku čuvaju se u tekstualnom fajlu.
Primer izvršavanja programa:
Nakon 3 navedena izvršavanja programa:
korisnicko ime:pera
lozinka: peric
>>> ================================ RESTART
================================ >>>
korisnicko ime: jova
lozinka: jovic
>>> ================================ RESTART
================================ >>>
korisnicko ime: steva
lozinka: stevic
>>> Fajl korisnici.txt treba da sadrži sledeće podatke:
pera|peric
jova|jovic
steva|stevic'''

def main():
    korisnicko_ime = input("Unesite korisnicko ime: ")
    lozinka = input ("Unesite lozinku: ")

    fajl = open("registracija.txt", "a")
    novi_string = korisnicko_ime + "|" + lozinka + "\n"
    fajl.write(novi_string)

    fajl.close()

main()


def main():
    username = input("Unesite korisnicko ime: ")
    password = input("Unesite lozinku: ")
    save(username,password)


def save(username,password):
    file = open('users.txt','a')
    file.write(username + "/" + password + "\n")


main()