'''8. Napiši program za registrovanje novih proizvoda u prodavnici. Prodavac može da doda novi
proizvod, pri čemu unosi naziv, cenu i raspoloživu količinu proizvoda. Nakon što je dodat novi
proizvod ispisuje se spisak svih proizvoda u prodavnici. Dodavanje proizvoda se ponavlja dok
prodavac ne unese „quit“. Ukoliko prodavac unese „quit“ bilo za naziv, cenu ili količinu
proizvoda, prekida se izvršavanje programa. Nazivi, cene i količine proizvoda čuvaju se u fajlu
proizvodi.txt.'''

def main():
    naziv = input("Unesite naziv proizvoda: ")
    cena = input("Unesite cenu proizvoda: ")
    kolicina = input("Unesite kolicinu proizvoda: ")

    while (naziv != 'quit') and (cena != 'quit') and (kolicina != 'quit'):
        upisi(naziv, cena, kolicina)
        izlistaj()
        naziv = input("Unesite naziv: ")
        cena = input("Unesite cenu proizvoda: ")
        kolicina = input("Unesite kolicinu proizvoda: ")


def upisi(naziv, cena, kolicina):
    fajl = open('proizvodi.txt', 'a')
    fajl.write(naziv + "|" + cena + "|" + kolicina + "\n")
    fajl.close()


def izlistaj():
    fajl = 'proizvodi.txt'
    fajl = open('proizvodi.txt', 'r')
    linije = fajl.readlines()
    for linija in linije:
        print(linija)
    fajl.close()


main()