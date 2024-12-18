#include <stdio.h>
#include <math.h>

void main()
{
    int x, kvadrat;
    float koren;
    printf ("\nUnesi celi broj x: ");
    scanf("%d", &x);

    if (x==0)
        printf ("/nFukncija je f(%d)= 0", x);
    else
            if (x >0)
        koren = sqrt (x);
        printf("\nFunckija je f(%d) = %.2f", x, koren);

    else
        kvadrat = x*x
        printf("\nFunkcija f(%d) = %d", x, kvadrat);

return;
