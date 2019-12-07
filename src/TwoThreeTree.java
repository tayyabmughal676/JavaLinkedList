import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

class TwoThreeTree<T extends Comparable<T>> implements Iterable {
    private Node root;
    private int size;
    private boolean modified = true;
    private Traversal traverse;


    public static void main(String[] args) {
        TwoThreeTree<Integer> twoThreeTree = new TwoThreeTree<>();

        twoThreeTree.insert(1);
        twoThreeTree.insert(225);
        twoThreeTree.insert(555);
        twoThreeTree.insert(445);
        twoThreeTree.insert(66);
        twoThreeTree.insert(45);
        twoThreeTree.insert(33);
        twoThreeTree.insert(453);
        twoThreeTree.insert(14);

//        List strList = new ArrayList<>();
//
//        //add at last
//        strList.add(0, "0");
//
//        //add at specified index
//        strList.add(1, "1");
//
//        //replace
//        strList.set(1, "2");
//
//        //remove
//        strList.remove("1");
//
//        twoThreeTree.addAll(strList);

    }


    TwoThreeTree() {
        root = null;
        traverse = new Traversal();
        size = 0;
    }

    private void addAll(Collection<T> collection) {
        for (T item : collection)
            this.insert(item);
        modified = true;
    }

    void insert(T x) {
        if (root == null) {                           // If root is null, then create a new root with null as the parent{
            root = new Node(x);
            size++;
        }
        root.insert(x);                             // Otherwise induce a recursive insert.
        modified = true;
    }

    int size() {
        return size;
    }

    boolean search(T x) {
        if (root == null)                           // If the root is null, then tree doesn't exist -> return null
            return false;
        return root.search(x).keys.contains(x);
    }

    void clear() {
        root = null;
        size = 0;
    }

    public Iterator<T> iterator() {
        if (modified) {                           // Build in-order Linked-List only if the tree was modified.{
            traverse.traverseTree();            // Traverse the tree
            modified = false;                   // Set flag to false since we just built a linked-list.
        }
        return traverse.ordered.iterator();     // Delegate the task of iterator to Linked-List's in built iterator.
    }

    //-----------------------------------------Inner Iterator Class-------------------------------------------------//

    private class Traversal {
        ArrayList<T> ordered;

        void traverseTree() {
            ordered = new ArrayList<>();                   // Reset the ordered list. traverseTree will be only called in case of modification
            traverse(root);                                 // Initialize traversal from the root.
        }

        void traverse(Node n) {
            if (n.children.size() == 0)                     // If it's a leaf node, add all its key to the linked list
                ordered.addAll(n.keys);
            else {
                traverse(n.children.get(0));                // Otherwise, first traverse the left branch
                ordered.add(n.keys.get(0));                 // When it is done, create add key 1 of the node n to keep it ordered.
                traverse(n.children.get(1));                // Then traverse the middle/right branch of n.
                if (n.children.size() == 3) {              // If there are 3 branches, then we still need to traverse it.
                    ordered.add(n.keys.get(1));             // Before we traverse, add the 2nd key of n to the list since everything is going to be greater than it in the right branch.
                    traverse(n.children.get(2));            // Then traverse the last branch and add all encountered nodes in order.
                }
            }
        }
    }

    //-------------------------------------------INNER CLASS NODE---------------------------------------------------//
    private class Node {
        ArrayList<T> keys = new ArrayList<>(3);
        ArrayList<Node> children = new ArrayList<>(4);

        Node(T data) {
            keys.add(data);
        }

        private void addKey(T x) {
            this.keys.add(x);                                 // Insert x at the last position
            int lastIndex = this.keys.size() - 1;
            for (int i = 0; i < lastIndex; i++) {             // Repeatedly check the key at index i if its greater than key at index keySize{
                if (this.keys.get(i).compareTo(this.keys.get(lastIndex)) > 0) {
                    T temp = this.keys.get(i);                // If it is, swap them and increase the i
                    this.keys.set(i, this.keys.get(lastIndex));
                    this.keys.set(lastIndex, temp);
                }
            }
        }

        private boolean insert(T x) {
            if (this.keys.contains(x))                                               // If the node contains key, return false
                return false;

            int i = 0;
            while (i < this.keys.size() && x.compareTo(this.keys.get(i)) > 0)        // Find the correct child to insert at.
                i++;
            boolean childWasSplit;

            if (i < this.children.size())                               // The node is not a leaf, so I can recursively insert.
                childWasSplit = this.children.get(i).insert(x);
            else {                                                     // Its a leaf, just add the key and then check if it needs to split.{
                this.addKey(x);
                size++;                                                 // Key wasn't a duplicate a could be inserted in the tree, therefore increase the size.
                if (this.keys.size() == 3) {
                    this.splitify();                                    // Split the node and return true to let the parent know
                    return true;                                        // that child was split.
                }
                return false;
            }

            if (childWasSplit) {                                                           // Child was split and parent might be a 2-node or 3-node.
                Node tempChild = this.children.get(i);                  // Copy of the split child because it will get modified by overwriting parent's children
                this.addKey(tempChild.keys.get(0));                     // "Moving the child's key to the parent"
                this.children.set(i, tempChild.children.get(0));        // The current child is replaced by its left child
                this.children.add(i + 1, tempChild.children.get(1));// Add the right child to the next index. ArrayList does the shifting automatically.
                if (this.children.size() == 4) {                          // The node is really full, it was 3-node and now it became 4-node, so it{
                    this.splitify();                                    // needs to redistribute its children by creating nodes out of itself.
                    return true;
                }
            }                                                           // It was a 2-node so the split child's children became parent's children and
            return false;                                               // and it's key adjusted inside the parent so it became a 3-node.
        }

        private void splitify() {
            Node left = new Node(this.keys.get(0));
            Node right = new Node(this.keys.get(2));
            if (this.children.size() == 4)                      // If the node is overfull, balance everything
            {                                                   // by giving left-most two nodes to the left child
                left.children.add(this.children.get(0));        // and the right most two nodes to the right child.
                left.children.add(this.children.get(1));
                right.children.add(this.children.get(2));
                right.children.add(this.children.get(3));
            }
            this.children.clear();
            this.children.add(left);                            // Set the new left and right child of "this"
            this.children.add(right);

            T tempKey = this.keys.get(1);                       // Also, the keys have been passed to the children
            this.keys.clear();                                  // so modify it. In the end, only the middle key should be
            this.keys.add(tempKey);                             // there.
        }

        private Node search(T val) {
            if (this.children.size() == 0 || this.keys.contains(val))                     // If the node is a leaf or has the key, return that node
                return this;
            else {
                int i = 0;
                while (i < this.keys.size() && val.compareTo(this.keys.get(i)) > 0)       // Else recursively search that appropriate branch by making use
                    i++;                                                                  // of the index i.
                return this.children.get(i).search(val);
            }
        }

        public String toString()  {          // toString method
            return this.keys.get(0) + (this.keys.size() == 2 ? " " + this.keys.get(1) : "");
        }
    }
}