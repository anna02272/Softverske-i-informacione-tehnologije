"""2.Napiši program koji od korisnika traži da unosi brojeve sve dok ne unese neparni broj. Kada
korisnik unese neparni broj, izvršavanje programa se prekida i korisniku se ispisuje suma
prethodno unetih (parnih) brojeva"""

def main():
    num = eval(input("Unesite broj"))
    neparni(num)


def neparni(num):

    sum = 0

    while (num % 2 == 0):
        sum = sum + num
        num = eval(input("Unesite broj: "))

    print("Suma parnih brojeva iznosi", sum)


main()