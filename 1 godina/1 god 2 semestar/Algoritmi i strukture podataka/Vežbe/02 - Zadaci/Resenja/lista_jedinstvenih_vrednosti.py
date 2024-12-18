#3.za prosleđeni niz integer vrednosti proveriti da li sadrži duplikate
def solution(array):
    nums_set = set(array)
    return len(array) != len(nums_set)


print(solution([1, 2, 3, 4, 5]))
print(solution([1, 2, 3, 3, 4]))
print(solution([1, 2, 3, 4, 4]))
