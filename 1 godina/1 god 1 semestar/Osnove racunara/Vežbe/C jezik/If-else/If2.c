#include <stdio.h>
#include <stdlib.h>

int main ()
{
    int x;
    scanf ("%d", &x);
    if (x > 0)
        printf("Broj je pozitivan\n");
    else if(x<0)
        printf("Broj je negativan\n");
    else
        prinf("Broj je jednak nuli\n");
    return 0;

}
