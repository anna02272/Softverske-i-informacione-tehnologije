# -+- encoding: utf-8 -+-
class Item:

    def __init__(self, key, value):
        self.key = key
        self.value = value

    def __str__(self):
        return '(' + str(self.key) + ', ' + self.value + ')'
    
    def __repr__(self):
        return str(self)


def bucket_sort(S):
    length = len(S)
    B = [[] for _ in range(length)]
    for i in range(length):
        item = S.pop(0)
        B[item.key].append(item)
    for i in range(length):
        for _ in range(len(B[i])):
            item = B[i].pop(0)
            S.append(item)
    return S


if __name__ == '__main__':
    #pretpostavka je da su ključevi u opsegu 0-5
    item1 = Item(5, 'd')
    item2 = Item(1, 'c')
    item3 = Item(3, 'a')
    item4 = Item(5, 'g')
    item5 = Item(3, 'b')
    item6 = Item(5, 'e')

    S = [item1, item2, item3, item4, item5, item6]

    S = bucket_sort(S)
    for item in S:
        print(item)

