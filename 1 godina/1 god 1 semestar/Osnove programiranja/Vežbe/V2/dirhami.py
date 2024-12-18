def main():
    x = eval(input("unesite broj dirhama"))     # x - smestamo dirhame
    y = eval(input("unesite broj eura"))        # evri
    z = 0.23 * x                                # racunamo evre na osnovu x
    n = 4.28 * y                                # racunamo dirahame na osnovu y
    print(x, "dirhama je", z, "eura")
    print(y, "eura je", n, "dirhama")
   
   
main()