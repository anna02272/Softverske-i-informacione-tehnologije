import math

def main():
    pi= math.pi

    r = eval(input("Unesite velicinu poluprecnika:"))
    V = (4/3)* pi * r**3
    P = 4 * pi* r**2

    print("Zapremina je: ", V)
    print("Povrsina je: ", P)
    
main()