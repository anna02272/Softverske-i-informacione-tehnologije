"""Zadatak 1. Napiši funkciju koja prima broj bodova na testu, 
a vraća ocenu sa testa. 
Student na testu može da osvoji od 0 do 100 bodova.
 Ocenjivanje je dato u tabeli:  
bodovi ocena 
od 0 do 55 5
od 56 do 65 6   
od 66 do 75 7   
od 76 do 85 8   
od 86 do 95 9   
od 96 do 100 10 
Primer izvršavanja programa: 
>>> ocenjivanje(77) 
 8 
>>> ocenjivanje(95)  
9  
>>> ocenjivanje(96)  
10 """


def main():
    bodovi = eval(input("Unesite bodove: "))
    print(ocenjivanje(bodovi))
    


def ocenjivanje(bodovi):
    if (bodovi >= 0) and (bodovi <=55):
        ocena = 5
    elif (bodovi >=56) and (bodovi <= 65):
        ocena = 6 
    elif (bodovi >= 66) and (bodovi <=75):
        ocena = 7 
    elif (bodovi >=76) and (bodovi <=85):
        ocena = 8 
    elif (bodovi >=86) and (bodovi <=95):
        ocena = 9 
    elif (bodovi >= 96) and (bodovi <=100):
        ocena = 10
    else:
        print("Niste uneli bodove u dobrom opsegu")



    print("Ocena je:",end=" ")    

    return ocena 
    
main()