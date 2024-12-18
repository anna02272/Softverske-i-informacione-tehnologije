
# -*- coding: utf-8 -*-
"""
Program izračunava maksimalni element liste rekurzivnim putem.
"""

#naći maksimalni element niza rekurzivnim putem, funkciji se prosleđuju niz i njegova dužina


def solution(lista):
    if len(lista) == 1:
        return lista[0]
    else:
        el = solution(lista[1:])
        if el > lista[1]:
            return el
        else:
            return lista[1]

lista = [88, 2, 123, 4, 11, 10, 19]
print(solution(lista))

def find_max_with_len(seq, n):
    """
    Funkcija izračunava maksimalni element liste rekurzivnim putem.

    Argumenti:
    - `seq`: lista čiji maksimum se traži
    - `n`: broj preostalih elemenata za poređenje
    """

    # bazni slučaj
    if n == 1:
        return seq[0]
    else:
        max_remaining_el = find_max_with_len(seq, n-1)
        if max_remaining_el > seq[n-1]:
            return max_remaining_el
        else:
            return seq[n-1]


def find_max(seq):
    """
    Funkcija izračunava maksimalni element liste rekurzivnim putem.

    Argumenti:
    - `seq`: lista čiji maksimum se traži
    - `n`: broj preostalih elemenata za poređenje
    """

    # bazni slučaj
    if len(seq) == 1:
        return seq[0]
    else:
        max_remaining_el = find_max(seq[1:])
        if max_remaining_el > seq[1]:
            return max_remaining_el
        else:
            return seq[1]


if __name__ == '__main__':
    seq = [88, 2, 123, 4, 11, 10, 19]
    length = str(len(seq))
    print("Solution with 2 params: max(" + length + ") = " +
          str(find_max_with_len(seq, len(seq))))
    print("Solution with 1 param: max(" + length + ") = " + str(find_max(seq)))
