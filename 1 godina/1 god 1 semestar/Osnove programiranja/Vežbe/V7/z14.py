'''Zadatak 14. Napisati funkciju koja prima niz reči, 
izbacuje duplikate i vraća string sastavljen
od preostalih reči odvojenih razmakom.
Primer unosa:  
['hello', 'world', 'hello', 'and', 'practice', 'and', 'makes', 
'perfect', 'and', 'hello', 'world', 'again'] 
 
Rezultat:  
'hello world and practice makes perfect again' 
 '''

def main():
    lista = ['hello', 'world', 'hello', 'and', 'practice', 'and', 'makes', 'perfect', 'and', 'hello', 'world', 'again']
    ispis = formiraj_string(lista)
    print(ispis)
    

def formiraj_string(lista):
    rezultat = ""
    nova_lista_bez_duplikata = []      

    for element in lista: 
        if nova_lista_bez_duplikata.count(element) == 0: 
            nova_lista_bez_duplikata.append(element)

    rezultat = " ".join(nova_lista_bez_duplikata)
    return rezultat





main()