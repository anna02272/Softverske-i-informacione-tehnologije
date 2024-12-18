# 1 UAE dirham = 0.24 eur

def main():
    Dirham = eval(input("Unesite broj dirhama:"))
    Eur = 0.24 * Dirham
    print("Vasi dirhami u evrima su:",Eur)

main()    


dec = input("Da li zelite da konvertujete EUR0 ili UAED:")
if dec == "UAED":
     UAED = eval(input("Unesite iznos UAED:"))
     EUR = UAED * 0.24
     print("Dirham u evrima je:", EUR)

else:
     EUR = eval(input("Unesite iznos EURO:"))
     UAED = EUR * 4,28
     print("Euro u dirhamu je:" ,UAED)