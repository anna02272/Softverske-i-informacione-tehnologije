#za prosleđeni niz integer vrednosti napisati funkciju koja postavlja sve nule na kraj niza pritom održavajući redosled ostalih elemenata
def solution(nums):
    for i in nums:
        if 0 in nums:
            nums.remove(0)
            nums.append(0)
    return nums


print(solution([0, 2, 0, 3, 5]))
print(solution([1, 5, 0, 0, 6, 0, 10, 11, 0, 3]))
