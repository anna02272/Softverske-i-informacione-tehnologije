#include <stdio.h>

int main()
{
    int broj1, broj2, broj3;
    float ars;

    printf("\nUpisi tri cela broja:\n");
    scanf("%d%d%d", &broj1, &broj2, &broj3);

    ars = (broj1 + broj2 + broj3) / 3.0;

    printf("\nAritmeticka sredina je %.3f", ars);

    return;
}
