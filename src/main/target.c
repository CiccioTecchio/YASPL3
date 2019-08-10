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

printf("%s\n", yasplBuffer);

scanf("%d",&f);
scanf("%s",str);
f = 3 + 3;
f = 12;
d = 3;
c = 13.4;
t = 's';
strcpy(str, "ciao");
strcpy(str2, "Carlo");

strcpy(yasplBuffer,"Moltiplicazione ");
sprintf(toParse,"%f", c);
strcat(yasplBuffer, toParse);
strcpy(toParse,": inserisci due numeri positivi");
strcat(yasplBuffer, toParse);
sprintf(toParse,"%s", "true");
strcpy(toParse," COCOMOCN ");
strcat(yasplBuffer, toParse);
sprintf(toParse,"%c",'x');
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);


strcpy(yasplBuffer,"Stringa");
sprintf(toParse,"%c", 'a');
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);

strcpy(yasplBuffer,"Mario");
sprintf(toParse,"%d", 3);
strcat(yasplBuffer, toParse);
strcpy(str, yasplBuffer);
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