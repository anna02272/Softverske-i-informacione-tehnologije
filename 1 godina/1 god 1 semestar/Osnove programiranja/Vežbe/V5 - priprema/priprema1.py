def main():

    x = eval (input("Unesite broj molekula vodonika:"))
    y = eval (input("Unesite broj molekula ugljenika:"))

    masa_H = (1.0079) * x
    masa_C = (12.011) * y

    masa_ugljovodonika = masa_H  + masa_C 
    print("Masa molekula ugljovodonika je:", masa_ugljovodonika)

main()