"""Zadatak 13. Napisati funkciju za utvrđivanje da li je string palindrom. """

def main():
    word = input("Unesite rec: ")
    palindrome(word)

def palindrome(word):

    if (word == word[::-1]):
        print("String je palindrom")
    else:
        print("String nije palindrom")
  

main()