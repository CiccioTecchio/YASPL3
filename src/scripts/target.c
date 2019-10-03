#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <math.h>

typedef int bool;
#define false 0
#define true 1
#define STRING_CONST 256

typedef char string[STRING_CONST];
string yasplBuffer;
string toParse;

int i;

int main(void){
scanf("%d",&i);
++i;
do {
--i;
printf("%d\n",i);
} while(i >= 0);
return 0;
}