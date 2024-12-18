#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n, suma=0;
    scanf("%d", &n);
    while(n>0)
    {
        suma += n%10;
        n /= 10;
    }
    printf("%d", suma);
    return 0;
}
