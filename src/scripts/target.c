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

double op1,op2,res;
int choise;
string menu;
void resetResTo0(double *res){
*res = 0;
}
void resetResTo1(double *res){
*res = 1;
}
void addizione(double op1, double op2, double *res){
*res = op1 + op2;
}
void sottrazione(double op1, double op2, double *res){
*res = op1 - op2;
}
void moltiplicazione(double op1, double op2, double *res){
*res = op1 * op2;
}
void divisione(double op1, double op2, double *res){
*res = op1 / op2;
}
void quadrato(double op, double *res){
*res = op * op;
}
void printMenu(){

strcpy(yasplBuffer,"Scegli l'operazione da svolgere\n");
strcat(yasplBuffer, "1. Addizione\n");
strcpy(toParse,"2. Sottrazione\n");
strcat(yasplBuffer, toParse);
strcpy(toParse,"3. Moltiplicazione\n");
strcat(yasplBuffer, toParse);
strcpy(toParse,"4. Divisione\n");
strcat(yasplBuffer, toParse);
strcpy(toParse,"5. Quadrato\n");
strcat(yasplBuffer, toParse);
strcpy(toParse,"0. esci");
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);

}
void readOps(){
printf("%s\n","Inserisci due numeri");
scanf("%lf",&op1);
scanf("%lf",&op2);
}

int main(void){
printMenu();
scanf("%d",&choise);
while(!(choise == 0)){
if(choise == 1){
readOps();
resetResTo0(&res);
addizione(op1,op2,&res);
printf("%lf\n",res);
}
else{
if(choise == 2){
readOps();
resetResTo0(&res);
sottrazione(op1,op2,&res);
printf("%lf\n",res);
}
else{
if(choise == 3){
readOps();
resetResTo1(&res);
moltiplicazione(op1,op2,&res);
printf("%lf\n",res);
}
else{
if(choise == 4){
readOps();
resetResTo1(&res);
divisione(op1,op2,&res);
printf("%lf\n",res);
}
else{
if(choise == 5){
printf("%s\n","Inserisci un numero");
scanf("%lf",&op1);
resetResTo1(&res);
quadrato(op1,&res);
printf("%lf\n",res);
}
else{
if((choise < 0 || choise > 5)){
printMenu();
scanf("%d",&choise);
}
}
}
}
}
}
printMenu();
scanf("%d",&choise);

}
printf("%s\n","Finito");
return 0;
}