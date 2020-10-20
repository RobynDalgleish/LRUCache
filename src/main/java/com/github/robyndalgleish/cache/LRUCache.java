package com.github.robyndalgleish.cache;

import java.util.HashMap;

public class LRUCache {

    // We need to access a particular node at any place in the list, but we only need to keep track of the newest and oldest. So, field them fuckers.
    Node newest;
    Node oldest;

    // This thing is essentially a map, so like, map.
    HashMap<Integer, Node> map;

    // The capacity stored from dah constructor comment which is next, just read that...
    int capacity;

    // The only thing we really need to define before implementing this fucker, is the capacity. Sew... pass that shit in da constructor.
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }

    // We essentially want a get method and a put method, so let's do that:

    // Put sumfin' in the cache:
    public void put(int key, int value) {
        // If the map has this key, just update the map
        if(map.containsKey(key)){

            Node node = map.get(key);
            node.value = value;

            // LinkedList reordering to put the node at the newest position
            pullNode(node);
            pushNode(node);

        //delete oldest node in map if we are above capacity with this new addition
        }else{
            if(map.size()>=capacity){
                map.remove(oldest.key);
                pullNode(oldest);
            }

            //add new node to map
            Node node = new Node(key, value);
            pushNode(node);
            map.put(key, node);
        }
    }

    // Get that cached value:
    public int get(int key) {
        if(map.get(key)==null){
            return -1;
        }

        Node node = map.get(key);
        // Reorder LinkedList to move gotten Node to newest
        pushNode(node);
        pushNode(node);

        return node.value;
    }

    void pullNode(Node node) {
        // if a node previous to the one we are updating exists
        // update previous node
        if(node.previous != null){
            // then the previous node now points to the node after the one we are updating
            node.previous.next = node.next;
        } else {
            // else the new newest is the one after the node we are updating
            oldest = node.next;
        }

        // if the node next to the node we are updating exists (gotta set it right too)
        if(node.next != null){
            node.next.previous = node.next;
        } else {
            newest = node.previous;
        }
    }

    void pushNode(Node node) {
        if(newest!=null){
            newest.next = node;
        }

        node.previous = newest;
        node.next = null;
        newest = node;

        if(oldest == null){
            oldest = newest;
        }
    }
}
