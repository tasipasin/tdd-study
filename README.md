# A study about TDD


**TDD Cicle:**

- Add Test
- What test fail
-- If a test is working, there's nothing else to do. There'll be a need to change code only if a test fails.
- Write/correct the code
- Run tests
- Refactor

In short: Create tests that will fail, but has an expected result, and then create a code do make tests pass. For last, refactor/clean the code.

When a test is added/created, it defines the class interface and the expected behaviour.
Using TDD is not about creating a hundred test at once, but creating one by one developing as basic as you can.

**Refactoring**

Must be done after implemented the code using the simplest solution and passing all written tests.

Refactor is improve software quality to a code:
- Easy to read;
- Easy to understand;
- Easy to maintain.

When refactoring, don't:
- Add new tests
- Add new features

