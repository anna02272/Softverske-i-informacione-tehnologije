"""Zadatak 1. Kvadratna jednačina zadata je formatom            . Koreni kvadratne jednačine
računaju se kao               . Napisati funkciju koja prima parametre a, b, c i vraća
rešenja kvadratne jednačine. Pri tome se pretpostavlja da postoje realni koreni kvadratne
jednačine."""

import math

def main():
    a = eval(input("Unesite parametar a: "))
    b = eval(input("Unesite parametar b: "))
    c = eval(input("Unesite parametar c: "))
    print(jednacina(a, b, c))


def jednacina (a, b, c):
    x1 = (- b + math.sqrt((b**2) - (4*a*c)))/2*a
    x2 = (- b - math.sqrt((b**2) - (4*a*c)))/2*a
    return x1, x2

main()