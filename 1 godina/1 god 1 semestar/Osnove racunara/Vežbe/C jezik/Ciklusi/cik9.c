#include <stdio.h>
#include <stdlib.h>

int main()
{
    int n, obrnuti = 0;
    scanf("%d", &n);
    while(n>0)
    {
        obrnuti = obrnuti*10 + n%10;
        n /=10;
    }
    printf("%d", obrnuti);
    return 0;
}
