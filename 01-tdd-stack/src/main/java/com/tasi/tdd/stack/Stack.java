
package com.tasi.tdd.stack;

import java.util.EmptyStackException;

public class Stack {

    /** Where stacked elements will be. */
    private Object[] elements;
    /** Amount of items and current top index (amount - 1). */
    private int amount = 0;

    public Stack(int max) {
        this.elements = new Object[max];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return amount;
    }

    public void stack(Object element) {
        if(amount == this.elements.length){
            throw new FullStackException();
        }
        this.elements[amount] = element;
        this.amount++;
    }

    /**
     * Returns last value inserted. Doesn't remove.
     */
    public Object top() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[amount - 1];
    }

    /**
     * Returns last value inserted and remove it.
     */
    public Object unstack() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        Object result = top();
        amount--;
        return result;
    }
}
