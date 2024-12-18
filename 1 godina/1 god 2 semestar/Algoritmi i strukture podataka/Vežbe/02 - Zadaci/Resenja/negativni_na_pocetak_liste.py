#u prosleđenom nizu pomeriti sve negativne brojeve na početak a sve pozitivne brojeve na kraj
def solution(niz):

    j = 0
    for i in range(len(niz)):
        if (niz[i] < 0):
            temp = niz[i]
            niz[i] = niz[j]
            niz[j] = temp
            j = j + 1
    return niz


def solution2(niz):
    nizPoz = []
    nizNeg = []
    for el in niz:
        if el > 0:
            nizPoz.append(el)
        else:
            nizNeg.append(el)
    return nizNeg + nizPoz


print(solution([-1, 3, -2, 7, 4, 3, -5, -2, 6]))
print(solution2([-1, 3, -2, 7, 4, 3, -5, -2, 6]))
