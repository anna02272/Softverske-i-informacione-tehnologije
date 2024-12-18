#include <stdio.h>
#include <math.h>

int main()
{
    int x1, y1, x2, y2, dx, dy;
    float c;

    printf("\nUpisite koordinate tacke A(x1 i y1):\n");
    scanf("%d%d", &x1, &y1);
    printf("\nUpisite koordinate tacke b(x2 i y2):\n");
    scanf("%d%d", &x2, &y2);

    dx = x2-x1;
    dy = y2-y1;
    c = sqrt((dx*dx) + (dy*dy));

    printf("\nDve tacke su udaljene %.2f", c);

    return;
}
