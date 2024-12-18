"""Zadatak 2. Indeks telesne mase se računa po sledećoj formuli
 BMI = m\h**2 u kojoj je m masa u 
kilogramima, a h visina u metrima.
 U tabeli je data preporučena klasifikacija indeksa telesne 
mase: 
 
BMI Klasifikacija  
 <18,5 Pothranjenost 
 18,5 - 25 Idealna telesna težina 
 25-30 Preterana telesna težina   
 >30 Gojaznost    
 
Napiši funkciju koja prima težinu u kilogramima i visinu,
 a vraća kategoriju iz klasifikacije BMI. 
 
Primer izvršavanja programa: 
 
>>> print(indeksTelesneMase(55,1.8))  
'pothranjenost'  
>>> print(indeksTelesneMase(75,1.8))  
'idealna telesna tezina'  
>>> print(indeksTelesneMase(81,1.8))  
'preterana telesna tezina'  
>>> print(indeksTelesneMase(120,1.8))  
'gojaznost'"""


def izracunaj_bmi(tezina,visina):
    bmi=(tezina)/(visina**2)
    print(bmi)
    return bmi

def provjeri_index(bmi):
    if bmi<18:
        print("pothranjenost")
    elif bmi>= 18.5 and bmi<=25:
        print("Idealna tjelesna tezina")
    elif bmi>=25 and bmi<=30:
        print("Pretjerana tjelesna tezina")
    else:
        print("Gojaznost")

def main():
    tezina=eval(input("Unesite svoju tezinu u kg:"))
    visina=eval(input("Unesite svoju visinu u m:"))
    print(provjeri_index(izracunaj_bmi(tezina,visina)))

main()