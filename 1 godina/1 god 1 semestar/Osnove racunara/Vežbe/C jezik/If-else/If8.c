#include<stdio.h>

void main()
{
    int broj;
    printf("\nUnesi celi broj: ");
    scanf("%d", &broj);

    if(broj<0)
        printf("Sledbenik je %d",broj+1);
    else
        if(broj>0)
        printf("Prethodnik je %d",broj-1);
    else
        printf("\nBroj je jednak nuli");
    return;
}
