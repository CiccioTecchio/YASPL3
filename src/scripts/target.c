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

int num,res,sum = 0;
bool flag = false;
void fibonacci(int num){
int i = 1,a = -1,b = 1;
while(i <= num){
res = a + b;
a = b;
b = res;
i = i + 1;
printf("%d\n",res);

}
}
void sommaFibonacci(int num, int *sum){
int i = 1,a = -1,b = 1;
while(i <= num){
res = a + b;
a = b;
b = res;
i = i + 1;
*sum = *sum + res;

}
}
void checkInput(int x, bool *flag){
if(x >= 0){
*flag = true;
}
else{
*flag = false;
}
}

int main(void){
printf("%s\n","Quanti numeri di Fibonacci vuoi vedere");
scanf("%d",&num);
checkInput(num,&flag);
if(flag == true){
printf("%s\n","Questa è la sequenza");
fibonacci(num);
printf("%s\n","La somma della sequenza è");
sommaFibonacci(num,&sum);
printf("%d\n",sum);
}
else{
printf("%s\n","Inserisci un intero positivo");
}
return 0;
}