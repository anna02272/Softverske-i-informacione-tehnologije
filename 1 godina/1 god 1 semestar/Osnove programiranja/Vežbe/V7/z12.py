"""Zadatak  12. Digitron.  Korisnik  unosi  sa  tastature 2  broja 
 i  operaciju  (string)  i  dobija  rezultat. 
Obezbediti reakciju na nevalidne unose."""

def main():
    num1 = input("Unesite prvi broj: ")
    num2 = input("Unesite drugi broj: ")
    print("Operacije: \n mnozenje \n deljenje \n sairanje \n oduzimanje")
    operacija = input("Unesite operaciju: ")
    digitron(num1,num2,operacija)

def digitron(num1,num2,operacija):

    if operacija == 'sabiranje':
        rezultat = int(num1) + int(num2)
        print("Rezultat je",rezultat)
    
    if operacija == 'oduzimanje':
        rezultat = int(num1) - int(num2)
        print("Rezultat je",rezultat)
    if operacija == 'mnozenje':
        rezultat = int(num1) * int(num2)
        print("Rezultat je,",rezultat)
    if operacija == 'deljenje':
        rezultat = int(num1) / int(num2)
        if (num2 != 0):
            print("Nemoguce deliti sa nulom")
        else:
            print("Rezultat je",rezultat) 
 
    if (operacija != 'sabiranje' and operacija != 'oduzimanje' and operacija !='deljenje' and operacija !='mnozenje'):
        print("Pogresan unos")
     
main()