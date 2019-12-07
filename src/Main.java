
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


//        TwoThreeTree<Integer> twoThreeTree = new TwoThreeTree<Integer>();
//
//        twoThreeTree.insert(2);
//        twoThreeTree.size();



//        myLinkedList();
//        myLinkedListWithLoopArray();

//        /* Start with the empty list. */
//        LinkedList list = new LinkedList();
//
//        //
//        // ******INSERTION******
//        //
//
//        // Insert the values
//        LinkedList.insert(list, 1);
//
//        list = insert(list, 2);
//        list = insert(list, 3);
//        list = insert(list, 4);
//        list = insert(list, 5);
//        list = insert(list, 6);
//        list = insert(list, 7);
//        list = insert(list, 8);
//
//        // Print the LinkedList
//        printList(list);
//
//        //
//        // ******DELETION BY KEY******
//        //
//
//        // Delete node with value 1
//        // In this case the key is ***at head***
//        deleteByKey(list, 1);
//
//        // Print the LinkedList
//        printList(list);
//
//        // Delete node with value 4
//        // In this case the key is present ***in the middle***
//        deleteByKey(list, 4);
//
//        // Print the LinkedList
//        printList(list);
//
//        // Delete node with value 10
//        // In this case the key is ***not present***
//        deleteByKey(list, 10);
//
//        // Print the LinkedList
//        printList(list);
//
//        //
//        // ******DELETION AT POSITION******
//        //
//
//        // Delete node at position 0
//        // In this case the key is ***at head***
//        deleteAtPosition(list, 0);
//
//        // Print the LinkedList
//        printList(list);
//
//        // Delete node at position 2
//        // In this case the key is present ***in the middle***
//        deleteAtPosition(list, 2);
//
//        // Print the LinkedList
//        printList(list);
//
//        // Delete node at position 10
//        // In this case the key is ***not present***
//        LinkedList.deleteAtPosition(list, 10);
//
//        // Print the LinkedList
//        printList(list);




    }

    private static void myLinkedListWithLoopArray() {

        LinkedList<Integer> linkedList = new LinkedList<>();
        int n ;
        System.out.println("Enter how many items to insert into Linked List");
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();

        Scanner scanner1 = new Scanner(System.in);
        for (int i = 0; i <n ; i++) {
            System.out.println("Enter Items : " + i);
            int item = scanner1.nextInt();
            linkedList.add(item);
        }

        System.out.println("List" + linkedList);


    }

    private static void myLinkedList() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(1);
        linkedList.add(12);
        linkedList.add(123);
        linkedList.add(1234);
        linkedList.add(12345);
        linkedList.addLast(54321);
        linkedList.addFirst(0);
        linkedList.add(1, 21);
        Response("Linked List values:        " , linkedList);

        linkedList.remove(4);
        Response("Linked List Removes values:" , linkedList);

        linkedList.removeFirst();
        linkedList.removeLast();
        Response("Linked List First & Last values:", linkedList);

    }


    private static void Response(String ms, LinkedList<Integer> linkedList){
        System.out.println(ms + linkedList);
    }

    private void myLinkList(){
        // create a linked list
        LinkedList<String> linkedList = new LinkedList<>();

        // add elements to the linked list
        linkedList.add("F");
        linkedList.add("B");
        linkedList.add("D");
        linkedList.add("E");
        linkedList.add("C");

        linkedList.addLast("Z");
        linkedList.addFirst("A1");

        linkedList.add(1, "A2");

        System.out.println("Original contents of linkedList: " + linkedList);

        // remove elements from the linked list
        linkedList.remove("F");
        linkedList.remove(2);

        System.out.println("Contents of ll after deletion: " + linkedList);

        // remove first and last elements
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println("ll after deleting first and last: " + linkedList);

        // get and set a value
        String val = linkedList.get(2);

        linkedList.set(2, val + " Changed");

        System.out.println("ll after change: " + linkedList);
    }
}

