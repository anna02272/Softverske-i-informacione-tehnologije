"""Zadatak 2.
Sekvenca Sirakuza se računa počevši od prirodnog broja x kao:
Napiši funkciju koja prima inicijalnu vrednost x i
 vraća listu sa Sirakuza sekvencom za tu vrednost.
Primer izvršavanja programa:
>>> print(sirakuza(5))
[5,16, 8, 4, 2, 1]"""


def main():
    a = eval(input("Molim molim"))
    konacno = izracunajdo(a)
    print(konacno)


def izracunajdo(broj):
    lista = []
    lista.append(broj)

    '''if broj % 2 != 0:
        broj = broj*3 + 1
        lista.append(broj)'''

    while broj > 1:
        if broj % 2 != 0:
            broj = broj*3 + 1
            lista.append(broj)
        broj = broj / 2
        lista.append(broj)
        
        '''if broj % 2 == 0:
            broj = broj / 2
        else:
            broj = 3 * broj + 1
        lista.append(broj)'''




    return lista



main()


def main():
    x = eval(input("Unesite x: "))
    lista = sirakuza(x)
    print(lista)



def sirakuza(x):
    lista = []
    lista.append(x)

    while (x != 1 ):
        if (x % 2 == 0):
            x = x // 2
        else:
            x = (3 * x) + 1 
        
        lista.append(x)
    
    return lista 
    
main()