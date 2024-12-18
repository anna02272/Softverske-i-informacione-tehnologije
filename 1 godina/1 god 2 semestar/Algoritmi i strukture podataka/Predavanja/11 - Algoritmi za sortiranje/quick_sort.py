# -+- encoding: utf-8 -+-
from queue import Queue

def inplace_quick_sort(S, a, b):
    if a >= b: return   # red je sortiran
    pivot = S[b]        # poslednji element opsega je pivot
    left = a            # skenira na desno
    right = b-1         # skenira na levo
    while left <= right:
        # skenira dok ne dođe do vrednosti >= od pivota
        while left <= right and S[left] < pivot:
            left += 1
        # skenira dok ne dođe do vrednosti <= od pivota
        while left <= right and pivot < S[right]:
            right -= 1
        if left <= right:       # došli smo do trenutka za zamenu
            S[left], S[right] = S[right], S[left]   # zameni vrednosti
            left, right = left + 1, right - 1       # smanji opseg
    # postavi pivota na finalnu poziciju (označenu sa left indeksom)
    S[left], S[b] = S[b], S[left]
    # pravi rekurzivne pozive
    inplace_quick_sort(S, a, left - 1)
    inplace_quick_sort(S, left + 1, b)

def quick_sort_with_queue(S):
    '''Sort the elements of queue S using the quick-sort algorithm.'''
    n = len(S)
    if n < 2:
        return  # red je sortiran
    # podeli
    p = S.first()   # prvi je uzet za pivot
    L = Queue()
    E = Queue()
    G = Queue()
    while not S.is_empty(): # podeli S u L, E i G
        if S.first() < p:
            L.enqueue(S.dequeue())
        elif p < S.first():
            G.enqueue(S.dequeue())
        else:
            E.enqueue(S.dequeue())
    # vladaj (sa rekurzijom)
    quick_sort_with_queue(L)    # sortiraj elemente manje od p
    quick_sort_with_queue(G)    # sortiraj elemente vece od p
    # spoji rezultate
    while not L.is_empty():
        S.enqueue(L.dequeue())
    while not E.is_empty():
        S.enqueue(E.dequeue())
    while not G.is_empty():
        S.enqueue(G.dequeue())

if __name__ == '__main__':

    array = [2, 6, 7, 8, 1, 3, 9, 9, 4]
    print(array)
    inplace_quick_sort(array, 0, len(array)-1)
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
    # quick_sort_with_queue(q)
    # print(q._data)
