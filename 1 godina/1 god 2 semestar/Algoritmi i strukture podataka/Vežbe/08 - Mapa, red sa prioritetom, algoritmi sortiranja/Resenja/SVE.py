from pqueue import UnsortedPriorityQueue
from pqueue import SortedPriorityQueue
from queue import Queue


class EmptyList(Exception):
    pass


class Node(object):
    def __init__(self, value, next=None):
        self._value = value
        self._next = next

    @property
    def value(self):
        return self._value

    @property
    def next(self):
        return self._next

    @value.setter
    def value(self, new_value):
        self._value = new_value

    @next.setter
    def next(self, new_next):
        self._next = new_next

    def __str__(self):
        return str(self.value)


class SinglyLinkedList(object):
    def __init__(self):
        self._head = None
        self._tail = None
        self._size = 0

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    def __iter__(self):
        current_node = self._head
        while current_node:
            yield current_node
            current_node = current_node.next

    def get_first(self):
        if self.is_empty():
            raise EmptyList("Lista je prazna")
        return self._head

    def get_last(self):
        if self.is_empty():
            raise EmptyList("Lista je prazna")
        return self._tail

    def add_first(self, value):
        new_node = Node(value)
        if self.is_empty():
            self._tail = new_node
        else:
            new_node.next = self._head
        self._head = new_node
        self._size += 1

    def add_last(self, value):
        new_node = Node(value)
        if self.is_empty():
            self._head = new_node
        else:
            self._tail.next = new_node
        self._tail = new_node
        self._size += 1

    def remove_first(self):
        if self.is_empty():
            raise EmptyList("Lista je prazna")
        if self._size == 1:
            self._tail = None
        self._head = self._head.next
        self._size -= 1

    def remove_last(self):
        if self.is_empty():
            raise EmptyList("Lista je prazna")
        if self._size == 1:
            self._head = None
        for node in self:
            if node.next == self._tail:
                node.next = None
                self._tail = node
                break
        self._size -= 1

    def get_at(self, index):
        if not 0 <= index <= self._size-1:
            raise IndexError("Nedozvoljena pozicija")
        counter = 0
        current_node = self._head
        while current_node:
            if counter == index:
                return current_node
            current_node = current_node.next
            counter += 1

    def insert_at(self, index, value):
        new_node = Node(value)
        if index == 0:
            self.add_first(value)
            return
        if index == self._size:
            self.add_last(value)
            return
        previous_node = self.get_at(index-1)
        temp = previous_node.next
        previous_node.next = new_node
        new_node.next = temp
        self._size += 1

    def remove_at(self, index):
        if not 0 <= index <= self._size-1:
            raise IndexError("Nedozvoljena pozicija")
        if index == 0:
            self.remove_first()
            return
        if index == self._size-1:
            self.remove_last()
            return
        previous_node = self.get_at(index-1)
        after_node = previous_node.next.next
        previous_node.next = after_node
        self._size -= 1


if __name__ == "__main__":
    lista = SinglyLinkedList()
    lista.add_first('b')
    lista.add_first('a')
    lista.add_last('c')
    lista.add_last('d')
    # lista.remove_first()
    # lista.remove_first()
    # lista.remove_first()
    # lista.remove_first()
    # lista.remove_first()
    # lista.remove_last()
    # lista.remove_last()
    # lista.remove_last()
    # lista.remove_last()
    # lista.remove_last()
    # print(lista.get_at(-1))
    #lista.insert_at(1, 'f')
    #n1 = lista.get_at(0)
    # print(n1.next.next.next.next)
    # lista.remove_at(4)
    for node in lista:
        print(node)


class EmptyList(Exception):
    pass


class Node(object):
    def __init__(self, value, previous_node=None, next_node=None):
        self._value = value
        self._previous = previous_node
        self._next = next_node

    @property
    def value(self):
        return self._value

    @property
    def previous(self):
        return self._previous

    @property
    def next(self):
        return self._next

    @value.setter
    def value(self, new_value):
        self._value = new_value

    @previous.setter
    def previous(self, new_value):
        self._previous = new_value

    @next.setter
    def next(self, new_value):
        self._next = new_value

    def __str__(self):
        return str(self._value)


