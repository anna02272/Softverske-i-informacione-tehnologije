#include <stdio.h>

void main()
{
    int br1, br2, br3, max;

    printf ("\nUnesi tri broja: ");
    scanf ("%d %d %d", &br1, &br2, &br3);

    max = br1;
    if(br2 > max)
        max = br2;
    if(br3 > max)
        max = br3;
    printf("\nNajveci broj je %d", max);

    return;
}
