def main():
    fahrenheit = eval(input("Unesite broj stepeni u Farenhajtima: "))
    celzius = (fahrenheit - 32) / 1.8
    print("Temperatura u farenhajtima je", fahrenheit)
    celzius = round(celzius, 2)
    print("Temperatura u celzijusima je", celzius)


main()