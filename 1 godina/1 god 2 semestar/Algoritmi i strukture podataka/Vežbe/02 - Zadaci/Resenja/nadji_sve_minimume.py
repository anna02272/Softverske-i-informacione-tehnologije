#za prosleđeni niz integer vrednosti naći sve najmanje vrednosti
def solution(niz):
    min = niz[0]
    nizRez = []
    for i in range(len(niz)):
        if niz[i] < min:
            min = niz[i]
    for i in range(len(niz)):
        if niz[i] == min:
            nizRez.append(niz[i])
    return nizRez


print(solution([27, 3, 5, 8, 11, 2, 6, 2]))
print(solution([2, 1, 22, 18, -3, 4, -5, -5]))
