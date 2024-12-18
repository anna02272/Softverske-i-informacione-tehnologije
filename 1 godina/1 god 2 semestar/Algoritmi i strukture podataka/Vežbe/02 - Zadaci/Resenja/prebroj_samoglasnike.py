#za prosleđeni string ispisati ukupan broj pojavljivanja samoglasnika i listu samoglasnika koji se pojavljuju (bez ponavljanja)
def solution(text):
    samoglasnici = "aeiuoAEIOU"
    slova = []
    for slovo in text:
        if slovo in samoglasnici:
            slova.append(slovo)
    print(len(slova))
    slova = set(slova)
    print(list(slova))


solution('skafiskafnjak')
solution('pErapericsupermegacar')
