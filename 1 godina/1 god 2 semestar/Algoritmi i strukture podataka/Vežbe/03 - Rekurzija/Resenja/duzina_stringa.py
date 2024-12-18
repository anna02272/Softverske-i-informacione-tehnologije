#za prosleđeni string odrediti njegovu dužinu
def duzina_stringa(str):
    if str == '':
        return 0
    else:
        return 1 + duzina_stringa(str[1:])


print(duzina_stringa("Pera Peric"))
print(duzina_stringa("Pera"))
