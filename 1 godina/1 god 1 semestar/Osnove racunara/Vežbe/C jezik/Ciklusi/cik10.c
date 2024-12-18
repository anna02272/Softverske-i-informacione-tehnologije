#include <stdio.h>
#include <stdlib.h>

void main()
{
    int i, broj, min, max;
    printf("Upisi 1. broj = \n");
    scanf("%d", &broj);

    min = broj;
    max = broj;

    for(i=2; i<=10; i++)
    {
        printf("Upisi %d. broj = \n", i);
        scanf("%d", &broj);

        if(broj > max)
            max = broj;
        if(broj< min)
            min = broj;
    }

    printf("Najmanji broj je %d\n", min);
    printf("Najveci broj je %d\n", max);

    return;
}
