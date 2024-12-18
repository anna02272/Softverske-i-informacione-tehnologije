#6. za prosleđeni niz integer vrednosti naći sve najmanje vrednosti
def solution(niz):
    min = niz[0]
    for i in range(len(niz)):
        if niz[i] < min:
            min = niz[i]

    return min


print(solution([27, 3, 5, 8, 11, 2, 6]))
print(solution([2, 1, 22, 18, -3, 4]))
