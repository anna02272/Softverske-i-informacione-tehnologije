# chaos.py
# Program ilustruje haoticno ponasanje :)

print("Ovaj program ilustruje haoticno ponasanje")
x = eval(input("Unesite broj izmedju 0 i 1: "))
for i in range(10):
    x = 3.9 * x * (1 - x)
    print(x)
