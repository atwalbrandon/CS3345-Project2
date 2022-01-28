/**The class which controls how the lazy binary tree behaves.
 *
 * @author Brandon Atwal
 */
public class LazyBinarySearchTree {
    TreeNode root;
    
    /**create a new, empty, binary tree
     */
    LazyBinarySearchTree(){
        root = null;
    }
    
    /**Inserts a node into the tree. If node exists but already deleted, undeletes it. 
     * If already exists and isn't deleted, do nothing.
     * @param key The integer to be inserted
     * @throws IllegalArgumentException thrown if key not within valid range [1,99]
     * @return Boolean denoting whether it logically (opposed to physically) inserted a new element.
     */
    public boolean insert(int key){
        if (key < 1 || key > 99) throw new IllegalArgumentException("Key not within valid range: [1,99]");
        if (root == null){
            root = new TreeNode(key);
            return true;
        }else{
            boolean done = false;
            TreeNode current = root;
            while (done != true){
                if (key == current.getKey()){
                    if (current.isDeleted() == true){
                        current.undelete(); //if the key already exists but is deleted
                        return true;
                    }
                    else if (current.isDeleted() == false){
                        done = true; //if the key already exists and isnt deleted, do nothing and return false
                    }
                }
                else if (key < current.getKey()){ //if smaller and at the end, insert
                    if (current.getLeftChild() == null){
                        current.setLeftChild(new TreeNode(key));
                        return true;
                    }else{                       //otherwise keep searching
                        current = current.getLeftChild();
                    }
                }
                else if (key > current.getKey()){ //if larger and at the end, insert
                    if (current.getRightChild() == null){
                        current.setRightChild(new TreeNode(key));
                        return true;
                    }else{                          //otherwise keep searching
                        current = current.getRightChild();
                    }
                }
            }
        }
        return false;
    }
    
    /**Deletes a node on the tree by marking it as deleted
     * @param key The integer to be deleted
     * @throws IllegalArgumentException thrown if key not within valid range [1,99]
     * @return Boolean denoting whether it deleted element.
     */
    public boolean delete(int key){
        if (key < 1 || key > 99) throw new IllegalArgumentException("Key not within valid range: [1,99]");
        if (root == null) return false;
        
        boolean done = false;
        TreeNode current = root;
        while (done != true){
            if (key == current.getKey()){
                if (current.isDeleted() == true){
                    return false; //already deleted, so do nothing
                }
                else if (current.isDeleted() == false){ //if exists and isnt deleted
                    current.delete();                   //delete
                    return true;
                }
            }
            else if (key < current.getKey()){
                if (current.getLeftChild() == null){
                    return false; //key doesn't exist
                }else{
                    current = current.getLeftChild();
                }
            }
            else if (key > current.getKey()){
                if (current.getRightChild() == null){
                    return false; //key doesn't exist
                }else{
                    current = current.getRightChild();
                }
            }
        }
        
        return false;
    }
    
    /**Finds the lowest non-deleted element in the tree.
     * @return integer containing the lowest value, or -1 if a non-deleted minimum doesn't exist
     */
    public int findMin(){
        int min = findMinHelper(root);
        if (min == Integer.MAX_VALUE) return -1; //if a non-deleted minimum doesnt exist, return -1
        return min;
    }
    
    //helper method for findMin(). Uses recursion to search for the lowest value.
    // Returns Integer.MAX_VALUE if no lowest non-deleted integer is found, since using -1 would mess up the logic.
    private int findMinHelper(TreeNode n){
        if (n == null) return Integer.MAX_VALUE; //empty children don't exist
        
        int key;
        if (n.isDeleted()) key = Integer.MAX_VALUE; //if it's deleted, don't include
        else key = n.getKey();       //otherwise get its value
        
        //get largest value of each subtree
        int lkey = findMinHelper(n.getLeftChild());
        int rkey = findMinHelper(n.getRightChild());
        
        //if either subtree is smaller than this node, make that value it
        if (lkey < key)
            key = lkey;
        if (rkey < key)
            key = rkey;
        
        return key; //return smallest value
    }
    
    /**Finds the highest non-deleted element in the tree.
     * @return integer containing the highest value, or -1 if a non-deleted maximum doesn't exist
     */
    public int findMax(){
        //-1 is alrady in helper method since it is already smaller than
        //range [1-99], so it doesn't need Integer.MIN_VALUE
        return findMaxHelper(root);
    }
    
