"""Zadatak6.Najveći zajednički delilac se računa pomoću Euklidovog algoritma: za brojeve m i n se
ponavlja ni+1=mi, mi+1=ni%mi dok m ne stigne do 0. Kada m postane 0, n je naveći zajednički delilac.
Napiši funkciju koja implementira Euklidov algoritam za računanje najvećeg zajedničkog delioca.
Funkcija prima dva prirodna broja, a vraća njihov najveći zajednički delilac.
Primer izvršavanja programa:
>>> print(nZD(25,15)) 5"""

def main():
    x = eval(input("Unesite prvi broj: "))
    y = eval(input("Unesite drugi broj: "))
    print(NZD(x,y))

def NZD (x,y):
    while (x!= y):
        step = y
        y = x % y 
        x = step
    return y 

main()