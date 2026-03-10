package module_2.src.ss10_arraylist_linkedlist.thuc_hanh;

import java.util.Arrays;

public class MyListTest {
    public static void main(String[] args) {
//        MyList<Integer> listInteger = new MyArrayList<Integer>();
//        listInteger.add(1);
//        listInteger.add(2);
//        listInteger.add(3);
//        listInteger.add(4);
//
//        boolean isExit = listInteger.containsAll(Arrays.asList(1, 2));
//        Integer[] result = listInteger.toArray(new Integer[]{});
//
//        System.out.println(Arrays.toString(result));
//
//        System.out.println(isExit);
//
//        System.out.println("element 4: " + listInteger.get(4));
//        System.out.println("element 1: " + listInteger.get(1));
//        System.out.println("element 2: " + listInteger.get(2));

//        listInteger.get(-1);
//        System.out.println("element -1: " + listInteger.get(-1));

        System.out.println("/=/=/=/= TESTING /=/=/=/=");
        MyLinkedList ll = new MyLinkedList(10);
        ll.addFirst(11);
        ll.addFirst(12);
        ll.addFirst(13);

        ll.add(4, 9);
        ll.add(4, 9);
        ll.remove(9);
        ll.clear();
        ll.printList();
    }
}
