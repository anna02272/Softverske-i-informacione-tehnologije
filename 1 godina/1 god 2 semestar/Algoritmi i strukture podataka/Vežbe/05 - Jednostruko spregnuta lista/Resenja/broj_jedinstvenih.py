# -*- coding: utf-8 -*-
from singly_linked_list import SinglyLinkedList

def unique(list):
    unique = []
    count = 0
    for item in list:
        if item.value not in unique:
            count += 1
            unique.append(item.value)
    return count

if __name__ == "__main__":
    lista = SinglyLinkedList()
    lista.add_first(5)
    lista.add_first(2)
    lista.add_first(5)
    lista.add_first(1)
    lista.add_first(6)
    lista.add_first(3)
    lista.add_first(-1)
    lista.add_first(2)

    print(unique(lista))