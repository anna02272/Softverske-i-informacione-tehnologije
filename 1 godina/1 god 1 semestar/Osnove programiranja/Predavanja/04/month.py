# month.py
#  Ispisuje skraceni naziv meseca za dati broj

def main():
    months = "JanFebMarAprMajJunJulAvgSepOktNovDec"

    n = eval(input("Unesite broj meseca (1-12): "))

    pos = (n-1) * 3
    
    monthAbbrev = months[pos:pos+3]

    print("Skracenica za mesec je", monthAbbrev + ".")

main()