class DoublyLinkedList(object):

    def __init__(self):
        self._head = Node(None, None, None)
        self._tail = Node(None, self._head, None)
        self._head.next = self._tail
        self._size = 0

    def __len__(self):
        return self._size

    def is_empty(self):
        return self._size == 0

    def get_first(self):
        if self.is_empty():
            raise EmptyList("Prazna lista!")
        return self._head.next

    def get_last(self):
        if self.is_empty():
            raise EmptyList("Prazna lista!")
        return self._tail.previous

    def __iter__(self):
        current_node = self._head.next
        while current_node != self._tail:
            yield current_node
            current_node = current_node.next

    def add_first(self, value):
        new_node = Node(value)
        if self.is_empty():
            self._tail.previous = new_node
        else:
            self._head.next.previous = new_node
        new_node.next = self._head.next
        new_node.previous = self._head
        self._head.next = new_node
        self._size += 1
        return new_node

    def add_last(self, value):
        new_node = Node(value)
        if self.is_empty():
            self._head.next = new_node
        else:
            self._tail.previous.next = new_node
        new_node.next = self._tail
        new_node.previous = self._tail.previous
        self._tail.previous = new_node
        self._size += 1
        return new_node

    def remove_first(self):
        if self.is_empty():
            raise EmptyList("Prazna lista!")
        to_remove = self._head.next
        if self._size == 1:
            self._head.next = self._tail
            self._tail.previous = self._head
        else:
            new_first = self._head.next.next
            self._head.next = new_first
            new_first.previous = self._head
        self._size -= 1
        return to_remove

    def remove_last(self):
        if self.is_empty():
            raise EmptyList("Prazna lista!")
        to_remove = self._tail.previous
        if self._size == 1:
            self._head.next = self._tail
            self._tail.previous = self._head
        else:
            new_last = self._tail.previous.previous
            new_last.next = self._tail
            self._tail.previous = new_last
        self._size -= 1
        return to_remove

    def insert_after(self, node1, value):
        new_node = Node(value, node1, None)
        new_node.next = node1.next
        node1.next.previous = new_node
        node1.next = new_node
        self._size += 1
        return new_node

    def insert_before(self, node1, value):
        new_node = Node(value, None, node1)
        new_node.previous = node1.previous
        node1.previous.next = new_node
        node1.previous = new_node
        self._size += 1
        return new_node

    def get_at(self, index):
        if not 0 <= index <= self._size-1:
            raise IndexError("Nedozvoljen indeks!")
        counter = 0
        #current_node = self._head.next
        for current_node in self:
            if counter == index:
                return current_node
            #current_node = current_node.next
            counter += 1

    def insert_at(self, index, value):
        new_node = Node(value)
        if not 0 <= index <= self._size + 1:
            raise IndexError("Nedozvoljeni indeks!")
        if index == 0:
            return self.add_first(value)
        if index == self._size:
            return self.add_last(value)
        current_node = self.get_at(index)
        #previous_node = current_node.previous
        #previous_node.next = new_node
        #new_node.previous = previous_node
        #new_node.next = current_node
        #current_node.previous = new_node
        #self._size += 1
        # return new_node
        # ili
        return self.insert_before(current_node, value)

    def remove_at(self, index):
        if not 0 <= index <= self._size-1:
            raise IndexError("Nedozvoljeni indeks!")
        if index == 0:
            return self.remove_first()
        if index == self._size-1:
            return self.remove_last()
        previous_node = self.get_at(index-1)
        to_remove = previous_node.next
        next_node = previous_node.next.next
        previous_node.next = next_node
        next_node.previous = previous_node
        self._size -= 1
        return to_remove

    def reverse(self):
        temp = None
        current = self._head

        while current is not None:
            temp = current.next
            current.next = current.previous
            current.previous = temp
            current = current.previous

        temp = self._head
        self._head = self._tail
        self._tail = temp


