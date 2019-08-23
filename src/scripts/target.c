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

int opt,n1,n2,res = 0;
void somma(int op1, int op2, int *res){
*res = 0;
*res = op1 + op2;
}
void moltiplicazione(int op1, int op2, int *res){
int i = 0;
*res = 0;
while(i < op2){
*res = *res + op1;
i = i + 1;

}
}
void potenza(int b, int e, int *res){
int i = 1;
*res = 1;
while(i <= e){
*res = *res * b;
i = i + 1;

}
}
void fibonacci(int num){
int i = 1,a = -1,b = 1;
res = 0;
while(i <= num){
res = a + b;
a = b;
b = res;
i = i + 1;
printf("%d\n",res);

}
}
void menu(){

strcpy(yasplBuffer,"Scegli un opzione:\n");
strcat(yasplBuffer, "1. Somma fra due interi\n");
strcpy(toParse,"2. Moltiplicazione fra due interi\n");
strcat(yasplBuffer, toParse);
strcpy(toParse,"3. Elevazione a potenza\n");
strcat(yasplBuffer, toParse);
strcpy(toParse,"4. Successione di Fibonacci\n");
strcat(yasplBuffer, toParse);
strcpy(toParse,"0. Esci");
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);

}

int main(void){
menu();
scanf("%d",&opt);
while(!(opt == 0)){
if(opt == 1){
printf("%s\n","Inserisci due interi");
scanf("%d",&n1);
scanf("%d",&n2);
somma(n1,n2,&res);

strcpy(yasplBuffer," + ");
sprintf(toParse,"%d", n1);
strcat(toParse, yasplBuffer);
strcpy(yasplBuffer, toParse);
sprintf(toParse,"%d",n2);
strcat(yasplBuffer, toParse);
strcpy(toParse," = ");
strcat(yasplBuffer, toParse);
sprintf(toParse,"%d",res);
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);

}
else{
if(opt == 2){
printf("%s\n","Inserisci due interi");
scanf("%d",&n1);
scanf("%d",&n2);
moltiplicazione(n1,n2,&res);

strcpy(yasplBuffer," * ");
sprintf(toParse,"%d", n1);
strcat(toParse, yasplBuffer);
strcpy(yasplBuffer, toParse);
sprintf(toParse,"%d",n2);
strcat(yasplBuffer, toParse);
strcpy(toParse," = ");
strcat(yasplBuffer, toParse);
sprintf(toParse,"%d",res);
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);

}
else{
if(opt == 3){
printf("%s\n","Inserisci basa ed esponente");
scanf("%d",&n1);
scanf("%d",&n2);
potenza(n1,n2,&res);

strcpy(yasplBuffer," ^ ");
sprintf(toParse,"%d", n1);
strcat(toParse, yasplBuffer);
strcpy(yasplBuffer, toParse);
sprintf(toParse,"%d",n2);
strcat(yasplBuffer, toParse);
strcpy(toParse," = ");
strcat(yasplBuffer, toParse);
sprintf(toParse,"%d",res);
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);

}
else{
if(opt == 4){
printf("%s\n","Inserisci il numero per vedere la successione di Fibonacci");
scanf("%d",&n1);
if(n1 >= 0){
printf("%s\n","Questa è la sequenza:");
fibonacci(n1);
}
else{
printf("%s\n","Inserisci numero positivo");
}
}
}
}
}
menu();
scanf("%d",&opt);

}
return 0;
}