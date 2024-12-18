# dateconvert.py
# Konvertuje datum u obliku "dd.mm.yyyy." u "dd. mesec gggg." 

# ucitaj datum
dateStr = input("Unesite datum (dd.mm.yyyy.): ")

# podeli na delove
dayStr, monthStr, yearStr, x = dateStr.split(".")

# konvertuj monthStr u ime meseca
months = ["januar", "februar", "mart", "april", 
          "maj", "jun", "jul", "avgust", 
          "septembar", "oktobar", "novembar", "decembar"]
monthStr = months[int(monthStr)-1]

# ispisi rezultat u formatu dd. mesec gggg.
print("Konvertovani datum je: " + dayStr + ". " + monthStr + " " + yearStr + ".")
