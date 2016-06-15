# Real-life Anagrams #

Now we want to solve real life puzzles. Thus we need a bigger dictionary, see enclosed file.

## Step 1 ##

Use the given dictionary (without deleting the previous one). From Java 7 onwards you can read in a text file in a single line of code:

`List<String> lines = Files.readAllLines(FileSystems.getDefault().getPath("dir", "file"), Charset.forName("UTF-8"));` 

This routine will throw an `IOException` on failure.

## Step 2 ##

As a customer I want to have all anagrams for a given word to be returned in less than 200ms. Ensure that this requirement is tested.

How could you make your implementation even faster and return in 20ms for example?

## Step 3 ##

How long does it take you to create the complete list of anagrams for all the words? Less than a minute sounds what I need as a customer.