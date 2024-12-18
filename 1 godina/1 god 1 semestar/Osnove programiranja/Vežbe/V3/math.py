import math

def main():

    r = eval(input("unesi prvi broj: "))
    a = eval(input("unesi drugi broj: "))

    rezlutat = math.sqrt(r*(math.cos(a))**2 + r*(math.sin(a))**2)
    print(rezlutat)

main()