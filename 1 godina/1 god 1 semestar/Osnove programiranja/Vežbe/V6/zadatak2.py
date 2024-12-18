'''Zadatak 2.Napiši funkciju kojoj se prosleđuju dva stringa i vraća novi string koji se sastoji od
dva puta ponovljena prva tri karaktera iz prvog stringa i poslednja tri karaktera drugog stringa.'''

def main():
    a = input("Unesi prvi string:")
    b = input("Unesi drugi string:")
    print(formula(a,b))

def formula(string1, string2):
    x = string1[0:3]* 2 + string2[-3:]
    return(x)

main()


# def ponovi_string(string, broj_ponavljanja):
    #return string.upper() * broj_ponavljanja