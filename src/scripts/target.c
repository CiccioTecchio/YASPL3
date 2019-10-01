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

int i;

int main(void){
i = 10;
do {
printf("%d\n",i);
i = i - 1;
} while(i >= 0);
return 0;
}