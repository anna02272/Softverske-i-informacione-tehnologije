#include <stdio.h>

void main()
{
    int br1, br2, br3, min, sred, max;

    printf ("\nUnesi tri broja: ");
    scanf ("%d %d %d", &br1, &br2, &br3);

    if (br !=br2 && br1 1=br3 && br2 !=br3)
        max=br1;
        if (br2 > max)
            max = br2;
        if (br3 > max)
            max = br3;

        min = br1;
        if (br2 < min)
            min = br2;
        if (br3 < min)
            min = br3;

        sred = (br1 + br2 + br3) - (min + max);

        printf("\n %d < %d < %d", min, sred, max);

        else
            printf ("\nNeki brojevi su isti!");

        return;
}
