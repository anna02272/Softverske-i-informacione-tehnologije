#za prosleđeni string ispisati sumu svih cifara koje se pojavljuju
def solution(broj):
    sum_digit = 0
    for x in broj:
        if x.isdigit() == True:
            z = int(x)
            sum_digit = sum_digit + z

    return sum_digit


print(solution("225abc883"))
print(solution("555xyz333"))
