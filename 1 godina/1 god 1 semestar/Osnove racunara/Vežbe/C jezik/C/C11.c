#include <stdio.h>
#include <math.h>

int main()
{
    float a;

    printf("\nUcitaj stranicu a trougla:");
    scanf("%f", &a);

    printf("\nObim trougla je: %.2f", 3*a);
    printf("\nPovrsina trougla je: %.2f", a*a*sqrt(3)/4);

    return;
}
