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
