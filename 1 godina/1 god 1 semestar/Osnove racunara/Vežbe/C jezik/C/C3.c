#include <stdio,h>
#include <stdlib.h>
main()
{
    int a, b;
    scanf("%d", &a);
    scanf("%d", &b);

    printf("a+b=%d\n", a+b);
    printf("a-b=%d\n", a-b);
    printf("a*b=%d\n", a*b);
    printf("a/b=%f\n", (float)a/b);
    printf("a%b=%d\n", a%b);
    return 0;
}
