# -+- encoding: utf-8 -+-
def bubble_sort(array):
    n = len(array)
    for i in range(n-1):
        for j in range(0, n-i-1):
            if array[j] > array[j + 1] :
                array[j], array[j + 1] = array[j + 1], array[j]

if __name__ == "__main__":
    array = [2, 6, 7, 8, 1, 3, 9, 9, 4]
    bubble_sort(array)
    print(array)