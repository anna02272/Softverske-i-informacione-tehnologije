#include <stdio.h>
#include <stdlib.h>

int main ()
{
    int i;
    for (i = 1; i <= 500; i++)
        if(i%3 == 0 && i%4 == 0 && i%5 == 0 && i%7 == 0)
        {
            printf("%d\n", i);
            break;
        }
    return 0;
}
