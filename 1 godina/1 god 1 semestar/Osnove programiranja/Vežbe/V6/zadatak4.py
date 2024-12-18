'''Zadatak 4.Napiši program koji učitava iz tekstulanog fajla korisnička imena i ispisuje ih. Za fajl
korisnici.txtiz prethodnog zadatka nakon izvršavanja programa treba da bude prikazano:
korisnicko ime: pera
lozinka: peric
korisnicko ime: jova
lozinka: jovic
korisnicko ime: steva
lozinka: stevic'''

def ucitaj_iz_fajla(fajl):
    ucitane_linije = fajl.readlines()
    return ucitane_linije

def main():
    fajl = open("registracija.txt", "r")
    ucitane_linije = ucitaj_iz_fajla(fajl)
    print(ucitane_linije)

    for linija in ucitane_linije:
        reci = linija.split("|")
        korisnicko_ime = reci[0]
        lozinka = reci[1]
        lozinka = lozinka [0:-1]    
        #lozinka = lozinka.replace("\n", "")
        print("korisnicko ime:", korisnicko_ime)
        print("lozinka:",lozinka)


main()