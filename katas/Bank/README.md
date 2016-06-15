# London School Kata
  
+ Language: Any with a mocking framework, e.g. Java, Scala, PHP, C# or Go
+ Level: Easy
+ Requires: Mocking framework, Internet connection, IDE recommended

## Motivation

The London School approach is also known as Outside-In TDD and concentrates more on the behaviour of objects rather than the state. It can lead to a better design than classic TDD if you already have a rough idea of the design. It also supports the "Double Loop of TDD" better, that is start with acceptance test and then fine-tune it with unit tests.

![Double Loop TDD](http://i.imgur.com/EZ84KuV.png)

## Objective
  
Learn and practice the double loop of TDD with a simple acceptance test. Test your application from the outside, not from the inside. This is an introductory kata and requires no BDD (Behaviour Driven Development) frameworks or such.

This kata is known as the "Bank kata". You can find multiple references on the web, this one is taken from Ruben Straube, see [https://github.com/rubek/bank-kata-softwerkskammer](https://github.com/rubek/bank-kata-softwerkskammer "https://github.com/rubek/bank-kata-softwerkskammer"). A more complex setup can be found on [https://github.com/sandromancuso/Bank-kata](https://github.com/sandromancuso/Bank-kata  "https://github.com/sandromancuso/Bank-kata").

## Coding

### Task

Create a simple bank application with the following features:
  
- Deposit into Account
- Withdraw from an Account
- Print a bank statement to the console.

Start with an acceptance test, see below. If motivated, follow the [Object Calisthenics](https://github.com/goeckeler/katas/blob/master/Object%20Calisthenics/README.md "Object Calisthenics Rules") rules.
  
### Acceptance criteria

Statement should have the following the format:
        
        DATE       | AMOUNT  | BALANCE
        10/04/2014 | 500.00  | 1400.00
        02/04/2014 | -100.00 | 900.00
        01/04/2014 | 1000.00 | 1000.00
 
Don't bother about spaces and formatting.
 
### Class under test 
 
You need to start testing from the following `Account` class or similar.        
        
        public class Account {
            public void deposit(int amount);
            public void withdrawal(int amount);
            public void printStatement();
        }
 
You are not allowed to add any other public method to this class. Those are the only *methods* that are visible from outside.
 
### Sample Acceptance Test
 
Proposed acceptance test starting point, here in Java syntax:

      public class PrintStatementFeature {    
        @Test public void  
          print_statement_containing_transactions_in_reverse_chronological_order() {
              account.deposit(1000);
              account.withdraw(100);
              account.deposit(500);
              
              account.printStatement();
         
              verify(console).printLine("DATE | AMOUNT | BALANCE");
              verify(console).printLine("10/04/2014 | 500.00 | 1400.00");
              verify(console).printLine("02/04/2014 | -100.00 | 900.00");
              verify(console).printLine("01/04/2014 | 1000.00 | 1000.00"); 
        }
      }

This won't compile even after adding Mockito to the classpath. That's on purpose, may be you get an idea why.