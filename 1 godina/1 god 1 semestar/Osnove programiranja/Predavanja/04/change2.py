# change2.py
# Racuna ukupnu vrednost gvozdenjaka u dolarima
# Ova verzija prikazuje ukupnu vrednost u centima

print("Change Counter\n")

print("Please enter the count of each coin type.")
quarters = eval(input("Quarters: "))
dimes = eval(input("Dimes: "))
nickels = eval(input("Nickels: "))
pennies = eval(input("Pennies: "))

total = quarters * 25 + dimes * 10 + nickels * 5 + pennies

print("The total value of your change is ${0}.{1:0>2}".format(total//100, total%100))
