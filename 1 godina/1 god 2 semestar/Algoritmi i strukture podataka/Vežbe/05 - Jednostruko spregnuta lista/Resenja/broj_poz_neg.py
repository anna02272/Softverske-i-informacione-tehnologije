# -*- coding: utf-8 -*-
from singly_linked_list import SinglyLinkedList

def count_pos_neg(list):
    pos_count, neg_count = 0, 0
    for el in list:
        if el.value >= 0:
            pos_count += 1
    
        else:
            neg_count += 1
    return pos_count, neg_count

if __name__ == "__main__":
    lista = SinglyLinkedList()
    lista.add_first(-5)
    lista.add_first(2)
    lista.add_first(5)
    lista.add_first(1)
    lista.add_first(6)
    lista.add_first(3)
    lista.add_first(-1)
    lista.add_first(2)

    print(count_pos_neg(lista))