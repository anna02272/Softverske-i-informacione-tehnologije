#za prosleđeni string ispisati slova u obrnutom redosledu
def reverse(string):
    if len(string) == 0:
        return string
    else:
        return reverse(string[1:]) + string[0]


print(reverse("Pera Peric je super mega car."))
