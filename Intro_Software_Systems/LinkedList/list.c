#include <stdlib.h>
#include <stdio.h>
#include "list.h"

static struct NODE *head; //global variable

void newList(){   //creates new list
	head=NULL;
}

int addNode(int value){
	struct NODE *temp = (struct NODE*) malloc(sizeof(struct NODE));
	if (temp==NULL) return 0;

	//Initiates fields of nodes 
	temp->data=value;
	temp->next=head;
	head=temp;
	
	return 1;//returns success
}

void prettyPrint(){
	struct NODE *temp =head;
	while(temp!=NULL){
		printf("%d ", temp->data);//Prints number in node
		temp=temp->next;//Sets current node to next node in list
	}
	printf("\n");
}
