# -*- coding: utf-8 -*-
from singly_linked_list import SinglyLinkedList

def find_max(list):
    max_el = list.get_first()
    for item in list:
        if item.value > max_el.value:
            max_el = item
    return max_el

if __name__ == "__main__":
    lista = SinglyLinkedList()
    lista.add_first(5)
    lista.add_first(1)
    lista.add_first(6)
    lista.add_first(3)
    lista.add_first(-1)
    lista.add_first(2)

    print(find_max(lista))