

import java.util.*;

/*
 *  WordTree class.  Each node is associated with a prefix of some word
 *  stored in the tree.   (Any string is a prefix of itself.)
 */

public class WordTree
{
    WordTreeNode root;

    // Empty tree has just a root node.  All the children are null.

    public WordTree()
    {
        root = new WordTreeNode();
    }


    /*
     * Insert word into the tree.  First, find the longest
     * prefix of word that is already in the tree (use getPrefixNode() below).
     * Then, add TreeNode(s) such that the word is inserted
     */
    public void insert(String word)
    {
        

            WordTreeNode start = this.getPrefixNode(word); //Starts inserting at longest prefix, potentially the root
            int depth = start.depth; //Gets the depth of the root

            if (depth==word.length()){ //If word is already in just changes the end of word to true
                start.setEndOfWord(true);
            }

            for(int i= depth;i< word.length();i++){ //Otherwise adds node one by one and then sets end of word to true
                char curr = word.charAt(i);
                start= start.createChild(curr);
                if(i==word.length()-1){
                    start.setEndOfWord(true);
                }
            }


       
    }

    // insert each word in a given list

    public void loadWords(ArrayList<String> words)
    {
        for (int i = 0; i < words.size(); i++)
        {
            insert(words.get(i));
        }
        return;
    }

    /*
     * Given an input word, return the TreeNode corresponding the longest prefix that is found.
     * If no prefix is found, return the root.
     * Running getPrefixNode("any") should return the
     * dashed node under "n", since "an" is the longest prefix of "any" in the tree.
     */
   WordTreeNode getPrefixNode(String word)
    {
       WordTreeNode temp = this.root; //Starts at root and tries to go to next letter, if that letter is null returns root
       for(int i=0;i<word.length();i++){
           if(temp.getChild(word.charAt(i))==null) {
               return temp;
           }else {

               temp = temp.getChild(word.charAt(i));//Moves temp node to child
           }
       }
        return temp;
       

    }

	/*
	 * Similar to getPrefixNode() but now return the prefix as a String, rather than as a TreeNode.
	 */

    public String getPrefix(String word)
    {
        return getPrefixNode(word).toString();
    }


    /*
     *  Return true if word is contained in the tree (i.e. it was added by insert), false otherwise.
     */
    public boolean contains(String word)
    {

        String s  = getPrefix(word); //Converts prefix node to string
        WordTreeNode temp= getPrefixNode(word); //Gets node form

        if(word.equals(s)&& temp.isEndOfWord()){ //If the strings match and is end of word returns true
            return true;
        }
        return false;
    }

    /*
     *  Return a list of all words in the tree that have the given prefix.
     *  For example,  getListPrefixMatches("") should return all words in the tree.
     */
    public ArrayList<String> getListPrefixMatches( String prefix )
    {
        ArrayList<String> list = new ArrayList<String>(); //Creates a list
        WordTreeNode myRoot= getPrefixNode(prefix); //Gets the longest prefix node

        if(myRoot.equals(root)&& !prefix.equals("")){ //If the node returned is root and it is not searching for an empty string, returns an empty list
            return list;
        }
        recursive(myRoot,list);//Otherwise calls depth first post order method, FOUND AT BOTTOM OF CLASS
        return list;

    }


	/*
	 *  Below is the inner class defining a node in a Tree (prefix) tree.
	 *  A node contains an array of children: one for each possible character.
	 *  The children themselves are nodes.
	 *  The i-th slot in the array contains a reference to a child node which corresponds
	 *  to character  (char) i, namely the character with  ASCII (and UNICODE) code value i.
	 *  Similarly the index of character c is obtained by "casting":   (int) c.
	 *  So children[97] = children[ (int) 'a']  would reference a child node corresponding to 'a'
	 *  since (char)97 == 'a'   and  (int)'a' == 97.
	 */

    public class WordTreeNode
    {
		/*
		 *   Highest allowable character index is NUMCHILDREN-1
		 *   (assuming one-byte ASCII i.e. "extended ASCII")
		 *
		 *   For simplicity,  we have given each WordTree node 256 children.
		 *   Note that if our words only consisted of characters from {a,...,z,A,...,Z} then
		 *   we would only need 52 children.   The WordTree can represent more general words
		 *   e.g.  it could also represent many special characters often used in passwords.
		 */

        public static final int NUMCHILDREN = 256;

        WordTreeNode     parent;
        WordTreeNode[]   children;
        int              depth;            // 0 for root, 1 for root's children, 2 for their children, etc..

        char             charInParent;    // Character associated with the tree edge from this node's parent
        // to this node.
        // See comment above for relationship between an index in 0 to 255 and a char value.

        boolean endOfWord;   // Set to true if prefix associated with this node is also a word.


        // Constructor for new, empty node with NUMCHILDREN children.
        //  All the children are automatically initialized to null.

        public WordTreeNode()
        {
            children = new WordTreeNode[NUMCHILDREN];

            //   These assignments below are unnecessary since they are just the default values.

            endOfWord = false;
            depth = 0;
            charInParent = (char)0;
        }


		/*
		 *  Add a child to current node.  The child is associated with the character specified by
		 *  the method parameter.  Make sure you set as many fields in the child node as you can.
		 *
		 *  To implement this method, see the comment above the inner class TreeNode declaration.
		 *
		 */

        public WordTreeNode createChild(char  c) 
        {
            WordTreeNode child       = new WordTreeNode();

                child.parent = this; //Sets parent
                child.charInParent=c;
                child.depth= this.depth + 1; //Increases depth of child
                this.children[c]=child; //Sets parents child to this node

            return child;
        }

        // Get the child node associated with a given character, i.e. that character is "on"
        // the edge from this node to the child.  The child could be null.

        public WordTreeNode getChild(char c)
        {
            return children[ c ];
        }

        // Test whether the path from the root to this node is a word in the tree.
        // Return true if it is, false if it is prefix but not a word.

        public boolean isEndOfWord()
        {
            return endOfWord;
        }

        // Set to true for the node associated with the last character of an input word

        public void setEndOfWord(boolean endOfWord)
        {
            this.endOfWord = endOfWord;
        }

		/*
		 *  Return the prefix (as a String) associated with this node.  This prefix
		 *  is defined by descending from the root to this node. 
		 *  This overrides the default toString() method.
		 */

        public String toString()//may be formated incorectly to call
        {

           if(this.charInParent == (char)0) { //If root returns empty String
               return "";
           }else{
               return this.parent.toString()+ this.charInParent; //Otherwise recursively adds letters to string
           }
        }

    }

    //Recursive Helper method for getPrefixMatches
    public void recursive(WordTreeNode root, ArrayList<String> list){
        int counter=0;
        for(int i=0;i<256;i++){//Tests whether current node is a leaf
            if (root.children[i]!= null){
                counter++;
            }
        }
        if(counter==0){ //is a leaf
            list.add(root.toString()); //if current node is a leaf it must be a word
            return;
        }else {
            for(int i=0; i<256;i++){
                if(root.children[i]!=null){
                    recursive(root.children[i],list); //Calls recursive method on non null childern
                }
            }
            if(root.isEndOfWord()==true){
                list.add(root.toString()); //If current node is a word adds it to the list
            }
            return;

        }
    }

}
