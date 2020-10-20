package com.github.robyndalgleish.cache;

import lombok.Value;

//@Value
public class Node {

    int key;
    int value;

    Node previous;
    Node next;

    Node(int key, int value){
        this.key=key;
        this.value=value;
    }
}
