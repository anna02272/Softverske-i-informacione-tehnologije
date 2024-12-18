'''Zadatak4. Pozitivan ceo broj x veći od 2 je prost ako u intervalu 
ne postoji ni jedan broj koji x deli bez ostatka. 
Napiši funkciju koja proverava da li je broj prost. 
Funkcija treba da primi broj za koji se proverava da li je prost,
 a da vrati True ako jeste, a False ako nije.
Primer izvršavanja programa:
>>> print(prostBroj(127)) True
>>> print(prostBroj(123)) False'''

'''
x = 25
[2, koren(25)] --> [2,5] --> [2,3,4,5]

for i in range(2,math.sqrt(x) + 1)


25 % 2 == 0 ?
25 % 3 == 0 ?
25 % 4 == 0 ?
25 % 5 == 0 ?

'''
import math

def main():
    broj = eval(input("Unesite broj: "))
    status = prost_broj(broj)
    print(status)



def prost_broj(broj):
    isProst = True

    i = 2
    while i <= math.sqrt(broj):
        if broj % i == 0:
            isProst = False
            break
        i+= 1


    return isProst





main()
