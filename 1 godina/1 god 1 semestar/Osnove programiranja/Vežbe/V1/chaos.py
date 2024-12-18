# File: chaos.py
# A simple program illustrating chaotic behavior.

def main():
	print('This program illustrates a chaotic function')	
	x = eval(input("Enter x between 0 and 1: "))

	for i in range(10): 
		x = 3.9 * x * (1 - x)	
		print(x)

main()

def main():
	print("Ovaj program ilustuje haoticnu funkciju")
	x =eval(input("Unesi broj izmedju 0 i 1:"))

	for i in range(10):
		x = 3.9 * x * (1-x)
		print(x)

main()								