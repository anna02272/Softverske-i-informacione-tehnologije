'''Napiši funkciju koji obračunava zaradu radnika. 
Radnici koji rade više od 40 sati nedeljno su plaćeni 1.5 puta više
 nego što bi bili plaćeni da rade do 40 sati nedeljno. Broj radnih
sati po danima završene radne nedelje se učitava iz fajla. 
Funkcija kao parametar prima putanju do fajla sa radnim satima i
 cenu radnog sata i ispisuje imena i zarade radnika.
Primer izvrašavanja programa: 
Za fajl radnici.txt 
pera|8|9|7|8|9 
jova|8|8|7|8|7 
steva|8|8|9|8|7 
rezultat poziva funkcije treba da bude: 
>>> racunanjeZarade("radnici.txt",1000) 
ime: pera 
zarada: 61500.0 
ime: jova 
zarada: 38000 
ime: steva 
zarada: 40000 '''


# korisnik|admin123

def main():
    naziv = input("Unesite naziv fajla: ")
    cena = eval(input("Unesite cenu radnog sata: "))
    obracunaj_zaradu_radnika(naziv, cena)
    
def racunaj_zaradu(lista_sati, cena_radnog_sata):
    zarada = 0
    ukupni_sati_u_nedelji = 0

    for i in lista_sati:
        ukupni_sati_u_nedelji = ukupni_sati_u_nedelji + int(i) 

    if ukupni_sati_u_nedelji > 40:
        zarada = ukupni_sati_u_nedelji * cena_radnog_sata * 1.5
    else:
        zarada = ukupni_sati_u_nedelji * cena_radnog_sata 

    return zarada

def obracunaj_zaradu_radnika(naziv_fajla, cena):
    fajl = open(naziv_fajla, "r")
    linije = fajl.readlines()       # ['pera|8|9|7|8|9\n', 'jova|8|8|7|8|7\n', 'steva|8|8|9|8|7\n' ]

    for linija in linije:
        linija = linija[0:-1]   # 'pera|8|9|7|8|9'
        podaci_o_radniku = linija.split("|")    # ['pera', '8', '9', '7', '8', '9']
        ime_radnika = podaci_o_radniku[0]
        sati = podaci_o_radniku[1:]             # ['8', '9', '7', '8', '9']
        zarada = racunaj_zaradu(sati, cena)
        print("ime: ", ime_radnika)
        print("zarada:", zarada)

main()







def main():
    satnica = eval(input("Unesite satnicu: "))
    fajl = 'radnicii.txt'
    obracunaj_zaradu(satnica, fajl)


def obracunaj_zaradu(satnica, fajl):
    otvori_fajl = open(fajl, 'r')
    linije = otvori_fajl.readlines()
    sum = 0
    otvori_fajl.close()
    for linija in linije:
        sum = int(linija.split("|")[1]) + int(linija.split("|")[2]) + int(
            linija.split("|")[3]) + int(linija.split("|")[4]) + int(linija.split("|")[5])
        print("Suma radnih sati za " +
              str(linija.split("|")[0]) + " je " + str(sum))

        if (sum > 40):
            plata = (sum * satnica) * 1.5
            print("Prekovremeno")
            print("Plata iznosi", plata)
        else:
            plata = (sum * satnica)
            print("Nije prekovremeno ")
            print("Plata iznosi", plata)


main()