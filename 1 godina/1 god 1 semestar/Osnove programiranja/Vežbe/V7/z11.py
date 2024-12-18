"""Zadatak 11. Napiši program koji prima datum u obliku dd/mm/gggg 
i računa redni broj dana  u godini. 
Redni broj dana u godini se računa na sledeći način: 
1.  danUGodini = 31(mm - 1) + dd  
2.  ako je mm posle februara danUGodini umanji za (4mm+23)/10  
3. ako je prestupna godina i mm posle februara danUGodini uvećaj za 1 """

def main():
    datum = input("Unesite datum: ")
    dan_u_godini(datum)

def prestupna(godina):
    if (godina % 100 == 0):
        if (godina % 400 == 0):
            return 1
            
        else:
            return 0
    else:
        if (godina % 4 == 0):
            return 1
        else:
            return 0 

def dan_u_godini(datum):
    dan = int(datum.split("/")[0])
    mesec = int(datum.split("/")[1])
    godina = int(datum.split("/")[2])

    dann_u_godini = int(31 * (mesec - 1) + dan)

    if (mesec > 2) and prestupna(godina):
        dann_u_godini = int(dann_u_godini + 1)
    if (mesec > 2):
        dann_u_godini = int(dann_u_godini - (4 * mesec + 23) / 10)
        
    print(dann_u_godini)
    
    

main()