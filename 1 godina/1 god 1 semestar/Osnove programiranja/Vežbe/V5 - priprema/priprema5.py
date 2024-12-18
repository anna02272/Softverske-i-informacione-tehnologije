import math
def main():
    n = eval(input("Unesite n:"))
    zbir = 0
    for i in range(1, n+1):
        zbir = zbir + i
        print(i)
    print(zbir)
   
    x = 1 + 2 + 3 + 4 + 5 + 6
    print(x) 

  

   

main()
import math

def main():

    n= eval(input("Unesite pocetak niza "))

    c= eval(input("Unesite pocetak niza "))

    zbir = 0

    b = 0

    for i in range(n,c+1):

        zbir=zbir+i

        b = b+1

    kzbir = zbir + b

    print("Ukupan zbir prirodnih brojeva je: ", kzbir)

main()