#include <stdio.h>
#include <stdlib.h>

int main()
{
    char i;
    for(i=1; i<127; i++)
        printf("%d %c\t", i, i);

    return 0;
}
