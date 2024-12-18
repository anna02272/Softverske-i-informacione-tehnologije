#za prosleđeni integer vratiti integer sa obrnutim ciframa, integer može biti i pozitivan i negativan
def solution(x):
    string = str(x)

    if string[0] == '-':
        return int('-'+string[:0:-1])
    else:
        return int(string[::-1])


print(solution(-543))
print(solution(723))
