# -+- encoding: utf-8 -+-
from queue import Queue

def merge(L, R):
    """
    Funkcija spaja dve sortirane liste u jednu rezultujuću

    Argumenti:
    - `L`: `leva` lista
    - `R`: `desna` lista
    """
    # broj elemenata u listama
    n = len(L)
    m = len(R)

    # indeksi listi L i R, respektivno
    i = 0
    j = 0

    # izlazna lista
    sorted = []

    # dokle god u obe liste ima neispitanih elemenata, proveravaj tekuće
    while i < n and j < m:
        if L[i] < R[j]:
            # element `leve` liste je manji, dodaj u sortiranu i pomeri indeks
            sorted.append(L[i])
            i += 1
        else:
            # element `desne` liste je manji, dodaj u sortiranu i pomeri indeks
            sorted.append(R[j])
            j += 1

    # u jednoj od listi je ostalo elemenata, proveri koja je lista i kopiraj
    # preostale elemente u rezultujuću listu
    if i < n:
        sorted.extend(L[i:])
    else:
        sorted.extend(R[j:])

    return sorted


def merge_sort(array):
    """
    Merge sort algoritam

    Argument:
    - `array`: lista za sortiranje
    """
    # bazni slučaj (lista od jednog elementa)
    n = len(array)
    if n == 1:
        return array

    # prepolovi listu i sortiraj polovine
    mid = n//2
    L = merge_sort(array[:mid])
    R = merge_sort(array[mid:])

    # spoji liste i vrati rezultat spajanja
    return merge(L, R)

def merge_v2(S1, S2, S):
    while not S1.is_empty() and not S2.is_empty():
        if S1.first() < S2.first():
            S.enqueue(S1.dequeue())
        else:
            S.enqueue(S2.dequeue())
    while not S1.is_empty():    # dodaj preostale elemente S1 u S
        S.enqueue(S1.dequeue())
    while not S2.is_empty():    # dodaj preostale elemente S2 u S
        S.enqueue(S2.dequeue())
     
def merge_sort_v2(S):
    n = len(S)
    if n < 2:
        return  # lista je već sortirana
    # podeli
    S1 = Queue()
    S2 = Queue()
    while len(S1) < n // 2: # dodaj prvu polovinu elemenata u S1
        S1.enqueue(S.dequeue())
    while not S.is_empty(): # dodaj ostatak elemenata u S2
        S2.enqueue(S.dequeue())
    # vladaj
    merge_sort_v2(S1)       # sortiraj prvu polovinu
    merge_sort_v2(S2)       # sortiraj drugu polovinu
    merge_v2(S1, S2, S)     # spoji sortirane polovine u S

def merge_v3(S1, S2, S):
    i=j=0
    while i + j < len(S):
        if j == len(S2) or (i < len(S1) and S1[i] < S2[j]):
            S[i+j] = S1[i] # kopiraj i-ti element S1 kao sledeći element S
            i += 1
        else:
            S[i+j] = S2[j] # kopiraj j-ti element S2 kao sledeći element S
            j += 1

def merge_sort_v3(S):
    n = len(S)
    if n < 2:
        return
    # divide
    mid = n // 2
    S1 = S[0:mid]
    S2 = S[mid:n]
    # conquer
    merge_sort_v3(S1)
    merge_sort_v3(S2)
    # merge
    merge_v3(S1, S2, S)

if __name__ == "__main__":
    array = [2, 6, 7, 8, 1, 3, 9, 9, 4]
    #print(merge_sort(array))
    merge_sort_v3(array)
    print(array)

    # q = Queue()
    # q.enqueue(2)
    # q.enqueue(6)
    # q.enqueue(7)
    # q.enqueue(8)
    # q.enqueue(1)
    # q.enqueue(3)
    # q.enqueue(9)
    # q.enqueue(9)
    # q.enqueue(4)
    # merge_sort_v2(q)
    # print(q._data)