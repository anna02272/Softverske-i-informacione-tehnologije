#8. za prosleđeni integer naći proizvod svih neparnih cifara, ako nema neparnih cifara vratiti 0
def solution(n):
    if any(int(c) % 2 for c in str(n)):
        prod = 1
        for c in str(n):
            if int(c) % 2 == 1:
                prod *= int(c)
        return prod
    return 0


def solution2(n):
    prod = 1
    flag = False
    for c in str(n):
        if int(c) % 2 == 1:
            flag = True
            prod *= int(c)
    if not flag:
        return 0
    return prod


print(solution(123456789))
print(solution(212121))
print(solution(22264))
