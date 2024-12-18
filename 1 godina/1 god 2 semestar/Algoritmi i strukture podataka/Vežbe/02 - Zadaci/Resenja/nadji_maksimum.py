#12.za prosleđeni niz integer vrednosti naći najveću vrednost
def solution(niz):
    max = niz[0]
    for i in range(len(niz)):
        if niz[i] > max:
            max = niz[i]

    return max


print(solution([27, 3, 5, 8, 11, 2, 6]))
print(solution([2, 1, 22, 18, -3, 4]))
