
def main():
    unos = input("Unesite neke reci odvojene zarezom:")
    reci = unos.split(",")    # ("evo", "nekih", "reci", "odvojene", "zarezom")
    broj_elemenata = len(reci)  #5
    

    for i in range(0,broj_elemenata, 2):
        #print(i)
        print(reci[i])




main()