# dateconvert2.py
# Konvertuje dan, mesec i godinu u dva formata

# ucitaj dan, mesec i godinu
day, month, year = eval(input("Unesite dan, mesec i godinu (d, m, g): "))

date1 = str(day) + "." + str(month) + "." + str(year) + "."

months = ["januar", "februar", "mart", "april", 
          "maj", "jun", "jul", "avgust", 
          "septembar", "oktobar", "novembar", "decembar"]
monthStr = months[month-1]
date2 = str(day) + ". " + monthStr + " " + str(year) + "."

print("Datum je", date1, "ili", date2)
