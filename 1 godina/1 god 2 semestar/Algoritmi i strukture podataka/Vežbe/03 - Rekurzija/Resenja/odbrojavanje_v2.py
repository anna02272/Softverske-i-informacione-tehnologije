##za broj ispisati odbrojavanje od nule do njega
def countdown(n):
    if n >= 0:
        countdown(n-1)
        print(n)


countdown(5)
