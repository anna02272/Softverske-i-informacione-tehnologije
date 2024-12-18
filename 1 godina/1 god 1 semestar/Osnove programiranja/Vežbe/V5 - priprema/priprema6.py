def main():
    a = eval(input("Unesite broj:"))
    b = eval(input("Unesite broj:"))
    suma = 0
    c = 0
    for i in range(a, b+1):
        suma = suma + 1
        c = c + 1

    x = b - a + 1
    prosek = suma / c
    print(prosek)

main()