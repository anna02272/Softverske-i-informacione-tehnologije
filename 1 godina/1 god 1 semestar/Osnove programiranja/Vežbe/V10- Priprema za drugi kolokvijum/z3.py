"""3.Napraviti fajl sa nazivom radnici.txt koji u svakom redu sadrži ime radnika, pol i platu. Potom
na osnovu fajla izračunati prosečnu platu svih zaposlenih osoba ženskog pola. Rezultat ispisati
na konzolu.
Primer fajla:
Pera|M|35000
Jova|M|63000
Ana|Z|45000
Maja|Z|50000
Rezultat: 47500"""

def main():
    naziv_fajla = 'radnici.txt'
    nadji_prosecnu_platu(naziv_fajla)


def nadji_prosecnu_platu(naziv_fajla):

    otvori_fajl = open(naziv_fajla, 'r')
    linije = otvori_fajl.readlines()
    brojac = 0
    suma = float()

    for linija in linije:

        if (linija.split("|")[1] == 'Z'):
            brojac = brojac + 1
            suma = suma + float(linija.split("|")[2])

    prosek = suma / brojac
    print("Prosecna plata osoba zenskog pola iznosi", int(prosek))


main()

