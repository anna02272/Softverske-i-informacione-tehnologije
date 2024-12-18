#za prosleđeni string ispisati Da ako je binarni ili Ne ako nije
def solution(string):
    p = set(string)
    s = {'0', '1'}
    if s == p or p == {'0'} or p == {'1'}:
        return "Da"
    else:
        return "Ne"


def solution2(string):
    p = set(string)
    if len(p) != len(string):
        return "Ne"
    else:
        return "Da"


def solution3(string):

    for ch in string:
        if not (ch == '0' or ch == '1'):
            return "Ne"
    return "Da"


print(solution('0001110101011'))
print(solution('00011a01b1011'))
