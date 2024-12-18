
def main():
    x = eval(input("Unesite koliko brojeva zelite da se izracuna prosek: ")) # x = 5
    zbir = 0
   
    for i in range(x):
        broj = eval(input("Unesite broj: "))
        zbir = zbir + broj
   
    prosek = zbir / x
    print("Prosek je", prosek)


main()