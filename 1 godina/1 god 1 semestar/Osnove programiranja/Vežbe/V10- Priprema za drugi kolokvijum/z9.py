'''9. Stanje na računu. Iz log fajla (bank_log.txt) 
čitati podatke o korisnicima. Podaci su dati u
obliku: ime_korisnika tip_akcije iznos.
Tipovi akcija su sledeći:
income - priliv sredstava na korisnikov račun
withdrawal - korisnik je podigao novac sa računa
-> Za sve korisnike odrediti stanje na računu.
-> Kreirati izveštaj stanja na računu za unetog korisnika. 
Izveštaj ispisati na konzoli u
proizvoljnom formatu

10. Dodatak na zadatak 9. Ispisati korisnike u novi fajl 
 sa trenutnim stanjem na računu.

pera income 1200
jova income 40000
pera withdrawal 362
ana income 500
sava withdrawal 5000
marija income 25000
ana withdrawal 16320
jova withdrawal 150
ana withdrawal 9640
pera income 800
sava income 1500
'''

# [ ['pera' , 46865] , ['jova' , 45] , ['ana' , 45] , ['sava' , 45]    ]


def main():
    naziv = input("Unesite naziv fajla: ")
    linije_fajla = ucitaj_iz_fajla(naziv)
    lista_korisnika = balans_svih_korisnika(linije_fajla)
    print(lista_korisnika)
    ime = input("Unesite ime korisnika: ")
    formiraj_izvestaj(ime, linije_fajla)


def ucitaj_iz_fajla(naziv_fajla):
    linije = []
    fajl = open(naziv_fajla, "r")
    linije = fajl.readlines()

    fajl.close()
    return linije

'''
ana income 500
ana withdrawal 16320
ana withdrawal 9640


'''
def formiraj_izvestaj(ime, linije):

    for linija in linije:
        linija = linija[0:-1]               # 'pera income 1200'
        podaci_o_korisniku = linija.split(" ")
        ime = podaci_o_korisniku[0]
        akcija = podaci_o_korisniku[1]
        iznos = podaci_o_korisniku[2]
        if ime in podaci_o_korisniku:   # if 'ana' == podaci_o_korisniku[0]
            print(linija)
            print(ime, akcija, iznos)



def stanje_na_racunu_jednog_korisnika(ime, linije):
    stanje = 0

    for linija in linije:
        linija = linija[0:-1]               # 'pera income 1200'
        podaci_o_korisniku = linija.split(" ")
        ime_korisnika = podaci_o_korisniku[0]
        akcija = podaci_o_korisniku[1]
        iznos = int(podaci_o_korisniku[2])
        if ime_korisnika == ime:
            if akcija == "income":
                stanje = stanje + iznos
            else:
                stanje = stanje - iznos

    return stanje


def balans_svih_korisnika(linije_fajla):
    jedinstveni_korisnici = []          # ['pera', 'jova' ]
    stanje_svih_korisnika = []
    
    for linija in linije_fajla:             # 'pera withdrawal 362\n'
        linija = linija[0:-1]               # 'pera withdrawal 362'
        podaci_o_korisniku = linija.split(" ")      # ['pera', 'withdrawal', '362']
        ime_korisnika = podaci_o_korisniku[0]       # 'pera'
        if ime_korisnika not in jedinstveni_korisnici: # jova u jedinstveni_korisnici ?
            jedinstveni_korisnici.append(ime_korisnika) # jova dodaje u jednistveni_korisnici
            stanje = stanje_na_racunu_jednog_korisnika(ime_korisnika , linije_fajla)
            mini_lista = []     # cuva ime i stanje jednog korisnika
            mini_lista.append(ime_korisnika)
            mini_lista.append(stanje)
            stanje_svih_korisnika.append(mini_lista)


    return stanje_svih_korisnika



main()













def main():
    fajl = 'bank_log.txt'
    odredi_stanje(fajl)


def odredi_stanje(fajl):
    otvori = open(fajl, 'r')
    linije = otvori.readlines()

    svi_korisnici = []

    for linija in linije:
        svi_korisnici.append(linija.split(" ")[0])
    otvori.close()

    jedinstveni_korisnici = odredi_jedinstvene(svi_korisnici)
    fajl = open('korisnici.txt','w')

    for korisnik in jedinstveni_korisnici:
        stanje = float()
        for linija in linije:
            if (korisnik == linija.split(" ")[0]):
                if (linija.split(" ")[1] == 'withdrawal'):
                    stanje = stanje - float(linija.split(" ")[2])
                else:
                    stanje = stanje + float(linija.split(" ")[2])
        fajl.write(korisnik + " " + str(stanje) + "\n")
        print(korisnik, stanje)



def odredi_jedinstvene(korisnici):
    jedinstveni_korisnici = []
    for korisnik in korisnici:
        unique = True
        for jedinstven_korisnik in jedinstveni_korisnici:
            if (korisnik == jedinstven_korisnik):
                unique = False

        if unique:
            jedinstveni_korisnici.append(korisnik)

    return jedinstveni_korisnici


main()












def main():
    fajl = 'bank_log.txt'
    korisnik = input("Unesite korisnika: ")
    stanje_na_racunu(fajl, korisnik)


def stanje_na_racunu(fajl, korisnik):
    fajl = 'bank_log.txt'
    otvori_fajl = open(fajl, 'r')
    linije = otvori_fajl.readlines()
    stanje = float()
    otvori_fajl.close()

    stanje = float()
    for linija in linije:
        if (linija.split(" ")[0] == korisnik):
            if (linija.split(" ")[1] == 'withdrawal'):
                stanje = stanje - float(linija.split(" ")[2])
            else:
                stanje = stanje + float(linija.split(" ")[2])
    print(korisnik, stanje_na_racunu)


main()