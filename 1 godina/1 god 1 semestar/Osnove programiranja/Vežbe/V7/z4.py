"""Zadatak  4.  Kazna za brzu vožnju računa se kao 5000 din +
 500 din za svaki kilometar preko 
ograničenja + 10000 din za vožnju preko 120km/h.
 Napisati funkciju koja prima izmerenu 
brzinu vozila i ograničenje brzine.
 Ako je brzina veća od dozvoljene funkcija vraća poruku sa 
cenom kazne, a ako je manja vraća poruku da je sve u redu. 
 
Primer izvršavanja programa: 
 
>>> print(kazna(80,60)) 
vasa kazna iznosi 15000din  
>>> print(kazna(50,60)) 
niste prekoracili brzinu  
>>> print(kazna(130,60)) 
vasa kazna iznosi 50000din """

def main():
    brzina = eval(input("Unesite brzinu: "))
    ogranicenje = eval(input("Unesite ogranicenje: "))
    print(kazna(brzina,ogranicenje))
    


def kazna(brzina,ogranicenje):
    if (brzina > ogranicenje):
        kazna = 5000 + (brzina - ogranicenje) * 500
        return kazna 
    elif (brzina > ogranicenje) and (brzina > 120):
        kazna = (5000 + (brzina - ogranicenje ) * 500 ) + 10000
        return kazna 
    else:
         return "Nema kazne"   
    



main()