lista = DoublyLinkedList()
n1 = lista.add_first('b')
lista.add_first('a')
lista.add_last('c')
lista.add_last('d')
#lista.insert_after(n1, 'e')
#lista.insert_before(n1, 'e')
# print(lista.get_at(4))
#lista.insert_at(3, 'e')
lista.remove_at(2)
#print("Izbrisani element: " + lista.remove_first().value)
#print("Izbrisani element: " + lista.remove_first().value)
#print("Izbrisani element: " + lista.remove_first().value)
#print("Izbrisani element: " + lista.remove_first().value)
#print("Izbrisani element: " + lista.remove_first().value)
#print("Izbrisani element: " + lista.remove_last().value)
#print("Izbrisani element: " + lista.remove_last().value)

# print(lista.get_first())
# print(lista.get_last())
for node in lista:
    print(node)
lista.reverse()
print("-----")
# lista.add_first('f')
for node in lista:
    print(node)
print("-----")
print(lista.get_at(0))
print(lista.get_last())
# -*- encoding: utf-8 -*-
"""
Modul sadrži implementaciju stabla.
"""


class TreeNode(object):
    """
    Klasa modeluje čvor stabla.
    """

    def __init__(self, data):
        """
        Konstruktor.

        Argument:
        - `data`: podatak koji se upisuje u čvor
        """
        self.parent = None
        self.children = []
        self.data = data

    def is_root(self):
        """
        Metoda proverava da li je čvor koren stabla.
        """
        return self.parent is None

    def is_leaf(self):
        """
        Metoda proverava da li je čvor list stabla.
        """
        return len(self.children) == 0

    def add_child(self, x):
        """
        Metoda dodaje potomka čvoru.

        Argument:
        - `x`: čvor potomak
        """
        # kreiranje dvosmerne veze između čvorova
        x.parent = self
        self.children.append(x)


class Tree(object):
    """
    Klasa modeluje stablo.
    """

    def __init__(self):
        self.root = None

    def is_empty(self):
        """
        Metoda proverava da li stablo ima elemenata.
        """
        return self.root is None

    def depth(self, x):
        """
        Metoda izračunava dubinu zadatog čvora.

        Argument:
        - `x`: čvor čija dubina se računa
        """
        if x.is_root():
            return 0
        else:
            return 1 + self.depth(x.parent)

    def _height(self, x):
        """
        Metoda izračunava visinu podstabla sa zadatim korenom.

        Argument:
        - `x`: koren posmatranog podstabla
        """
        if x.is_leaf():
            return 0
        else:
            return 1 + max(self._height(c) for c in x.children)

    def height(self):
        return self._height(self.root)

    def preorder(self, x):
        """
        Preorder obilazak po dubini

        Najpre se vrši obilazak roditelja a zatim svih njegovih potomaka.

        Argument:
        - `x`: čvor od koga počinje obilazak
        """
        if not self.is_empty():
            print(x.data)
            for c in x.children:
                self.preorder(c)

    def postorder(self, x):
        """
        Postorder obilazak po dubini

        Najpre se vrši obilazak potomaka a zatim i roditelja

        Argument:
        - `x`: čvor od koga počinje obilazak
        """
        if not self.is_empty():
            for c in x.children:
                self.postorder(c)
            print(x.data)

    def breadth_first(self):
        """
        Metoda vrši obilazak stabla po širini.
        """
        to_visit = Queue()
        to_visit.enqueue(self.root)
        while not to_visit.is_empty():
            e = to_visit.dequeue()
            print(e.data)

            for c in e.children:
                to_visit.enqueue(c)


if __name__ == '__main__':
    # instanca stabla
    t = Tree()
    t.root = TreeNode(0)

    # kreiranje relacija između novih čvorova
    a = TreeNode(1)
    b = TreeNode(2)
    c = TreeNode(3)
    d = TreeNode(4)

    a.add_child(b)
    a.add_child(d)
    t.root.add_child(a)
    t.root.add_child(c)

    # visina stabla
    print('Visina = %d' % t.height())

    # dubina čvora
    print('Dubina(a) = %d' % t.depth(a))

    # obilazak po dubini - preorder
    print('PREORDER')
    t.preorder(t.root)

    # obilazak po dubini - postorder
    print('POSTORDER')
    t.postorder(t.root)

    # obilazak po širini
    print('BREADTH FIRST')
    t.breadth_first()
