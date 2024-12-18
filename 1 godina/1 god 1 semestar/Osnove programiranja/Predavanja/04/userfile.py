# userfile.py
# Program kreira fajl sa korisnickim imenima u batch modu

print("Ovaj program kreira username fajl od fajla sa")
print("stvarnim imenima korisnika.")

infileName = input("Unesite ime fajla sa imenima: ")
outfileName = input("Unesite ime username fajla: ")

infile = open(infileName, "r")
outfile = open(outfileName, "w")

# obradi svaki red u ulaznom fajlu
for line in infile:
    # izdvoj ime i prezime (razdvojeni razmacima)
    first, last = line.split()
    # kreiraj username
    uname = (first[0]+last[:7]).lower()
    # upisi username u izlazni fajl
    outfile.write(uname+"\n")

# zatvori oba fajla
infile.close()
outfile.close()

print("Username fajl", outfileName, "je kreiran.")
