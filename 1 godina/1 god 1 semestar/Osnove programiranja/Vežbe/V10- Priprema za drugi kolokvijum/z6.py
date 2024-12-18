"""6.Omogućiti korisniku da unosi imena u listu. Imena se unose sve dok korisnik ne unese ‘q’.
Nakon prekida unosa imena sortirati ih po abecednom redu, potom pozvati funkciju koja
izbacuje iz liste imena koja počinju na slovo A ili se završavaju na slovo A/a"""

def main():
    ime = input("Unesite ime: ")
    list = []

    while (ime != 'q'):
        list.append(ime)
        ime = input("Unesite ime: ")
        list.sort()
    print(list)
    izbaci_lista(list)
    print(list)


def izbaci_lista(list):
    i = 0

    while (i != len(list)):
        el = list[i]

        if ((el[-1] == 'a') or (el[-1] == 'A') or (el[0] == 'A')):
            list.remove(el)
        else:
            i = i + 1

    return list


main()