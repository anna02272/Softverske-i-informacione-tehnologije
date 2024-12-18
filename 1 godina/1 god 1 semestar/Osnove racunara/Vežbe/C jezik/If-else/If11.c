#include <math.h>
#include <stdio.h>

void main()
{
    float a, b, c, det, m;
    float x1, x2, rx, ix;

    printf("\nUnesi koeficijente jedinice a, b i c: ");
    scanf("%d %d %d", &a, &b, &c);

    det = b*b - 4*a*c;

    if (det==0)
        x1 = (-b) / (2*a);
    printf("\nJednacina ima jedno resenje: %.2f", x1);

    else
        if (det>0)
            x1 = (-b+sqrt(det))/(2*a);
            x2 = (-b-sqrt(det))/(2*a);

            printf ("\nResenja jednacine su x1 = %.2f i x2 = %.2f", x1, x2);

        else
            rx = (-b)/(2*a);
            m = abs(det);
            ix = sqrt(m)/(2*a);

            printf("\nx1 = %.2f + %.2fi i x2 = %.2f - %.2fi ", rx, ix, rx, ix);

    return;
}
