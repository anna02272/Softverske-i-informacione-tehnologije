# 5/1 + 5/4 + 5/9 + 5/16 + 5/25 + 5/36 + ...

def main():
    n = eval(input("Unesite broj elemenata koje treba sabirati:"))
    suma = 0

    for i in range(1, n + 1 ):       # i = 1,2,3,4,5
        print(i)
        suma = suma + 5 / i ** 2
        print("*" * 50)

    print(suma)



main()