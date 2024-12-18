"""5.Napiši program za evidenciju studenata. Za svakog studenta unosi se: ime, prezime, broj
indeksa i godina studija. Podaci za svakog studenta se čuvaju u jednom redu u fajlu, u formatu
ime|prezime|indeks|godina_studija. Ime i prezime moraju da počinju velikim slovom. Indeks
mora da bude jedinstven (ako se ponovi ista vrednost za indeks, omogućiti korisniku da ponovo
unese).
Primer nakon 2 unosa sa tastature:
Ime: Mihajlo
Prezime: Pupin
Broj indeksa: AA15
Godina upisa: 1885
------------------------------
Ime: ervin
Prezime: sredinger
Broj indeksa: BB18
Godina upisa: 1906

Studenti.txt
Mihajlo|Pupin|AA15|1885
Ervin|Sredinger|BB18|1906"""

def main():
    ime = veliko_slovo(input("Unesite ime: "))
    prezime = veliko_slovo(input("Unesite prezime: "))
    broj_indeksa = proveri_indeks(input("Unesite broj indeksa: "))
    godina_studija = input("Unesite godinu studija: ")
    pisi(ime, prezime, broj_indeksa, godina_studija)


def veliko_slovo(ime):
    if (ime.istitle() == False):
        ime = input("Unesite ponovo ime: ")

    return ime


def proveri_indeks(indeks):
    fajl = open('evidencija.txt', 'r')
    data = fajl.read().replace("\n", "|").split("|")

    while indeks in data:
        indeks = input("Unesite indeks ponovo, ovaj vec postoji: ")
    return indeks


def pisi(ime, prezime, broj_indeksa, godina_studija):
    fajl = open('evidencija.txt', 'a')
    fajl.write("\n" + ime + "|" + prezime + "|" +
               broj_indeksa + "|" + godina_studija)

    fajl.close()

    print("Uspesno dopisano!")


main()