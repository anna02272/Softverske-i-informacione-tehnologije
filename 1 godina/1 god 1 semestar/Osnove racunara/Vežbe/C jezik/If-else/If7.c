#include <stdio.h>

void main()
{
    int broj;

    printf("\nUnesi celi broj: ");
    scanf("%d", &broj);

    if(broj % 3 ==0)
        printf("\nBroj je deljiv sa tri");
    else
        printf("\nBroj nije deljiv sa tri");

    return;

