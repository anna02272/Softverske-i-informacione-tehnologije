#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n, i, suma=0, fakt=1;
    scanf("%d", &n);

    for(i=1; i<=n; i++)
        suma += i;
    printf("Suma prvih %d je %d\n", n, suma);

    for(i=1; i<=n; i++)
        fakt *= i;
    printf("%d! = %d\n", n, fakt);

    return 0;
}
