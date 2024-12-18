# -+- encoding: utf-8 -+-
from pqueue import SortedPriorityQueue

def pq_sort(A):
    """
    Funkcija sortira listu koristeći dodatni prioritetni red

    Argument:
    - `A`: lista koja se sortira
    """
    size = len(A)
    #insertion sort koristi sortiranu listu
    pq = SortedPriorityQueue()

    # svi elementi liste prebacuju se u prioritetni red
    for i in range(size):
        element = A.pop()
        pq.add(element, element)

    # vraćanje elementa iz prioritetnog reda u listu
    for i in range(size):
        (k, v) = pq.remove_min()
        A.append(v)

def insertion_sort(array):
    n = len(array)
    for i in range(1, n):
        curr = array[i]
        # Pomeraj elemente array[0..i-1], koji su veći
        # od trenutnog na poziciju za jedan veću
        # od njihove trenutne
        j = i-1
        while j >=0 and curr < array[j]:
            array[j+1] = array[j]
            j -= 1
        array[j+1] = curr

if __name__ == "__main__":
    array = [2, 6, 7, 8, 1, 3, 9, 9, 4]
    #pq_sort(array)
    insertion_sort(array)
    print(array)