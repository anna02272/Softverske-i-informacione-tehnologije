"""7. Omogućiti korsniku da unosi brojeve, ukoliko je broj deljiv sa 4 dodati ga u sumu. Kada suma
pređe 1000 program se završava i ispisuje korisniku rezultat"""

def main():
    petlja()


def petlja():
    suma = 0
    while (suma < 1000):
        num = eval(input("Unesite broj: "))

        if (num % 4 == 0):
            suma = suma + num
    print(suma)


main()