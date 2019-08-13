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

double sommagrande,sommapiccola;
int i;
double x,y,risultato;
bool grande;
void moltiplicazione(double x, double y, double *res, bool *grande){
double risultato = x * y;
if((x * y >= 100)){
grande = true;
}
else{
grande = false;
}
res = risultato;
}

int main(void){
sommagrande = 0;
sommapiccola = 0;
printf("%s\n","Questo programma permette di svolgere una serie di moltiplicazioni");
printf("%s\n","accumulando i risultati < 100 in sommagrande e quelli < 100 in sommapiccola");
i = -1;
while((i <= 0)){
printf("%s\n","Quante moltiplicazioni vuoi svolgere? (inserire intero > 0)");
scanf("%d",&i);

}
while((i > 0)){
x = -1;
y = -1;
while(!(((x > 0) && (y > 0)))){

strcpy(yasplBuffer,"Moltiplicazione ");
sprintf(toParse,"%d", i);
strcat(yasplBuffer, toParse);
strcpy(toParse,": inserisci due numeri positivi");
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);

scanf("%lf",&x);
scanf("%lf",&y);

}
moltiplicazione(x,y,risultato,grande);
if(grande){
sommagrande = sommagrande + risultato;
}
else{
sommapiccola = sommapiccola + risultato;
}
i = i - 1;

}

strcpy(yasplBuffer,"Valore di sommagrande: ");
sprintf(toParse,"%lf", sommagrande);
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);


strcpy(yasplBuffer,"Valore di sommapiccola: ");
sprintf(toParse,"%lf", sommapiccola);
strcat(yasplBuffer, toParse);

printf("%s\n", yasplBuffer);

printf("%s\n","ciao");
return 0;
}