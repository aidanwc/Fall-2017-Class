#include <stdlib.h>
#include <stdio.h>
#include "list.h"

int main(){
	int c;
	scanf("%d", &c);
	newList();  //creates a new list
	while(c>=0){
		addNode(c);
		scanf("%d", &c); //scans for a new number
	}
	prettyPrint();//Prints out list after it is created
}
