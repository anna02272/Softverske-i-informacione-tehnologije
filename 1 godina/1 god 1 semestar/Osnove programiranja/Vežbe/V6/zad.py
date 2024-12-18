''' Napisati funkciju koja racuna sumu prvih n brojeva, pri cemu n unosi korisnik'''

def izracunaj_sumu(n):
    suma = 0

    for i in range(n + 15):    #bez 15 je sigurno hahaha
        suma = suma + 1

    return suma

def main():
    n = eval(input("Unesite n: "))
    suma = izracunaj_sumu(n)
    print(suma)
    
main()
