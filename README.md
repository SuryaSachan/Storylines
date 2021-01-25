# Storylines
Implement the information in the two csv files as a graph and write functions to output the following:

(a) Function: average. Should print the average number of characters each Marvel character is
associated with, as a float upto two decimal places.

Sample random output:

$java assignment4 nodes.csv edges.csv average

7.43

(b) Function: rank prints a sorted list of all characters, with comma as delimiter (only comma,
as delimiter and no space). Sorting is in descending order of co-occurrence with other
characters. That is, characters with more co-occurrences appear before. If there is a tie between
characters based on co-occurrence count, then the order is in descending based on
lexicographic order of the character strings.

Sample random output:

$java assignment4 nodes.csv edges.csv rank

Yogish,Riju,Rahul

(c) Function: independent_storylines_dfs  implements DFS, then find independent storylines,
that have no edge across them, using DFS. Prints the characters in each independent storyline, as a
separate line in the output.
The largest storyline (with maximum characters)  appears at the top, followed by the second
largest and so on. Within each line, the character names are delimited with comma, and
lexicographically sorted in descending order. If two storylines have same number of characters,
ties are broken in lexicographically descending order of character names.

Sample random output:

$java assignment4 nodes.csv edges.csv independent_storylines_dfs

Riju,Rahul

Yogish
