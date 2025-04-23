public class BST<T> {
    private BSTNode<T> root, current;

    public BST() {
        root = current = null;
    }
    

    public boolean empty() {
        return root == null;
    }
    
    
    public boolean full() {
       return false;}
       
    public T retrieve () {
      return current.data;
    }

     public boolean findKey(String k) {
        BSTNode<T> p = root, q = root;
        if (empty()) return false;

        while (p != null) {
            q = p;
           // compare classes
            int cmp = k.compareTo(p.key);
            
            if (cmp == 0) {
                current = p;
                return true;
            } else if (cmp < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        current = q;
        return false;
    }
    
    
    public boolean insert(String k, T val) {
    BSTNode<T> p, q = current;
    if (findKey(k)) {
        current = q;  
        return false; 
    }

    p = new BSTNode<T>(k, val);
    if (empty()) {
        root = current = p;
        return true;
    } else {
        int cmp = k.compareTo(current.key); 
        if (cmp < 0) 
            current.left = p;
        else 
            current.right = p;
        
        current = p;
    
        return true;
  }}
  
  
  
  public boolean removeKey(String key) {
    BSTNode<T> p = root;
    BSTNode<T> q = null; 
    boolean found = false;

    while ((p != null) && (!found)) {
        int res = key.compareTo(p.key);
        if (res < 0) {
            q = p;
            p = p.left;
        } else if (res > 0) {
            q = p;
            p = p.right;
        } else { 
            found = true;
        }
    }

    if (found) {
          if ((p.left != null) && (p.right != null)) { 
            BSTNode<T> min = p.right;
            q = p;
            while (min.left != null) {
                q = min;
                min = min.left;
            }
            p.key = min.key;
            p.data = min.data;
            deleteNode(min, q);
        } else { 
            deleteNode(p, q);
        }

        current = root;
        return true;
    }

    return false; }
    
    
private void deleteNode(BSTNode<T> n, BSTNode<T> parent) {
        if (n == null) return;

        BSTNode<T> child;
        if (n.left != null) {
            child = n.left;
        } else {
            child = n.right;
        }

        if (parent == null) {
            root = child;
        } else {
            if (n.key.compareTo(parent.key) < 0) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
    }

    public void inOrder(){//just for testing

        if(root == null)
            System.out.println("Empty tree");
        else
            inOrder((BSTNode<LinkedList<Photo>>) root);

    }
    private void inOrder(BSTNode<LinkedList<Photo>> p){
        if(p == null)
            return;
        inOrder(p.left);
        System.out.println("key = " +p.key);
        PrintAllPhotos(p.data);
        inOrder(p.right);

    }

    public void PrintAllPhotos(LinkedList<Photo> photos) {//just for testing

        if (!photos.empty()) {

            photos.findFirst();
            int num = 1;
            while (!photos.last()) {
                System.out.println(num+") Path: " + photos.retrieve().getPath());
                photos.findNext();
                num++;
            }//end while
            System.out.println(num+") Path: " + photos.retrieve().getPath());
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            num = 1;
        }//end if
        else
            System.out.println("\nEmpty List\n");
    }

// ########################################################################################################
    public BSTNode<T> getRoot() {
        return root;
    }

}//end class