# -*- encoding: utf-8 -*-
"""
Modul sadrži implementaciju asocijativnog niza
"""


class MapElement(object):
    """
    Klasa modeluje element asocijativnog niza
    """

    def __init__(self, key, value):
        self._key = key
        self._value = value

    @property
    def key(self):
        return self._key

    @property
    def value(self):
        return self._value

    @value.setter
    def value(self, new_value):
        self._value = new_value


class Map(object):
    """
    Klasa modeluje asocijativni niz
    """

    def __init__(self):
        self._data = []

    def __getitem__(self, key):
        """
        Pristup elementu sa zadatim ključem

        Metoda vrši pristup elementu sa zadatim ključem. U slučaju
        da element postoji u mapi, metoda vraća njegovu vrednost, dok
        u suprotnom podiže odgovarajući izuzetak.

        Argument:
        - `key`: ključ elementa kome se pristupa
        """
        for item in self._data:
            if key == item.key:
                return item.value

        raise KeyError('Ne postoji element sa ključem %s' % str(key))

    def __setitem__(self, key, value):
        """
        Postavljanje vrednosti elementa sa zadatim ključem

        Metoda najpre pretražuje postojeće elemente po vrednosti ključa.
        Ukoliko traženi ključ već postoji, vrši se ažuriranje vrednosti
        postojećeg elementa. U suprotnom, kreira se novi element koji se
        dodaje u mapu.

        Argumenti:
        - `key`: ključ elementa koji se kreira ili ažurira
        - `value`: nova vrednost elementa
        """
        for item in self._data:
            if key == item.key:
                item.value = value
                return

        # element nije pronađen, zapiši ga u mapu
        self._data.append(MapElement(key, value))

    def __delitem__(self, key):
        """
        Brisanje elementa sa zadatim ključem

        Metoda pretražuje elemente po vrednosti ključa. Ukoliko element
        sa zadatim ključem postoji u mapi, vrši se njegovo brisanje. U
        suprotnom se podiže odgovarajući izuzetak.

        Argument:
        - `key`: ključ elementa za brisanje
        """
        length = len(self._data)
        for i in range(length):
            if key == self._data[i].key:
                self._data.pop(i)
                return

        raise KeyError('Ne postoji element sa ključem %s' % str(key))

    def __len__(self):
        return len(self._data)

    def __contains__(self, key):
        """
        Metoda vrši proveru postojanja ključa u mapi

        Argument:
        - `key`: ključ koji se traži
        """
        for item in self._data:
            if key == item.key:
                return True

        return False

    def __iter__(self):
        for item in self._data:
            yield item.key

    def items(self):
        for item in self._data:
            yield item.key, item.value

    def keys(self):
        """
        Metoda vraća sve ključeve u mapi
        """
        keys = []
        for key in self:
            keys.append(key)

        return keys

    def values(self):
        """
        Metoda vraća sve vrednosti u mapi
        """
        values = []
        for key in self:
            values.append(self[key])

        return values

    def clear(self):
        """
        Metoda uklanja sve elemente iz mape
        """
        self._data = []


if __name__ == '__main__':
    table = Map()
    table[3] = 10
    table['x'] = 11
    table['asd'] = 'abcdefg'

    # pristup elementima
    print(table['asd'])
    print(table.values())
    print(table.keys())

    # metoda __contains__
    if 'y' in table:
        print('Tabela sadrži ključ y.')
    else:
        print('Tabela ne sadrži ključ y.')

    # iteracija kroz tabelu
    for item in table:
        print(item, table[item])

    # brisanje elementa
    del table['asd']
    print(len(table) == 2)

    # clear metoda
    table.clear()
    print(len(table) == 0)
# -+- encoding: utf-8 -+-

"""
Modul sadrži različite implementacije reda sa prioritetom.
"""


class PQError(Exception):
    """
    Klasa modeluje izuzetke vezane za prioritetni red.
    """
    pass


