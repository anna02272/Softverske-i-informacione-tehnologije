'''Zadatak 10. Napiši funkciju koji datum prima u obliku dd/mm/gggg 
i proverava da li je datum  validan. 
 Na  primer  24/5/1962  je  validan  datum,  
 ali  31/9/2000  nije  jer  septembar  nema  31 
dan. Takođe voditi računa i o tome da li je godina prestupna'''

# dan > 31, mesec > 12   +
# dan == 31, mesec = 1, 3, 5, 7, 8, 10, 12 +
# dan == 29, mesec = 2 + prestupna_godina 


def main():
    datum = input("unesite datum: ")
    rezultat = proveri_datum(datum)
    print("Datum je", rezultat)


def proveri_datum(datum):       # '29/2/2004'
    datum_splitovano = datum.split("/")
    dan = int(datum_splitovano[0])
    mesec = int(datum_splitovano[1])
    godina = int(datum_splitovano[2])
    ispis = ""

    if dan > 31 or mesec > 12:
        ispis = "nevalidan"
    elif dan == 31 and (mesec == 1 or mesec == 3 or mesec == 5 or mesec == 7 or mesec == 8 or mesec == 10 or mesec == 12):
        ispis = "validan"
    elif dan == 29 and mesec == 2 and prestupna(godina) == 0:       # True and True and False -- > False
        ispis = "nevalidan"
    else:
        ispis = "validan"

    return ispis
    
def prestupna(god): # '2/5/2005'
    if god % 100 == 0: # if '2/5/2005' % 100 == 0 ?
        if god % 400 == 0:
            prestupna = 1
        else:
            prestupna = 0
    elif god % 4 == 0:
        prestupna = 1
    elif god % 4 != 0:
        prestupna = 0

    return prestupna

main()







def prestupna_godina(godina):
    if (godina % 100 == 0):
        if (godina % 400 == 0):
            print("Prestupna je")
        else:
            print("Nije prestupna")
    
    else:
        if (godina % 4 == 0):
            print("Jeste prestupna")
        else:
            print("Nije prestupna")


def main():
    godina = int(input("Unesite godine: "))
    prestupna_godina(godina)


main()