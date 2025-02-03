# Class 01 (01-tdd-stack)

Develop a Stack class, that can stacks objects, using TDD.

Steps:
1. Created StackTest.java 
a. Create the first test. It must start from the most basic to the most complex. In this case, the first test will be to test emptyStack.
The code won't compile, because any of the methods are created.
b. So, for this test, creating the methods and returning hard-coded values is enough.
c. The test passed.

2. Create more tests.
a. Create a test to stack one element. The method to stack is not available yet.
b. Create everything needed to stack the element, in this case, stack() and top().
c. stack() will set an simple object to the value received and top will return it (like a setter and getter).
d. isEmpty() must be changed to check if the object isn't null. 
e. size() must have it's own counter of elements incremented on every stack() call.

This method creates the simples solution for one problem

3. The process must be repeated creating failing tests. Any new test created that pass at first it's not good.