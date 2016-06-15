Different ways of testing exceptions in Java and JUnit
======================================================

Source
--------
[http://blog.goyello.com/2015/10/01/different-ways-of-testing-exceptions-in-java-and-junit/](http://blog.goyello.com/2015/10/01/different-ways-of-testing-exceptions-in-java-and-junit/)

01/10/2015 • rafal.borowiec • Software

Disclaimer
----------
Apart from some formatting this is the complete work of Rafal Borowiec who reserves all rights. Please ask him if you want to use this article in part or in full. See above for details.

Abstract
--------

An exception is an event that occurs during the execution of a program that disrupts the normal flow of instructions. Java programming language provides exceptions to deal with errors and other exceptional events in the code. The biggest advantage of exceptions is that they simply allow you to separate error-handling code from regular code. This improves the robustness and readability of programs created in Java.

Java provides several techniques to effectively work with exceptions:

- `try`, `catch`, and `finally` − to handle exceptions,
- `try-with-resources` statement − to work with resources,
- `throw/throws` − to throw and declare exceptions respectively.

In JUnit, we may employ many techniques for testing exceptions including:

- "Old school" try-catch idiom
- `@Test` annotation with `expected` element
- JUnit `ExpectedException` rule
- Lambda expressions (Java 8+)

Continue reading to find out which technique is best for you.

Code to be tested
-----------------

Throughout the article different examples will be presented. The below class will be used in most of them. It provides simple methods throwing exceptions, either checked or unchecked:

- `Thrower` − containing several methods that throw some exceptions

```java
public class Thrower {
      public void throwsRuntime() {
        throw new MyRuntimeException();
      }

      public void throwsRuntimeWithCause() {
        throw new MyRuntimeException(new IllegalStateException("Illegal state"));
      }

      public void throwsRuntimeWithCode(int code) {
        throw new MyRuntimeException(code);
      }

      public void throwsRuntimeInsteadOfChecked() throws MyCheckedException {
        throw new MyRuntimeException();
      }
    }
```

All examples can be found in Rafal Borowiec's [GitHub](https://github.com/kolorobot/unit-testing-demo/tree/master/src/test/java/com/github/kolorobot/exceptions "GitHub") repository.

A bit of history
----------------

In JUnit 3 and in JUnit 4 − before `ExpectedException` rule was introduced − the best way to test exceptions was by using standard classical `try-catch` idiom in a unit test:

```java
@Test
public void throwsException() {
    try {
        thrower.throwsRuntime();
        Assert.fail("Expected exception to be thrown");
    } catch (MyRuntimeException e) {
        assertThat(e)
            .isInstanceOf(MyRuntimeException.class)
            .hasMessage("My custom runtime exception");
    }
}
```

The above test will fail when no exception is thrown and the exception itself is verified in a `catch` clause. This solution is perfectly fine, but it has some drawbacks. Firstly, extra code needs to be created; we always need to remember to fail the test if no exception is thrown (otherwise nothing happens and the test will pass). And finally, if other than expected exception is thrown the test will fail but not with the assertion error as we would expect. For example, the following test:

```java
@Test
public void throwsDifferentExceptionThanExpected() {
    try {
        thrower.throwsRuntimeInsteadOfChecked();
        Assert.fail("Expected exception to be thrown");
    } catch (MyCheckedException e) {
        assertThat(e)
            .isInstanceOf(MyCheckedException.class)
            .hasMessage("My custom checked exception");
    }
}
```

will fail with the message:

```java
c.g.k.e.MyRuntimeException: My custom runtime exception
    at c.g.k.e.Thrower.throwsRuntimeInsteadOfChecked(Thrower.java:21)
    [...]
```

@Test annotation
----------------

The simplest way to verify exceptions in JUnit (4+) tests, that requires (almost) no additional code, comes with `@Test` annotation and `expected` element. The example shows how simple the solution is:


```java
public class ExpectedTest {

    private Thrower thrower = new Thrower();

    @Test(expected = MyRuntimeException.class)
    public void throwsException() { // will pass
        thrower.throwsRuntime();
        System.out.println("I am here!"); // never gets executed
    }

    @Test(expected = MyCheckedException.class) // will fail
    public void throwsDifferentExceptionThanExpected() throws MyCheckedException {
        thrower.throwsRuntimeInsteadOfChecked();
    }

    @Test(expected = MyRuntimeException.class)
    public void noExceptionThrown() { // will fail

    }

    @Test(expected = RuntimeException.class)
    public void misleading() { // will pass
        thrower.throwsRuntime(); // assume this is an unexpected exception
        throw new RuntimeException(); // never executed!
    }
}
```

This approach to testing exceptions in JUnit code is a really simple, built-in, not much code but ... we need to be quite careful about using `@Test` annotation: there is way to verify the message or the cause which may lead to quite unexpected behaviour like in the misleading method in the above example.

But fortunately there is a better solution!

Introducing ExpectedException rule
----------------------------------

In JUnit, rules (`@Rule`) can be used as an alternative or an addition to fixture setup and
cleanup methods: `@Before`, `@After`, `@BeforeClass`, and `@AfterClass`. `ExpectedException` rule is meant for verification that code throws a specific exception. The rule must be declared as public field annotated with `@Rule` annotation:

```java
public class Junit4RuleExceptionsTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
}
```

To properly use a `ExpectedException` rule we need to add expectations to it before execution of the test method. Note that adding a rule does not affect tests that are not using it which means you may have tests that use rule and ones that don’t. In addition, the rule may be reused − we don’t need to create separate rules for different tests.

In the below example, we verify the type and message of an exception.

```java
@Test
public void verifiesTypeAndMessage() {
    thrown.expect(MyRuntimeException.class);
    thrown.expectMessage("My custom runtime exception");

    thrower.throwsRuntime();
}
```

If the expected exception is not thrown a valid assertion error will be thrown by JUnit with a descriptive message:

```java
java.lang.AssertionError:
Expected: (an instance of c.g.k.e.MyRuntimeException and exception with message a string containing "My custom runtime exceptions")
     but: exception with message a string containing "Something else" message was "My custom runtime exception"
```

As you may see, the code is much more readable. We are also sure that if other type of exception is thrown the rule will record that and inform us the same way. So the previous example will look much simpler now:

```java
@Test
public void throwsDifferentExceptionThanExpected() throws MyCheckedException {
    thrown.expect(MyCheckedException.class);
    thrown.expectMessage("Expected exception to be thrown");

    thrower.throwsRuntimeInsteadOfChecked();
}
```

and it will fail with a message:

```java
java.lang.AssertionError:
Expected: (an instance of c.g.k.e.MyCheckedException and exception with message a string containing "Expected exception to be thrown")
     but: an instance of c.g.k.e.MyCheckedException  is a c.g.k.e.MyRuntimeException
```

And if there is no exception thrown:

```java
@Test
public void doesNotThrowExpectedException() {
    thrown.expect(MyRuntimeException.class);
    // the below line is optional
    thrown.reportMissingExceptionWithMessage("No exception of %s thrown");
}
```

the message will be:

```java
java.lang.AssertionError: No exception of type an instance of c.g.k.e.MyRuntimeException thrown
    at org.junit.Assert.fail(Assert.java:88)
```

What can I do more with `ExpectedException`?
------------------------------------------

What is more, the `ExpectedException` rule provide us methods to verify exceptions in a more sophisticated way using Hamcrest matchers. [Hamcrest](https://github.com/hamcrest "Hamcrest") *provides a library of matcher objects* and it works great with JUnit. If you work with Maven or Gradle JUnit depends on Hamcrest so Hamcest will be in the classpath.

### Verify the message with either built-in Hamcrest matcher ###

```java
import static org.hamcrest.CoreMatchers.startsWith;

@Test
public void verifiesMessageStartsWith() {
    thrown.expect(RuntimeException.class);
    thrown.expectMessage(startsWith("My custom runtime"));

    thrower.throwsRuntime();
}
```
We can verify the cause with a custom Hamcrest matchers:

```java
@Test
public void verifiesCauseTypeAndAMessage() {
    thrown.expect(RuntimeException.class);
    thrown.expectCause(new MyCauseMatcher(IllegalStateException.class, "Illegal state"));

    thrower.throwsRuntimeWithCause();
}
```

whereas MyCauseMatcher is as follows:

```java
class MyCauseMatcher extends TypeSafeMatcher {
    private final Class<? extends Throwable> expectedType;
    private final String expectedMessage;

    public MyCauseMatcher(Class<? extends Throwable> expectedType,
                            String expectedMessage) {
        this.expectedType = expectedType;
        this.expectedMessage = expectedMessage;
    }

    @Override
    protected boolean matchesSafely(Throwable item) {
        return item.getClass().isAssignableFrom(expectedType)
            &amp;&amp; item.getMessage().contains(expectedMessage);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("expects type ")
                   .appendValue(expectedType)
                   .appendText(" and a message ")
                   .appendValue(expectedMessage);
    }
}
```

I usually used built-in matchers as they provide most of the basic and more advanced matchers. Custom matchers can be used for some special cases like for example verifying more complex exception objects with come custom fields etc.

AssertJ
-------

[AssertJ](http://joel-costigliola.github.io/assertj/ "AssertJ") − Fluent assertions for Java − provides a rich set of assertions with helpful error messages. With AssertJ (3+) and Java 8 testing exceptions is much easier than before. The idea is to pass a Java 8 `@FunctionalInterface` whose instances can be created with lambda expressions, method references, or constructor references to an assertion method that will capture the exception and return an assertion object. Let’s see an example:

```java
@Test
public void verifiesTypeAndMessage() {
    assertThatThrownBy(new Thrower()::throwsRuntime) // method reference
            // assertions
            .isInstanceOf(MyRuntimeException.class)
            .hasMessage("My custom runtime exception")
            .hasNoCause();
}
```

`assertThrown` is a static factory method creating a new instance of `ThrowableAssertion` with a reference to caught exception. `ThrowableAssertion` if further used to verify the exception.

We can also pass lambda expressions directly to the method:

```java
@Test
public void verifiesCauseType() {
    assertThatThrownBy(() -> new Thrower().throwsRuntimeWithCause())
            .isInstanceOf(MyRuntimeException.class)
            .hasMessage("My custom runtime exception")
            .hasCauseInstanceOf(IllegalStateException.class);
}
```

AssertJ also supports a so called *AAA* style, if you wish to distinguish act and assert phases of the test for improving readability:

```java
@Test
public void aaaStyle() {
    // arrange
    Thrower Thrower = new Thrower();

    // act
    Throwable throwable = catchThrowable(Thrower::throwsRuntime);

    // assert
    assertThat(throwable)
            .isNotNull()
            .hasMessage("My custom runtime exception");
}
```

In my opinion testing exceptions with AssertJ and Java 8 is one of the cleanest solutions so far. It is really easy to write and read. Comparing to `ExpectedException` rule it does not require to define any public field that may not be used in many tests in the class. In addition, the standard assertions offered by the library are enough for many cases. And if not, you can easily create custom ones.

Going native?
-------------

If you are reluctant to use 3rd party libraries like AssertJ, you may write your own Java 8 style exception handling code. It is not that hard. You may find a pretty good example here: [http://blog.codeleak.pl/2014/07/junit-testing-exception-with-java-8-and-lambda-expressions.html](http://blog.codeleak.pl/2014/07/junit-testing-exception-with-java-8-and-lambda-expressions.html)

Summary
-------

There is no single and best way to test exceptions in JUnit. The technique chosen depends on the code to be tested. For basic cases standard `@Test` annotation may be utilized. For more complex scenarios, `ExpectedException` can be employed as it is also very simple but much more powerful than `@Test` annotation. Built-in or custom Hamcrest matchers offer some possibilities for creating better tests.

As of Java 8, I am in favour of AssertJ’s way of testing exceptions. I find it really clear (e.g. I don’t need to introduce an additional `public` field that may be used or not), easy to write (in most cases one liners) and really powerful (great built-in matchers plus extensible with easy to write custome ones).

Since in all my tests I employ AssertJ I don’t see any reason for not using it for testing exceptions in JUnit.

And how do you test exceptions in your Java / JUnit code?
