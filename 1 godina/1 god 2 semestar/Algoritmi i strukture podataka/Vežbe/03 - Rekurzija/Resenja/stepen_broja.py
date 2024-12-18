#naći stepen prosleđenog broja, funkcija ima dva parametra - prvi je broj za koji se računa stepen, a drugi je stepen

def power(num, topwr):
    if topwr == 0:
        return 1
    else:
        return num * power(num, topwr - 1)


print(power(3, 9))
print(power(2, 8))
