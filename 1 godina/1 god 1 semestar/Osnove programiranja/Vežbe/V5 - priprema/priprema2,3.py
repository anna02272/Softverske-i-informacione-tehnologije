import math

def main():
    x1 = eval(input("Unesite x1:"))
    x2 =  eval(input("Unesite x2:"))
    y1 = eval(input("Unesite y1:"))
    y2 =  eval(input("Unesite y2:"))

    m = (y2 - y1) / (x2 - x1)
    print("Nagib prave je:", round(m,2))
    d = math.sqrt(math.pow((x2-x1),2)+math.pow((y2-y1),2))
    print("Rastojanje je:", round(d,2))

main()