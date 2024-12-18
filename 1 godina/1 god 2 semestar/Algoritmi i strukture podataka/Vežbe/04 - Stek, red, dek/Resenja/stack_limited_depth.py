# -*- coding: utf-8 -*- 
"""
Modul sadrži implementaciju steka na osnovu liste.
"""


class StackError(Exception):
    """
    Klasa modeluje izuzetke vezane za klasu Stack.
    """
    pass


class Stack(object):
    """
    Implementacija steka na osnovu liste.
    """

    def __init__(self, capacity):
        self._data = []
        self._capacity = capacity

    def __len__(self):
        return len(self._data)

    def is_empty(self):
        """
        Metoda proverava da li je stek prazan.
        """
        return len(self._data) == 0

    def push(self, e):

        if len(self._data) == self._capacity:
            raise StackError("Stek je pun.")
        """
        Metoda vrši ubacivanje elementa na stek.
        Argument:
        - `e`: novi element
        """
        self._data.append(e)

    def top(self):
        """
        Metoda vraća element na vrhu steka.
        """
        if self.is_empty():
            raise StackError('Stek je prazan.')
        return self._data[-1]

    def pop(self):
        """
        Metoda izbacuje element sa vrha steka.
        """
        if self.is_empty():
            raise StackError('Stek je prazan.')
        return self._data.pop()


if __name__ == '__main__':
    s = Stack(3)
    s.push(2)
    print(s.top())
    print(len(s))
    s.push(3)
    s.push(4)
    #s.push(5)
    s.pop()
    print(s.is_empty())
