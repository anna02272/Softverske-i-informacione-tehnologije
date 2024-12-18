#include<stdio.h>
void main()
{
    int broj, sto, des, jed, min;
    printf("\n unesite trocifreni broj: ");
    scanf("%d",&broj);

    sto=broj/100;
    des=(broj%100)/10;
    jed= broj % 10;
    min=sto;
    if(des<min)
        min=des;
    if(jed<min)
        min=jed;
    printf("\nNajmanja pozicija broja %d je %d",broj,min);
    return;
}
