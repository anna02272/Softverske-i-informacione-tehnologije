#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n, i, suma;

    for(n=1; n<10000; n++)
    {
        suma = 0;
        for (i=1; i<n; i++)
            if(n%i == 0)
                suma += i;

        if(suma == n)
            printf("broj %d je savrsen\n", n);
    }

    return 0;
}
