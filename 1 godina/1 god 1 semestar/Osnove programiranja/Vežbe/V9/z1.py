'''Zadatak1. Koristeći while petlju napiši funkciju koji određuje 
koliko godina treba da prođe dok
se u uloženi novac u banku ne udvostruči.
 Funkcija kao parametar prima kamatnu stopu na
godišnjem nivou, a vraća broj godina.
Primer izvršavanja programa:
>>> print(brojGodina(0.04))
18'''

def main():
    kamata = eval(input("Unesite kamatu u procentima: "))
    iznos = eval(input("Unesite iznos: "))
    udvostrucen_novac(kamata, iznos)


def udvostrucen_novac(kamata, iznos):
    dupli_iznos = iznos * 2

    novi_iznos = iznos + (iznos / 100) * kamata
    godina = 1

    while (novi_iznos < dupli_iznos):
        godina = godina + 1
        novi_iznos = novi_iznos + (novi_iznos / 100) * kamata
    print("Broj godina potreban da se novac udvostruci je ", godina)


main()