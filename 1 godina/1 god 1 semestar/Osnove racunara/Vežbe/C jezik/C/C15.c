#include <stdio.h>
#include <math.h>


void main()
{
    float x, y, a, b, broj1, broj2;
    printf("\nUcitaj brojeve x i y: ");
    scanf ("%f%f", &x, &y);

    printf("\nUcitaj brojeve a i b: ")
    scanf("%f%f", &a, &b);

    broj1= abs(x-y) / (a*b);
    broj2 = (x/y) / abs(a-b)

    printf("\nZbir brojeva je &.2f", broj1+broj2);


    return;
}
