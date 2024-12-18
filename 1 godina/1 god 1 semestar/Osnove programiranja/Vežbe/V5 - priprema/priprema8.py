def main():
    recenica = input("Unesite recenicu:")
    reci = recenica.split(" ")
    broj_reci = len(reci)
    invertovani_ispis = ""

    for i in range( broj_reci - 1, -1 , -1):
        invertovani_ispis = invertovani_ispis + reci[i] + "#"

    print(invertovani_ispis[:-1])
main()