
import java.util.ArrayList;

    public class HashLinkedList<K,V>{
        /*
         * Fields
         */
        private HashNode<K,V> head;

        private Integer size;

	/*
	 * Constructor
	 */

        HashLinkedList(){
            head = null;
            size = 0;
        }


	/*
	 *Add (Hash)node at the front of the linked list
	 */

        public void add(K key, V value){
            // ADD CODE BELOW HERE
            HashNode<K,V> newNode = new HashNode<>(key,value);//Creates new node and increases size
            size++;
            //Sets head of list
            if (head == null){
                head = newNode;
            }
            else{
                newNode.next=head;
                head= newNode;

            }

            // ADD CODE ABOVE HERE
        }

	/*
	 * Get Hash(node) by key
	 * returns the node with key
	 */

        public HashNode<K,V> getListNode(K key){
            // ADD CODE BELOW HERE
            HashNode<K,V> temp = head;
            //Loops through list looking for key, returns null otherwise
            for(int i=0; i<size;i++){
                if(temp.getKey().equals(key)){
                    return temp;
                }
                temp=temp.getNext();
            }
            return null;
            // ADD CODE ABOVE HERE
        }


	/*
	 * Remove the head node of the list
	 * Note: Used by remove method and next method of hash table Iterator
	 */

        public HashNode<K,V> removeFirst(){
            // ADD CODE BELOW HERE
            //Removes head node
            HashNode<K,V> temp = head;
            head=head.getNext();
            size--;
            return temp;
            // ADD CODE ABOVE HERE

        }

	/*
	 * Remove Node by key from linked list
	 */

        public HashNode<K,V> remove(K key){
            // ADD CODE BELOW HERE
            HashNode<K,V> temp = head;
            if (size == 1 && temp.getKey().equals(key)) { //size is one
                head= null;
                size--;
                return temp;
            }
            for(int i=0; i<size-1;i++){
                if(i==0&& temp.getKey().equals(key)){ //if node is the head
                    head = temp.getNext();
                    size--;
                    return temp;
                }
                HashNode<K,V> nodeToRemove = temp.getNext();
                if(i==size-2&&nodeToRemove.getKey().equals(key)){//otherwise if the key is the second last one
                    temp.next=null;
                    size--;
                    return nodeToRemove;
                }if (nodeToRemove.getKey().equals(key)){ //any other node
                    temp.next = nodeToRemove.getNext();
                    size--;
                    return nodeToRemove;
                }
                temp= temp.getNext();//gets next node
            }
            // ADD CODE ABOVE HERE
            return null;
        }



        /*
         * Delete the whole linked list
         */
        public void clear(){
            head = null;
            size = 0;
        }
	/*
	 * Check if the list is empty
	 */

        boolean isEmpty(){
            return size == 0? true:false;
        }

        int size(){
            return this.size;
        }

        //ADD YOUR HELPER  METHODS BELOW THIS

        //To string method for linked list.
        public String toString(){

                String test ="My List: ";
                HashNode<K,V> temp = head;

                for(int i=0; i<size; i++){
                    String s = "Key: "+ temp.getKey() + "Value: " + temp.getValue()+"\n";
                    test+= s;
                    temp=temp.getNext();

                }
                return test;
        }

        //Gets node at index of list
        public HashNode<K,V> getIndex(int index) {

            //Avoids index out of bounds
            if (index >= size) {
                System.out.println("Index out of bounds");
                return null;

            }

            HashNode<K, V> temp = head;
            for (int i = 0; i < size; i++) {
                if (i == index) {
                    return temp; //Returns node at index in list
                }
                temp = temp.getNext();

            }
            return null;
        }


        //ADD YOUR HELPER METHODS ABOVE THIS//
}


