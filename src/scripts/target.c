#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

typedef int bool;
#define false 0
#define true 1
#define STRING_CONST 256

typedef char string[STRING_CONST];
string yasplBuffer;
string toParse;

int a;
int b;

int main(void){
printf("%s\n","Calcolo il resto fra due numeri\nInserisci due numeri");
scanf("%d",&a);
scanf("%d",&b);
printf("%d\n",a % b);
return 0;
}