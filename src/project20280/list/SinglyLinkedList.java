package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {

    private static class Node<E> {

        private final E element;            // reference to the element stored at this node

        /**
         * A reference to the subsequent node in the list
         */
        private Node<E> next;         // reference to the subsequent node in the list

        /**
         * Creates a node with the given element and next node.
         *
         * @param e the element to be stored
         * @param n reference to a node that should follow the new node
         */
        public Node(E e, Node<E> n) {
            // TODO
            element = e;
            next = n;
        }

        // Accessor methods

        /**
         * Returns the element stored at the node.
         *
         * @return the element stored at the node
         */
        public E getElement() {
            return element;
        }

        /**
         * Returns the node that follows this one (or null if no such node).
         *
         * @return the following node
         */
        public Node<E> getNext() {
            // TODO
            return next;
        }

        // Modifier methods

        /**
         * Sets the node's next reference to point to Node n.
         *
         * @param n the node that should follow this one
         */
        public void setNext(Node<E> n) {
            // TODO
            next = n;
        }
    } //----------- end of nested Node class -----------

    /**
     * The head node of the list
     */
    private Node<E> head = null;               // head node of the list (or null if empty)


    /**
     * Number of nodes in the list
     */
    private int size = 0;                      // number of nodes in the list

    public SinglyLinkedList() {
    }              // constructs an initially empty list

    //@Override
    public int size() {
        // TODO
        //return how many elements are in the list
        return size;
    }

    //@Override
    //check if the list has zero elements
    public boolean isEmpty() {
        // TODO
        return size == 0;
    }

    @Override
    //returns the element stored at the index position in the list
    public E get(int position) {
        // TODO: start at head, move forward position times, return with the data in that node
        Node<E> current = head;

        if(position < 0 || position >=size){
            throw new IndexOutOfBoundsException();
        }

        for (int i = 0; i < position; i++){
            current = current.getNext();
        }
        return current.getElement();
    }

    @Override
    public void add(int position, E e) {
        // TODO:    insert a new element in front of the list
        if (position < 0 || position > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + position);
        }

        if (position == 0) {
            addFirst(e);
            return;
        }

        Node<E> prev = head;
        for (int i = 0; i < position - 1; i++) {
            prev = prev.getNext();
        }

        Node<E> newest = new Node<>(e, prev.getNext());
        prev.setNext(newest);

        size++;
    }


    @Override
    public void addFirst(E e) {
        // TODO
        //new node becomes the head
        head = new Node<E>(e, head); //create and link a new node
        size++;
    }

    @Override
    public void addLast(E e) {
        // TODO
        Node<E> newest = new Node<E>(e, null); //node will eventually be the tail
        Node<E> last = head;
        if(last == null) { //if the list is empty
            head = newest; //the new node becomes the head
        }
        //otherwise walk until current.next == null, and attach new node ther
        else {
            while (last.getNext() != null) {
                last = last.getNext();
            }
            last.setNext(newest);
        }
        size++;
    }

    @Override
    public E remove(int position) {
        //removeposition: remove the node at index position and return its element

        //invalid position
        if(position < 0 || position >= size){
            //throw error
            throw new IndexOutOfBoundsException("Invalid index: " + position);
        }

        //removing the first node
        if(position == 0){
            removeFirst();
        }

        //if position > 0
        // traverse to the node BEFORE the one we remove
        Node<E> prev = head;
        for (int i = 0; i < position - 1; i++) {
            prev = prev.getNext();
        }

        // target is the node we want to remove
        Node<E> target = prev.getNext();

        // save element to return
        E answer = target.getElement();

        // unlink target: prev -> target.next
        prev.setNext(target.getNext());

        // decrease size
        size--;

        return answer;
    }

    @Override
    public E removeFirst() {
        // TODO
        //edge case: if the list is empty
        if(isEmpty()){
            return null;
        }
        //save old head's element
        E answer = head.getElement();

        //move head to head.next
        head = head.getNext();

        //decrease size
        size--;

        //return saved element

        return answer;
    }

    @Override
    //remove last = remove the last node in the list and return its element
    public E removeLast() {
        // TODO
        //handle edge cases
        if(isEmpty()){
            return null;
        }

        //only node
        if(size == 1){
            E answer = head.getElement();
            head = null;
            size = 0;
            return answer;
        }
        //traverse until current.next.next == null;
        Node<E> current = head;
        while (current.getNext().getNext() != null){
            current = current.getNext();
        }
        //save the last element
        E answer = current .getNext().getElement();

        //set current.nect = null
        current.setNext(null);

        //decrease size
        size--;

        return answer;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();

        ll.addFirst(0);
        ll.addFirst(1);
        ll.addFirst(2);
        ll.addFirst(3);
        ll.addFirst(4);
        ll.addLast(-1);
        ll.removeLast();
        ll.removeFirst();
        System.out.println("I accept your apology");
        ll.add(3, 2);
        System.out.println(ll);
        ll.remove(5);
        System.out.println(ll);

    }
}