class PQItem(object):
    """
    Klasa modeluje element prioritetnog reda.
    """

    def __init__(self, key, value):
        self._key = key
        self._value = value

    @property
    def key(self):
        return self._key

    @key.setter
    def key(self, new_key):
        self._key = new_key

    @property
    def value(self):
        return self._value

    @value.setter
    def value(self, new_value):
        self._value = new_value

    def __lt__(self, x):
        return self._key < x._key

    def __str__(self):
        return "(" + str(self._key) + ")"


class PriorityQueueBase(object):
    """
    Baza prioritetnog reda

    Klasa sadrži operacije nezavisne od organizacije strukture podataka
    koja se koristi za smeštanje elemenata prioritetnog reda.
    """

    def __init__(self):
        self._data = []

    def __len__(self):
        return len(self._data)

    def __str__(self):
        return ', '.join('(%s, %s)' % (e._key, e._value) for e in self._data)

    def is_empty(self):
        """
        Metoda proverava da li je red prazan.
        """
        return len(self) == 0


class SortedPriorityQueue(PriorityQueueBase):
    """
    Klasa modeluje prioritetni red korišćenjem sortirane liste.
    """

    def __init__(self):
        # super().__init__()
        super(SortedPriorityQueue, self).__init__()

    def min(self):
        """
        Metoda omogućava pristup elementu sa najmanjim ključem.
        """
        if self.is_empty():
            raise PQError('Red je prazan.')

        min_item = self._data[0]
        return (min_item._key, min_item._value)

    def remove_min(self):
        """
        Metoda uklanja element sa najmanjim ključem.
        """
        if self.is_empty():
            raise PQError('Red je prazan.')

        removed = self._data.pop(0)
        return (removed._key, removed._value)

    def add(self, key, value):
        """
        Metoda dodaje novi element u red.
        """
        new_item = PQItem(key, value)

        last = len(self)-1
        position = 0

        # pronalaženje pozicije za dodavanje elementa
        for i in range(last, -1, -1):
            current_item = self._data[i]
            if not new_item < current_item:
                position = i+1
                break

        self._data.insert(position, new_item)


class UnsortedPriorityQueue(PriorityQueueBase):
    """
    Klasa modeluje prioritetni red korišćenjem nesortirane liste.
    """

    def __init__(self):
        super(UnsortedPriorityQueue, self).__init__()

    def _find_min(self):
        """
        Metoda pronalazi element sa najmanjim ključem.
        """
        if self.is_empty():
            raise PQError('Red je prazan.')

        min_item = self._data[0]
        size = len(self)
        for i in range(1, size):
            current_item = self._data[i]
            if current_item < min_item:
                min_item = current_item

        return min_item

    def min(self):
        """
        Metoda omogućava pristup elementu sa najmanjim ključem.
        """
        min_item = self._find_min()
        return (min_item.key, min_item.value)

    def remove_min(self):
        """
        Metoda uklanja element sa najmanjim ključem.
        """
        min_item = self._find_min()
        index = self._data.index(min_item)
        removed = self._data.pop(index)
        return (removed.key, removed.value)

    def add(self, key, value):
        """
        Metoda dodaje novi element u red.
        """
        new_item = PQItem(key, value)
        self._data.append(new_item)


if __name__ == '__main__':
    upq = UnsortedPriorityQueue()
    upq.add(3, 'abc')
    upq.add(2, 'ab')
    upq.add(1, 'a')
    upq.add(11, 'abcdefghijk')

    print('------------------------------')
    print('UNSORTED PRIORITY QUEUE')
    print('------------------------------')
    print('Content: %s' % upq)
    print('Minimum: (%s, %s)' % upq.min())
    print('------------------------------')
    print('Removed (%s, %s)' % upq.remove_min())
    print('------------------------------')
    print('Content: %s' % upq)
    print('Minimum: (%s, %s)' % upq.min())
    print('------------------------------')
    spq = SortedPriorityQueue()
    spq.add(3, 'abc')
    spq.add(2, 'ab')
    spq.add(1, 'a')
    spq.add(11, 'abcdefghijk')

    print('\n------------------------------')
    print('SORTED PRIORITY QUEUE')
    print('------------------------------')
    print('Content: %s' % spq)
    print('Minimum: (%s, %s)' % spq.min())
    print('------------------------------')
    print('Removed (%s, %s)' % spq.remove_min())
    print('------------------------------')
    print('Content: %s' % spq)
    print('Minimum: (%s, %s)' % spq.min())
