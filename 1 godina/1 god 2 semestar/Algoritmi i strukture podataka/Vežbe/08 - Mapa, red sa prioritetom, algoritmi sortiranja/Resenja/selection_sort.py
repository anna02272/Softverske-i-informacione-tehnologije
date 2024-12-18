# -+- encoding: utf-8 -+-
from pqueue import UnsortedPriorityQueue

def pq_sort(A):
    """
    Funkcija sortira listu koristeći dodatni prioritetni red

    Argument:
    - `A`: lista koja se sortira
    """
    size = len(A)
    #selection sort koristi nesortiranu listu
    pq = UnsortedPriorityQueue()

    # svi elementi liste prebacuju se u prioritetni red
    for i in range(size):
        element = A.pop()
        pq.add(element, element)

    # vraćanje elementa iz prioritetnog reda u listu
    for i in range(size):
        (k, v) = pq.remove_min()
        A.append(v)

def selection_sort(array):

    for i in range(len(array)):
      
        # Nađi najmanji element u preostalom
        # delu nesortiranog niza
        min_i = i
        for j in range(i+1, len(array)):
            if array[min_i] > array[j]:
                min_i = j
              
        # Zameni pronađeni minimalni element sa
        # prvim elementom u trenutnoj iteraciji       
        array[i], array[min_i] = array[min_i], array[i]

if __name__ == "__main__":
    array = [2, 6, 7, 8, 1, 3, 9, 9, 4]
    pq_sort(array)
    #selection_sort(array)
    print(array)