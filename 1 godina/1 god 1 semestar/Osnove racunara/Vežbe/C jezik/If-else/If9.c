#include <stdio.h>

void main()
{
    int broj;

    printf("\nUnesi dva cela broja: ");
    scanf("%d %d", &br1, &br2);

    if (br1 >br2)
        printf("\nPrvi je veci a razlika je %d",br1 -  br2);
    else
        if (br2 >br1)
            printf("\nDrugi je veci, a razlika je %d", br2-br1);
        else
            printf("\nBrojevi su isti")

return;
}