# -*- encoding: utf-8 -*-

"""
Modul sadrži implementacije algoritama za pretraživanje teksta
"""


def brute_force(T, P):
    """
    Brute force algoritam pretrage

    Funkcija poredi odgovarajuće karaktera teksta i šablona koji
    se traži. Svako nepoklapanje pomera startnu poziciju pretrage
    za 1. Složenost: O(nm), gde su n i m dužine teksta odnosno
    šablona koji se traži.

    Argumenti:
    - `T`: tekst koji se pretražuje
    - `P`: šablon koji se traži
    """
    n = len(T)
    m = len(P)

    found = []
    diff = n-m+1
    for s in range(diff):
        if P == T[s:s+m]:
            found.append(s)

    return found


def brute_force_v2(T, P):
    """Funkcija vraća najmanji indeks teksta T
    od kojeg počinje podstring P (u suprotnom -1)."""
    n, m = len(T), len(P)                   # odredi dužine oba stringa
    for i in range(n-m+1):                  # pokušaj za svaki potencijalni početni indeks u T
        k = 0                               # indeks u šablonu P
        while k < m and T[i + k] == P[k]:   # k-ti karakter P se podudara
            k += 1
        if k == m:                          # ako smo došli do kraja šablona,
            return i                        # podstring T[i:i+m] odgovara P
    return -1                               # nije pronađeno nijedno poklapanje počevši od i


def boyer_moore(T, P):
    """
    Boyer-Moore algoritam pretrage

    Funkcija poredi odgovarajuće karaktera teksta i šablona koji
    se traži sa desna nalevo. Svako nepoklapanje pomera startnu
    poziciju pretrage za broj koraka koji se računa na osnovu
    poslednjeg pojavljivanja nepoklopljenog karaktera iz teksta u šablonu.
    Složenost: O(nm+s), gde su n i m dužine teksta odnosno šablona koji se
    traži, a s predstavlja dužinu alfabeta.

    Argumenti:
    - `T`: tekst koji se pretražuje
    - `P`: šablon koji se traži
    """
    n, m = len(T), len(P)
    # trivijalno traženje za prazan string
    if m == 0:
        return 0
    last = {}
    # popunjavanje rečnika tako da se beleže poslednje pojave karaktera u šablonu
    for k in range(m):
        last[P[k]] = k
    # indeksi počinju od kraja šablona
    i = m-1  # indeks teksta
    k = m-1  # indeks šablona
    while i < n:
        # pokušaj poklapanja
        if T[i] == P[k]:
            if k == 0:
                # uspešno poklapanje svih karaktera od kraja šablona do početka, uspešno završeno
                return i
            else:
                # uspešno poklapanje poslednjih k karaktera šablona
                # sledeće poređenje je između prethodnog karaktera teksta i prethodnog karaktera šablona
                i -= 1
                k -= 1
        else:
            # čitanje indeksa nepoklopljenog karaktera teksta iz rečnika
            # j=-1 ukoliko traženi karakter ne postoji u rečniku
            j = last.get(T[i], -1)
            # računanje broja koraka za skok
            i += m - min(k, j + 1)
            # početak analize šablona od kraja (restart)
            k = m - 1

    return -1


def generate_table(P):
    """
    Funkcija generiše tabelu poklapanja za KMP algoritam

    Tabela poklapanja beleži maksimalnu dužinu tzv. `proper`
    prefiksa stringa koji je ujedno i njegov sufiks.

    Argument:
    - `P`: string čija se tabela generiše
    """
    # tabela ima onoliko elemenata koliko string ima karaktera
    m = len(P)
    table = [0] * m

    # broj poklapanja (k) i indeks trenutnog karaktera (i)
    k = 0
    i = 1

    while i < m:
        if P[i] == P[k]:
            # ukoliko se karakteri poklapaju povećava se broj poklopljenih
            table[i] = k+1
            i = i + 1
            k = k + 1
        elif k > 0:
            # karakteri se ne poklapaju, ali je bilo pogodaka, pokušaj pronaći
            # kraći prefiks koji je ujedno i sufiks
            k = table[k-1]
        else:
            # nema poklapanja, pređi na sledeći karakter
            i = i + 1

    return table


