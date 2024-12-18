import math
def main():
    a = eval(input("Unesi stranicu a:"))
    b = eval(input("Unesi stranicu b:"))
    c = eval(input("Unesi stranicu c:"))

    S = (a + b + c) / 2
    A = math.sqrt(S * (S - a) * (S - b) * (S-c))

    print("S je:", round(S,2))
    print("A je:", round(A,2))

main()