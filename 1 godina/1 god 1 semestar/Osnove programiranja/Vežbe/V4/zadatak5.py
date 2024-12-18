def main ():

    input_string = input("unesite string: ")
    na = input_string.split(" ")     # na = lista reci
    print(na)
    for i in (na):
        b = i[0]              # prvo slovo reci
        print(b.upper(),end="")

main()