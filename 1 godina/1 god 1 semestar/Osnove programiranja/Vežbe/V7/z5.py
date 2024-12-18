'''Dadilja naplaćuje 150din po satu čuvanja dece do 9 sati uveče, 
a 100din po satu čuvanja dece posle 9 sati uveče.
 Napiši funkciju koja kao parametre prima vreme kada dadilja
počinje da čuva deci i kada završava učuvanje dece, 
a vraća poruku o zaradi koja treba se isplati.
 Vreme je zadato u formatu hh:mm, 
 a predpostavlja se da se čuvanje dece odvija u
periodu od 24h.'''

def main():
    pocetno_vreme = input("Unesite pocetno vreme: ")        # '18:50'
    krajnje_vreme = input("Unesite krajnje vreme: ")        # '22:00'
    ukupna_zarada = racunaj_zaradu(pocetno_vreme, krajnje_vreme)
    print("zarada dadilje je", round(ukupna_zarada), "din")


def racunaj_zaradu(pocetak, kraj):
    zarada = 0
    pocetak_sati_i_minuti = pocetak.split(":")          # ['18', '30']
    sati = float(pocetak_sati_i_minuti[0])                      # '18'
    minuti = float(pocetak_sati_i_minuti[1])                    # '50'
    
    kraj_sati_i_minuti = kraj.split(":")          # ['18', '50']
    kraj_sati = float(kraj_sati_i_minuti[0])                      # '18'
    kraj_minuti = float(kraj_sati_i_minuti[1])                    # '50'

    pocetni_sati = sati + minuti / 60               # ukupno koliko sati je proslo od pocetka dana
    krajnji_sati = kraj_sati + kraj_minuti / 60     
    
    ukupno_sati_rada = krajnji_sati - pocetni_sati

    if krajnji_sati < 21:
        zarada = ukupno_sati_rada * 150
    elif pocetni_sati < 21 and krajnji_sati > 21:
        zarada = (21 - pocetni_sati) * 150 + (krajnji_sati - 21) * 100
    else:
        zarada = ukupno_sati_rada * 100


    return zarada




main()