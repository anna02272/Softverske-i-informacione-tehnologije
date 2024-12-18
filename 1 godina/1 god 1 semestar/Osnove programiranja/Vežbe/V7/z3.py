"""Zadatak 3. Napiši program koji za zadati niz brojeva: 
 Ispisuje najmanji element niza 
 Ispisuje najveći element niza 
 Ispisuje sumu vrednosti u nizu 
 Ispisuje srednju vrednost za niz 
Svaki od zadatih zahteva trebada bude implementiran kao
 posebna funkcija. 
Primer izvršavanja programa: 
 
>>> karakteristikeNiza([1,2,3,4,5,-1,6]) 
najmanji element niza je: -1 
najveci element niza je: 6 
suma elemenata niza je: 20 
prosek elemenata niza je: 2.857142857142857
"""
def main():
    niz = [1,2,3,4,5,-1,6]
    print("Najmanji element niza je",najmanji(niz))
    print( "Najveci element niza je",najveci(niz))
    print("Suma niza je",suma(niz))
    print("Srednja vrednost niza je",srednja_vrednost(niz))


def najmanji(niz):
    minimalni = min(niz)
    return minimalni


def najveci(niz):
    maksimalni = max(niz)
    return maksimalni 

def suma(niz):
    zbir = sum(niz)
    return zbir 

def srednja_vrednost(niz):
    srednja = round( sum(niz) / len(niz),2)
    return srednja 




main()