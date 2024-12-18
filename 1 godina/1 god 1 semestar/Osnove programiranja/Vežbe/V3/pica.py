import math

def main():

    pi = math.pi
    r = eval(input("unesite poluprecnik: "))
    x = eval(input("unesi cenu pice: "))
    P = r ** 2 * pi
    y = x / P

    print("Povrsina je: ", P)
    print("Cena pola pice je:", y)


main()




import math

def main():

    r = eval(input("Unesite poluprecnik(cm): "))
    u = eval(input("Unesite cenu cele pice: "))
    P = math.pi * r**2
    x = u / P

    print("Cena po cm je: ", x)

main()