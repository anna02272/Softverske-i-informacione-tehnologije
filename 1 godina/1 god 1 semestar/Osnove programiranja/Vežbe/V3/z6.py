import math

def main():
    r = eval(input("Unesi poluprecnik: "))
    c = eval(input("unesi cenu cele pice: " ))
    povrsina = r**2 * math.pi
    print("povrsina je ",(round(povrsina,2)))
    cenapokvadratu = c / povrsina
    print(cenapokvadratu)

main()