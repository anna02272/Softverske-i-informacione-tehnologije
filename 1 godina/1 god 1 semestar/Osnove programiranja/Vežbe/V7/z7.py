"""Zadatak 7. Datum na koji pada uskrs u Gregorijanskom kalendaru
 za godine 1982 – 2048 se računa po formuli:  
a=year%19;  b=year%4;  c=yera%7;  d=(19a+24)%30;  e=(2b+4c+6d+5)%7 
 Datum na koji pada uskrs je 22+d+e mart 
 (ako je vrednost veća od 31 onda je april). 
 Napiši funkciju koja računa datum uskrsa. 
 Funkcija kao parametar prima godinu i vraća poruku sa 
informacijom kada je uskrs ako je godina u zadatom opsegu,
 odnosno poruku da je došlo do 
greške ako godina nije u zadatom opsegu. 
  
 Primer izvršavanja programa: 
  >>> print(uskrs(1994))  
  uskrs je 29. marta 1994. godine  
  >>> print(uskrs(2011))  
  uskrs je 19. aprila 2011. godine  
  >>> print(uskrs(1962))  
  godina nije u predviñenom opsegu"""


def racunanje_uskrsa(godina):
    if godina in range(1982,2048):
          a = godina % 19
          b = godina % 4
          c = godina % 7
          d = (19 * a + 24) % 30
          e = (2 * b + 4 * c + 6 * d + 5) % 7
          datum = 22 + d + e 

          if (datum <= 31):
              return "Uskrs je" + str(datum) + ".marta" + str(godina) + ".godine"
          else:
              datum = datum - 31 
              return "Uskrs je" + str(datum) + " .aprila" + str(godina) + ".godine"
    else:
        return "Godina nije u predvidjenom opsegu"
 


def main():
    godina = int(input("Unesite godinu: "))
    print (racunanje_uskrsa(godina))


main()