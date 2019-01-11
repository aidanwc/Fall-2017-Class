
#include <stdio.h>
#include <stdlib.h>

//declares function for later
int compareString(char[], char *);

//Helper function counts lenth of name in char array.
int countLength(char counted[]) {
    char *cur = counted;
    int count = 0;
    while (*cur != ',') { //ends when it reaches the comma
        count++;
        cur++;
    }
    //Returns value 
    return count;
}

//Helper function Counts length of a string which ends with \0
int countLengthString(char *name) {
    char *cur = name;
    int count = 0;
    while (*cur != '\0') { //gets count of word to replace
        count++;
        cur++;
    }
    //Returns int count
    return count;
}

//Find record function which returns the string from file that contains the name 
void FindRecord(char *filename,char *name, char record[]){
    //opens file and returns if nothing is opened 
    FILE *q= fopen(filename,"rt");
    if(q==NULL) return;
    //while not the end of file gets the current line and tests whether it contains the name
    while(!feof(q)){
        fgets(record,999,q);
        if(compareString(record,name)==1){//breaks with current record if it contains the name(uses helper function)
            break;
        }
    }
    fclose(q);
}

//Replace function, replaces name with new name and edits record 
void Replace(char *name, char *newname, char record[]) {
    
    //gets the length of the new name and the old one 
    int recordNameLength = countLength(record);
    int nameLength = countLengthString(newname);
    
    //creates a temporary copy of the second have of record
    char tempRest[1000];
    int saveMyCount=0;
    
    //Loops through array the second half of the record, increment is a pointer to the temp rest array.
    for (char *i = record + recordNameLength, *increment = tempRest; *i != '\n'; i++, increment++,saveMyCount++) {
        *increment = *i;
    }
    
    //Copies new name in to array
    char *limit = record+nameLength;
    for (char *i = record; i < limit; i++, newname++){
        *i = *newname;
    }
    
    //creates other limit for loop, if newname is shorter than old name makes longer loop
    char *otherLimit= record+nameLength+saveMyCount;
    if((saveMyCount+nameLength)<(saveMyCount+recordNameLength))otherLimit=(record+recordNameLength+saveMyCount);
    
    //copies second half of record to first half, fills in with nulls if newname is shorter than the old name
    for (char *i = record + nameLength, *toArray = tempRest; i<otherLimit; i++, toArray++){
        *i = *toArray;
        if(i>=record+nameLength+saveMyCount) *i='\0';
    }
    
    //inserts a new \n in the record that was taken away in first for loop
    char *insert=(record+nameLength+saveMyCount);//+1???
    *insert='\n';
}

//Helper function that compares two strings 
int compareString(char haystack[], char *needle) { //helper functionfor comparing strings
    char *haystackCounter = haystack;
    char *needleCounter = needle;
    //Goes through array and string if they do not match at any point returns a zero, otherwise returns a 1
    for (; *haystackCounter != ','; haystackCounter++, needleCounter++) {
        if (*needleCounter != *haystackCounter) return 0;
    }
    return 1;
}

//function to save the record 
void SaveRecord(char *filename, char *name, char record[]){
   
    //opens the file to read and returns if null
    FILE *read = fopen(filename, "r");//or filename
    char buffer[1000];
    if (read == NULL) return;
    
    //Goes through file and finds line to replace 
    int lineNumber=0;
    while (fgets(buffer, 999, read)!=NULL) {
        lineNumber++;
         //checks whether array contains the name and breaks loop if it does 
        if (compareString(buffer, name) == 1){
            break;
        }
    }
    
    //rewinds file and opens a replica file 
    rewind(read);
    FILE *write=fopen("replica.c","w");
    int counter=0;
    
    //while not the end of the file gets the current line 
    while(fgets(buffer,999,read)!=NULL){
        
        //if the current line is the one that contains the name, writes to file from record
        counter++;
        if(counter==lineNumber){
            fputs(record,write);  
        } else{
            fputs(buffer,write);
        }

    }
   
    //closes and renames files 
    fclose(read);
    fclose(write);
    remove(filename);
    rename("replica.c", filename);
}


int main() {
    
    //Creates two arrays to hold names, and scans in names
    char name[300], rname[300];
    printf("Please enter a name.\n");
    scanf("%s",name);
    printf("Please enter a replacment name\n");
    scanf("%s",rname);
    
    //creates an array to hold the record
    char workingArray[1000];
    
    //Finds name, replaces name, and then saves the new record
    FindRecord("phonebook.csv",name,workingArray);
    Replace(name,rname,workingArray);
    SaveRecord("phonebook.csv",name,workingArray);


}