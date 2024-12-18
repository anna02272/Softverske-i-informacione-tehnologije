# -*- coding: utf-8 -*-

from singly_linked_list import SinglyLinkedList


class StackError(Exception):
    """
    Klasa modeluje izuzetke vezane za klasu Stack.
    """
    pass


class UnlimitedStack(object):
    """
    Implementacija steka na osnovu liste.
    """

    def __init__(self):
        self._data = SinglyLinkedList()

    def __len__(self):
        return len(self._data)

    def is_empty(self):
        """
        Metoda proverava da li je stek prazan.
        """
        return len(self._data) == 0

    def push(self, e):
        """
        Metoda vrši ubacivanje elementa na stek.

        Argument:
        - `e`: novi element
        """
        self._data.add_last(e)

    def top(self):
        """
        Metoda vraća element na vrhu steka.
        """
        if self.is_empty():
            raise StackError('Stek je prazan.')
        return self._data.get_last()

    def pop(self):
        """
        Metoda izbacuje element sa vrha steka.
        """
        if self.is_empty():
            raise StackError('Stek je prazan.')
        #el = self._data.get_at(len(self)-1)
        el = self._data.get_last()
        self._data.remove_last()
        return el


if __name__ == '__main__':
    s = UnlimitedStack()
    s.push(2)
    s.push(3)
    s.push(4)
    print(s.top())
    print(len(s))

    print(s.pop())
    print(s.top())
    print(s.is_empty())
