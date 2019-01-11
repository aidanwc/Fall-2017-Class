#ifndef NODEH
#define NODEH

//Defines struct node
struct NODE {
	int data;
	struct NODE *next;
};

//Function prototypes 
void newList();
int addNode();
void prettyPrint();

#endif
