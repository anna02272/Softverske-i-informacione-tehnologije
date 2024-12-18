#include <stdio.h>

int main()
{
    int ukupno, h, min, sec;

    printf("Ucitaj ukupan broj sekundi:\n");
    scanf("%d", &ukupno);

    h = ukupno / 3600;
    min = (ukupno%3600)/60;
    sec = (ukupno%3600)%60;

    printf("\n%d sekundi iznosi: ", ukupno);
    printf("\n%d sat, %d minute i %d sekunde", h, min, sec);

    return;
}
