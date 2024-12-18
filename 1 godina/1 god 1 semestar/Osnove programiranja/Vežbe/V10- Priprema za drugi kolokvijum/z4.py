"""4.Napiši program koji kombinuje sadržaj dva fajla i snima ga u treći fajl. U prvom fajlu se nalaze
korisnička imena i lozinke prodavaca, a u drugom fajlu se nalaze nizovi cena artikala koje su
prodavci prodali. Pri tome prvom redu u fajlu sa korisničkim imenima i lozinkama odgovara prvi
red u fajlu sa računima, drugom redu u fajlu sa korisničkim imenima i lozinkama odgovara drugi
red u fajlu sa računima itd. Svaki red trećeg fajla treba da sadrži korisničko ime prodavca,
ukupnu cenu robe koju je prodao i prosečnu cenu artikla koji je prodao.
Primer izvršavanja programa:
Ako su dati fajlovi
korisnici.txt
pera|peric
jova|jovic
steva|stevic
i
racuni.txt
100|200|150|150|300|100
50|100|100|50
300|400|200|100|400
Formirani fajl treba da bude:
statistika.txt
pera|1000.0|166.66666666666666
jova|300.0|75.0
steva|1400.0|280.0
Dodatak.Izmeni formatiranje tako da se prikazuju samo prve dve decimale:
statistika.txt
pera|1000.00|166.67
jova|300.00|75.00
steva|1400.00|280.00"""

def main():
    formiraj_statistiku()


def sumiraj(lista_str_vrednosti):
    suma = 0
    
    for el in lista_str_vrednosti:
        suma = suma + int(el)
    
    return suma


def formiraj_statistiku():
    fajl_korisnici = open("korisnici.txt", "r")
    fajl_racuni = open("racuni.txt", "r")
    fajl_statistika = open("statistika.txt", "w")

    linije_korisnika = fajl_korisnici.readlines()
    linije_racuna = fajl_racuni.readlines()

    n = len(linije_korisnika)       # broj linija u korisnici.txt == broj linija u racuni.txt

    for i in range(n):      # i = 0
        linija_korisnik = linije_korisnika[i] # linije_korisnika[0] -> 'pera|peric\n'
        linija_racun = linije_racuna[i]         # '100|200|150|150|300|100\n'
        linija_korisnik = linija_korisnik[0:-1]
        podaci_o_korisniku = linija_korisnik.split("|") # [ 'pera', 'peric' ]
        ime = podaci_o_korisniku[0]
        
        linija_racun = linija_racun[0:-1]
        podaci_o_racunu = linija_racun.split("|") #['100','200','150','150','300','100']
        suma = sumiraj(podaci_o_racunu)
        prosek = suma / len(podaci_o_racunu)

        # "dsafd" + 59 ?
        upis = ime + "|" + str(suma) + "|" + str(round(prosek, 2)) + "\n"
        fajl_statistika.write(upis)


    fajl_korisnici.close()
    fajl_racuni.close()
    fajl_statistika.close()







main()








def main():
    korisnici = 'korisnici.txt'
    racuni = 'racuni.txt'


def pisi_u_fajl(korisnici, racuni):

    fajl_korisnici = open(korisnici, 'r')
    fajl_racuni = open(racuni, 'r')

    linije_korisnici = fajl_korisnici.readlines()
    linije_racuni = fajl_racuni.readlines()

    fajl_racuni.close()
    fajl_korisnici.close()

    statistika = 'statistika.txt'
    fajl_statistika = open('statistika.txt', 'w')

    for j in range(0, len(linije_korisnici)):
        suma = 0
        # uglasta je zato sto SVAKU liniju splitujes po kosoj crti
        temp = linije_racuni[j].split("|")

        for i in temp:
            suma = suma + int(i)
            formatirana_suma = '{:.2f}'.format(suma)

        prosek = suma / len(temp)
        formatiran_prosek = '{:.2f}'.format(prosek)

        fajl_statistika.write(linije_korisnici[j].split(
            "|")[0] + "|" + str(formatirana_suma) + "|" + str(formatiran_prosek))
        fajl_statistika.write("\n")


main()