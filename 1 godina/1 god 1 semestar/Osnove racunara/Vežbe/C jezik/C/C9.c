#include <stdio.h>
#include <stdlib.h>

int main()
{
    float a, b, p, o;

    printf("\nUcitaj duzine stranica a i b:\n");
    scanf("%f%f", &a, &b);

    p = a*b;
    o = 2*(a+b);

    printf("\nPovrsina je %.2f\nOBim je %.2f", p, o);

    return;
}
