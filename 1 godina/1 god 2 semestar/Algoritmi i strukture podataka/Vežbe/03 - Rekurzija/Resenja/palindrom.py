#za prosleđeni string proveriti da li je palindrom
def je_palindrom(rec):
    """Vraca True ako je rec palindrom, False ako nije"""
    if len(rec) <= 1:
        return True
    else:
        return rec[0] == rec[-1] and je_palindrom(rec[1:-1])


print(je_palindrom("anavolimilovana"))
print(je_palindrom("nijepalindrom"))
