# Challenge 1 (02-camel-case-challenge)

Create a method to transform a camel case (https://en.wikipedia.org/wiki/Camel_case) String into a List of String.
The method must have the signature: ```public static List<String> convertCamelCase(String original)```

To every new test created, describe which test was created and the code status before and after the test pass. See [Report Section](#report). 

Here's some examples:

|Input|Output|
|---|---|
|nome|“nome” |
|Nome|“nome” |
|nomeComposto|“nome”, “composto” |
|NomeComposto|“nome”, “composto” |
|CPF|“CPF” |
|numeroCPF|“numero”, “CPF” |
|numeroCPFContribuinte|“numero”, “CPF”, “contribuinte” |
|recupera10Primeiros|“recupera”, “10”, “primeiros” |
|null|Invalid → received a null value|
|*empty*|Invalid → received an empty value|
|10Primeiros|Invalid → should NOT begin with a number |
|nome#Composto|Invalid → special character aren't allowed, only letter and numbers|

Feel free to create any utility methods needed.

For each refactoring needed, add a section in the [Report Section](#report) showing the code as it was before and how it was afterwards.

*Rules*:
1. **In order to practice refactor, no method should be greater than 10 lines of code**
2. **Only pure Java API utilization is allowed.**

# Report
### Preparation
1. Created project structure.
2. Created class CamelCase.java, which will receive the problem solution implementations.
3. As the required method is *static* at CamelCase java class, to avoid code smells, the constructor must be privated.
4. Created class CamelCaseTest.java, which will receive the test implementations.

## 1st Cicle
It must start from the simplest case, that must cover a string with no camel case and no work needed. So, the thest simpleStringDoNothing() was created.

The test uses the ```CamelCase::convertCamelCase(String)``` method passing the "nome" string and it must returns the list with only one value and it must be the same as the original.

❌ The CamelCase class had nothing on it besides the private constructor, so the required method was created and, because this case required no modification, the return list was created, the received string was added and the list returned.

✔️ All tests passed

## 2nd Cicle
The next test must cover a simple string (no more than 1 word) that begins with a Upper Case letter.

The test itself was copied from the previous changing only the input to "Nome".

❌ As the test failed when comparing the expected result "nome" with the output, it's time to adequate the solution. So, to solve this, the operation String::toLowerCase() was used before inserting the value received as parameter into the result list.

✔️ All tests passed

## 3rd Cicle
The basic are covered, now it's time to split camel case string. So the next test added is for input "nomeComposto".

❌ In order to make the test pass, the first thing is to locate where there's a uppercase letter. To find this, the ASCII value of each char is checked to be between 65 and 90, both included included. Each new uppercase char is extracted the substring from the original string.

✔️ All tests passed

## 4th Cicle
✔️ The next example to be tested is the input "NomeComposto". This test pass, so let's go to the next example: "CPF".

❌ At this case, as it is an acronym, it should not be transformed to lowercase. To solve this, the method suffered a considerous changing. Now, it keeps the information if the last char checked is UpperCase. If not, it means that from the last UpperIndex found to current, is a word and change the lastUpperIndex value to currentIndex. If it's not an upperCase but the last was, it goes to acronym. For the base test, it checks if it is equals to itself in uppercase, if yes, don't transform to lowercase.

✔️ All tests passed

## 5th Cicle

✔️ Next example to be used is "numeroCPF"; it pass.

❌ Next is "numeroCPFContribuinte". It fails as the last value is not "contribuinte", but "cpfcontribuinte". To solve this, the lastUpperIndex had to be changed to the currentIndex - 1.

✔️ All tests passed

## Time to Refactor:
The code, now, seems a little messy. For now, let's refator the test class, afterwards, it must also be readable and understandable. Up to now, all the tests have the same structure: it call the convertCamelCase() method, checks it's size and all the values expected, So, it can be put in a generic method.

So, the method created for this was the ```CamelCaseTest:runsBasicTest(String original, int expectedSize, String... expectedOutputs)```, where:
- original: is the original string;
- expectedOutputs: all the terms expected at the output.

And now the tests are easier to read, understand and write new ones.

In addition to that, all the testes are enumerated with the prefix "tc0X_", where X is the unique number of the test.

## 6th Cicle
❌ Now, let's test the input "recupera10Primeiros". It fails, as the code is not expecting any numbers. So here there's two alternatives: adds a verification where checks for ascii number too or; check if it's different from the lowercase letters interval. It'll be implemented the first one.

✔️ After only adding an OR operator with interval [48, 57], the tests pass.

## 7th Cicle
Here begins the cicles of data input integrity. The first of all, is the empty/null check. When this happend, the code must throw an EmptyInputException.

❌ When creating the test ```emptyInput()```, it should fail, as the Exception doesn't exist yet. 

✔️ Creating the EmptyInputException and throwing it on null or empty input check makes the tests pass. Since it's the same scope, the test ```nullInput()``` is created as well, it also passes.

## 8th Cicle

And here's the tests for wrong format inputs:
- Starting with numbers;
- Having a special character;

❌ Tests beginsWithNumber() and specialCharacters() where created for this pupose

✔️ The first was solved by adding a verification at the first very char of the string after making sure it wasn't empty or null (7th Cicle)
✔️ The second was solved using regex to check if there's only letters and numbers at the input.

## Final refactoring

The method works fine and all tests passed, but the rule says "10 lines maximum", that means i have to refactor the method.

First thing: move the input checks into another method: ```private static void checkInput(String input)```. The input exceptions will be thrown from here.

Second: Abstract get an substring from the original string using ```getItem(String original, List<String> destination, int begin, int end)```. This will get the substring from the original string, put it into the list of strings and return the end.

Third: ```toLowercase(List<String> toLower)``` passes all the String from the list into lowercase, but only if the string is not all uppercase.

-----------

This is the end for now, but it doesn't mean I've covered all scenarios.  There will likely be cases I've forgotten.

