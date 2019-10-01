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

int c = 3;
string ciao = "ciao";
string ciao2 = "ciao2";
bool b;

int main(void){
b = strcmp(ciao, ciao2)==0;
printf("%s\n", b? "true": "false");
return 0;
}