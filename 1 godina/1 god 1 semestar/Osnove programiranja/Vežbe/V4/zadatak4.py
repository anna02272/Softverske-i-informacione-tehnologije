def main():

    Prvi_string = input("Unesite prvi string: ")
    Drugi_string = input("Unesite drugi string: ")

    Prvi = Prvi_string[0:3]
    Drugi = Drugi_string[-3:]

    Rezlutat = (Prvi * 2 ) + Drugi
    print(Rezlutat)


main()