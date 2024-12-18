import math

def main():
    # suma prvih 5  brojeva
    suma = 0
    for i in range(2, 11, 2):
        print(i)
        print("Prethodna suma", suma)
        suma = suma + i
        print("suma je", suma)
        print()

    print("Ukupna suma je", suma)


main()
