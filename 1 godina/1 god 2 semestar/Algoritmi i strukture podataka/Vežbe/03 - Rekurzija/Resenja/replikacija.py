# -*- coding: utf-8 -*-
"""
Program za replikaciju.
"""

import time

#napisati rekurzivnu funkciju za replikaciju elemenata, funkcija ima dva parametra - prvi je broj koji treba umnožiti, a drugi broj ponavljanja
def replikacija(broj, broj_ponavljanja):
    if broj_ponavljanja == 1:
        return [broj]
    else:
        return [broj] + replikacija(broj, broj_ponavljanja-1)




print(replikacija(5, 6))
print(replikacija(2, 4))

def replicate(number, times):
    """
    Funkcija umnožava zadati element zadati broj puta.

    Argumenti:
    - `number`: element koji se umnožava
    - `times`: broj umnožavanja
    """

    # bazni slučaj
    if times == 0:
        return []
    else:
        return [number] + replicate(number, times-1)


if __name__ == '__main__':
    start_time = time.time()
    result = replicate(5, 6)
    end_time = time.time()

    passed = end_time-start_time
    print('Elapsed time: %fs' % passed)
    print('Solution:' + str(result))
