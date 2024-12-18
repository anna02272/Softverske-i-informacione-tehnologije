def main():
    ocena1, ocena2, ocena3 = eval(input("Unesi tri ocene: "))
    prosek = (ocena1 + ocena2) / 2
    prosek2 = (ocena1 + ocena2 + ocena3) / 3
    prosek2 = round(prosek2, 2)
    print("Prosek ocena je", round(prosek, 2))
    print("Prosek tri ocene je", prosek2)


main()