def kmp(T, P):
    """
    Knuth-Morris-Pratt algoritam

    Na osnovu generisane tabele, algoritam vrši `pametne` pomeraje
    prilikom pretraživanja stringa. Složenost: O(n+m), gde su n i m
    dužine teksta koji se pretražuje odnosno šablona koji se traži.

    Argumenti:
    - `T`: tekst koji se pretražuje
    - `P`: šablon koji se traži
    """
    n = len(T)
    m = len(P)

    if m == 0:
        return [0]

    # get prefix table
    table = generate_table(P)

    # indeks šablona (k) i indeks trenutnog karaktera (i)
    k = 0
    i = 0

    # lista čuva indekse na kojima počinju poklapanja
    found = []

    while i < n:
        if T[i] == P[k]:
            # ako se karakteri poklapaju proveri da li je došlo do potpunog
            # poklapanja ili povećaj broj poklopljenih karaktera
            if k == m-1:
                found.append(i-m+1)
                k = table[k-1]
            else:
                i = i + 1
                k = k + 1
        elif k > 0:
            # karakteri se ne poklapaju ali je bilo pogodaka, pomeri za
            # odgovarajući broj mesta
            k = table[k-1]
        else:
            # nema poklapanja, pređi na sledeći karakter
            i = i + 1

    return found


if __name__ == '__main__':
    text = 'abaabccbcabbabaab'
    pattern = 'abba'
    print(brute_force(text, pattern))
    print(brute_force_v2(text, pattern))
    print(boyer_moore(text, pattern))
    print(kmp(text, pattern))
# -+- encoding: utf-8 -+-


def pq_sort(A):
    """
    Funkcija sortira listu koristeći dodatni prioritetni red

    Argument:
    - `A`: lista koja se sortira
    """
    size = len(A)
    # insertion sort koristi sortiranu listu
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
        while j >= 0 and curr < array[j]:
            array[j+1] = array[j]
            j -= 1
        array[j+1] = curr


if __name__ == "__main__":
    array = [2, 6, 7, 8, 1, 3, 9, 9, 4]
    # pq_sort(array)
    insertion_sort(array)
    print(array)
    # -+- encoding: utf-8 -+-


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
    while len(S1) < n // 2:  # dodaj prvu polovinu elemenata u S1
        S1.enqueue(S.dequeue())
    while not S.is_empty():  # dodaj ostatak elemenata u S2
        S2.enqueue(S.dequeue())
    # vladaj
    merge_sort_v2(S1)       # sortiraj prvu polovinu
    merge_sort_v2(S2)       # sortiraj drugu polovinu
    merge_v2(S1, S2, S)     # spoji sortirane polovine u S


def merge_v3(S1, S2, S):
    i = j = 0
    while i + j < len(S):
        if j == len(S2) or (i < len(S1) and S1[i] < S2[j]):
            S[i+j] = S1[i]  # kopiraj i-ti element S1 kao sledeći element S
            i += 1
        else:
            S[i+j] = S2[j]  # kopiraj j-ti element S2 kao sledeći element S
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
    # print(merge_sort(array))
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
    # -+- encoding: utf-8 -+-


def pq_sort(A):
    """
    Funkcija sortira listu koristeći dodatni prioritetni red

    Argument:
    - `A`: lista koja se sortira
    """
    size = len(A)
    # selection sort koristi nesortiranu listu
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
    # selection_sort(array)
    print(array)

    # -+- encoding: utf-8 -+-


def bubble_sort(array):
    n = len(array)
    for i in range(n-1):
        for j in range(0, n-i-1):
            if array[j] > array[j + 1]:
                array[j], array[j + 1] = array[j + 1], array[j]


if __name__ == "__main__":
    array = [2, 6, 7, 8, 1, 3, 9, 9, 4]
    bubble_sort(array)
    print(array)
