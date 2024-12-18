#5.za prosleđeni tekst i karakter vratiti tekst bez tog karaktera
def solution(str1, c):
    return ''.join([el for el in str1 if el != c])


def solution2(str1, c):
    return str1.replace(c, "")


def solution3(str1, c):
    str2 = ""
    for ch in str1:
        if ch != c:
            str2 += ch
    return str2


str1 = "Pera Peric je super mega car."
c = "a"
print(solution2(str1, c))
