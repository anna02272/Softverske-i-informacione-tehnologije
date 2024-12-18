"""Zadatak 6. Napiši funkciju koja ispisuje sve brojeve između 
1200 i 2500 koji su deljivi sa 7 i 11."""
def main():
    for i in range(1200,2501):
        if (i % 7 == 0) and (i % 11 == 0):
            print(i)
    print("Ovo su svi brojevi deljivi sa 7 i 11")


main()


