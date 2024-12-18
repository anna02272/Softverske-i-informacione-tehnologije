# printfile.py
# Ispisuje sadrzaj fajla na ekran

fname = input("Unesite ime fajla: ")
infile = open(fname, "r")
data = infile.read()
print(data)
infile.close()
