#include <stdio.h>
#include <stdlib.h>
#include<string.h>
#include <unistd.h>

typedef int bool;
#define false 0
#define true 1
#define STRING_CONST 256

typedef char string[STRING_CONST];
string yasplBuffer;
string toParse;

int f;
int d;
double c;
char t;
string str;
string str2;
string str3;
bool b;

int main(void){
b = false;

strcpy(yasplBuffer,"Pippone");
sprintf(toParse,"%s", b? "true" : "false");
strcat(toParse, yasplBuffer);
strcpy(yasplBuffer, toParse);
;
printf("%s\n", yasplBuffer);

d = 3 + 8;
printf("%d\n",d);
f = 12;
d = 3;
c = 13.4;
t = 's';
strcpy(str, "ciao");
strcpy(str2, "Carlo");
printf("%d\n",f);
printf("%d\n",d);
printf("%f\n",c);
printf("%c\n",t);
printf("%s\n",str);

strcpy(yasplBuffer,str);
strcat(yasplBuffer, str2);

printf("%s\n", yasplBuffer);


strcpy(yasplBuffer,str);
sprintf(toParse,"%d", 2);
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);


strcpy(yasplBuffer,str);
sprintf(toParse,"%d", f);
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);


strcpy(yasplBuffer,str);
sprintf(toParse,"%f", 12.4);
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);


strcpy(yasplBuffer,str);
sprintf(toParse,"%s", "true");
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);


strcpy(yasplBuffer,str);
sprintf(toParse,"%s", b? "true" : "false");
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);

return 0;
}