    //helper method for findMax(). Uses recursion to search for the highest value.
    private int findMaxHelper(TreeNode n){
        if (n == null) return -1; //empty children don't exist
        
        int key;
        if (n.isDeleted()) key = -1; //if it's deleted, don't include
        else key = n.getKey();       //otherwise get its value
        
        //get largest value of each subtree
        int lkey = findMaxHelper(n.getLeftChild());
        int rkey = findMaxHelper(n.getRightChild());
        
        //if either subtree is smaller than this node, make that value it
        if (lkey > key)
            key = lkey;
        if (rkey > key)
            key = rkey;
        
        return key; //return smallest value
    }
    
    /**Determines whether the tree contains a given integer.
     * @param key The requested integer
     * @throws IllegalArgumentException thrown if key not within valid range [1,99]
     * @return Boolean denoting whether the value is in the tree
     */
    public boolean contains(int key){
        if (key < 1 || key > 99) throw new IllegalArgumentException("Key not within valid range: [1,99]");
        if (root == null) return false;
        
        boolean done = false;
        TreeNode current = root;
        while (done != true){
            if (key == current.getKey()){
                if (current.isDeleted() == true){
                    return false; //already deleted, so do nothing
                }
                else if (current.isDeleted() == false){ //if exists and isnt deleted
                    return true;                        //then it contains it
                }
            }
            else if (key < current.getKey()){
                if (current.getLeftChild() == null){
                    return false; //key doesn't exist
                }else{
                    current = current.getLeftChild();
                }
            }
            else if (key > current.getKey()){
                if (current.getRightChild() == null){
                    return false; //key doesn't exist
                }else{
                    current = current.getRightChild();
                }
            }
        }
        
        return false;
    }
    
    /** Gets the value of each elements of the tree, including ones marked as deleted. Ordered via pre-order transversal
     *  Elements marked as deleted are preceded by a single asterisk. 
     * @return String of values in tree, with deleted elements denoted via asterisk before it
     */
    public String toString(){
        return toStringHelper(root);
    }
    
    /*Helper method for toString(). Uses recursion to order via pre-order transversal
    and return the string. Also adds the asterisk to deleted numbers*/
    private String toStringHelper(TreeNode n){
        if (n == null) return "";
        
        String s = "";
        if (n.isDeleted()) s = "*"; //add * to indicate if is deleted
                
        s = s + n.getKey() + " ";  //add value to string
        
        //do for every node, starting with left first (preorder)
        s = s + toStringHelper(n.getLeftChild());
        s = s + toStringHelper(n.getRightChild());
        
        return s;
    }
    
    /**Finds the height of the tree, including deleted elements
     * @return integer containing the height of the tree
     */
    public int height(){
        return heightHelper(root);
    }
    
    /*Helper method for height(). Uses recursion to find the height of the tree
     by repeatedly taking the height of each subtree of each node and adding 1*/
    private int heightHelper(TreeNode n){
        if (n == null) return 0;
        
        //find height of each subtree
        int left = heightHelper(n.getLeftChild());
        int right = heightHelper(n.getRightChild());
        
        //return the larger height value
        if (left > right)
            return (left + 1);
        else
            return (right + 1);
    }
    
    /**Finds how many elements are in the tree, including deleted elements.
     * @return integer containing size of the tree 
     */
    public int size(){
        return sizeHelper(root);
    }
    
    /*Helper method for size(). Uses recursion to find the size of the tree
      by counting the nodes recursively.*/
    private int sizeHelper(TreeNode n){
        if (n == null) return 0;
        //add up the number of subtrees via recursion
        return 1 + sizeHelper(n.getLeftChild()) + sizeHelper(n.getRightChild());
    }
    
    //nested class TreeNode
    class TreeNode{
        int key;
        TreeNode leftChild, rightChild;
        boolean deleted;

        TreeNode (int k){
            key = k;
            leftChild = null; rightChild = null;
            deleted = false;
        }

        //getter methods
        int getKey(){return key;}
        TreeNode getLeftChild(){return leftChild;}
        TreeNode getRightChild(){return rightChild;}
        boolean isDeleted(){return deleted;}

        //setter methods
        void setKey(int k){key = k;}
        void setLeftChild(TreeNode c){leftChild = c;}
        void setRightChild(TreeNode c){rightChild = c;}
        void delete(){deleted = true;}
        void undelete(){deleted = false;}
    }
}

