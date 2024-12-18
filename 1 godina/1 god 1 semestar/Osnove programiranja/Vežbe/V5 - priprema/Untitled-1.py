# 2/1 + 4/3 + 16/5 + 256/7 + ...
def main():
    n = input("Unesite recenicu:")
    reci = n.split(" ")
    invertovano = ""
    rec = len(reci)
    for i in range(rec-1,-1,-1):
        invertovano = invertovano + reci[i] + "#"
    print(invertovano[:-1])


   


    


main()