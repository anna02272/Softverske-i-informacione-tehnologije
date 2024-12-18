# -*- coding: utf-8 -*-

from singly_linked_list import SinglyLinkedList

class QueueError(Exception):
    """
    Klasa modeluje izuzetke vezane za klasu Queue.
    """
    pass


class UnlimitedQueue(object):
    """
    Implementacija reda na osnovu liste.
    """

    def __init__(self):
        """
        Konstruktor.

        """
        self._data = SinglyLinkedList()

    def __len__(self):
        return len(self._data)

    def is_empty(self):
        """
        Metoda proverava da li je red prazan.
        """
        return len(self._data) == 0

    def first(self):
        """
        Metoda omogućava pristup prvom elementu reda.
        """
        if self.is_empty():
            raise QueueError('Red je prazan.')
        return self._data.get_first()

    def dequeue(self):
        """
        Metoda izbacuje prvi element iz reda.
        """
        if self.is_empty():
            raise QueueError('Red je prazan.')

        element = self._data.get_first()
        self._data.remove_first()

        return element

    def enqueue(self, e):
        """
        Metoda vrši dodavanje elementa u red.

        Argument:
        - `e`: novi element
        """
        self._data.add_last(e)

if __name__ == '__main__':
    queue = UnlimitedQueue()
    queue.enqueue(3)
    queue.enqueue(8)
    queue.enqueue(1)
    print(len(queue))
    print(queue.first())

    queue.dequeue()
    print(len(queue))

    print(queue.first())
    print(queue.is_empty())