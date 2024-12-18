#include <stdio.h>
#include <math.h>

int main()
{
    int stranica, obim, povrsina;
    float d;

    printf("\n Unesi stranicu kvadrata: ");
    scanf("%d", &stranica);

    povrsina = stranica * stranica;
    obim = 4 * stranica;
    d = stranica * sqrt(2);

    printf("\nPovrsina je: %d", povrsina);
    printf("\nObim je: %d", obim);
    printf("\nDijagonala je: %.2f", d);
    return 0;
}
