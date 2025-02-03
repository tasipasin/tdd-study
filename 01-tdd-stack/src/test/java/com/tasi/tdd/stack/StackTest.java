
package com.tasi.tdd.stack;

import java.util.EmptyStackException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackTest {

    Stack stack;

    @Before
    public void initStack() {
        this.stack = new Stack(10);
    }

    /**
     * Simplest case: empty stack.
     */
    @Test
    public void emptyStack() {
        Assert.assertTrue(this.stack.isEmpty());
        Assert.assertEquals(0, this.stack.size());
    }

    /**
     * Stack must have one item
     */
    @Test
    public void stackOneElement() {
        this.stack.stack("first");
        Assert.assertFalse(this.stack.isEmpty());
        Assert.assertEquals(1, this.stack.size());
        Assert.assertEquals("first", this.stack.top());
    }

    @Test
    public void stackTwoElementsUnstackOne() {
        // Stacks first two elements
        this.stack.stack("first");
        this.stack.stack("second");
        Assert.assertFalse(this.stack.isEmpty());
        Assert.assertEquals(2, this.stack.size());
        Assert.assertEquals("second", this.stack.top());
        Object unstacked = this.stack.unstack();
        Assert.assertEquals(1, this.stack.size());
        Assert.assertEquals("first", this.stack.top());
        Assert.assertEquals("second", unstacked);
    }

    @Test(expected = EmptyStackException.class)
    public void unstackFromEmptyStack() {
        // This expects a exception to be thrown, so it doesn't need
        // a try-catch block.
        this.stack.unstack();
    }

    @Test(expected = EmptyStackException.class)
    public void topFromEmptyStack() {
        this.stack.top();
    }

    @Test
    public void stackToFullStack() {
        for (int index = 0; index < 10; index++) {
            this.stack.stack("element " + index);
        }
        // A try-block is required because the test must fail at this point
        // forward. What's happening before is a preparation for what may
        // cause the test to fail. If something goes wrong before, the test
        // must fail. But then is expected.
        try {
            this.stack.stack("overflows");
            Assert.fail();
        } catch (FullStackException e) {
            // A simple test just because no code block must be empty
            Assert.assertEquals(10, this.stack.size());
        }
    }
}
