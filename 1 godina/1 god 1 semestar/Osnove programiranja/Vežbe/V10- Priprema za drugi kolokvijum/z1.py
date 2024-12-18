"""1.Omogućiti korisniku da unosi reči sve dok ne unese reč koja se
 završava slovom a. Potom
ispisati na konzolu ukupan broj pogrešnih unosa.
Primer izvršavanja:
>> ispit
>> programiranje
>> ocena
>> Broj pogrešnih unosa: 2"""

def main():
    rec = input("Unesite rec: ")
    pogresan_unos(rec)


def pogresan_unos(rec):
    sum = 0

    while (rec[-1] != 'a'):
        rec = input("Unesite rec:")
        sum = sum + 1

    print("Broj pogresnih unosa je", sum)


main()



def main():
    br = 0
    while True:
         user_input = input("Unesi rec")
         if user_input[-1] == 'a':
             break
         else:
             br += 1
    print("Brojac je: {0}".format(br))
 
main()