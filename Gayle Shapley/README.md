# **Gayle Shapley matching Algorithm**

## Problem Definition
The Gayle Shapley Matching Algorithm addresses the problem of finding stable matchings between two disjoint sets, each with preference lists ranking elements of the other set. Specifically, the task is to design an O(n^2) algorithm to determine the number of elements in the first set that have only one valid partner, defined as being paired with the same element in every stable matching.

## Solution Approach
### Overview
The algorithm employs the Gayle Shapley algorithm to achieve stable matchings between two sets while ensuring a time complexity of O(n^2). The Gayle Shapley algorithm yields optimal results for one set (askers) and pessimal results for the other (responders), making it suitable for our scenario.

### Implementation Details
#### 1. Data Representation
* Preference lists are stored in a nested array, ensuring O(n^2) time complexity.
* Askers are stored in a stack (O(1) time) for efficient retrieval and placement into the unmatched pool.

#### 2. Inverse Preference List
* An inverse preference list is used to avoid repeated linear searches when deciding if a responder should upgrade. Each index in this list represents the ranking of an asker, allowing for O(1) time retrieval.

#### 3. Algorithm Execution
* A while loop iterates through each asker, checking the state of every option on their preference list.
* If an option is unmatched, instant matching occurs; if matched, the inverse preference list is used to determine if the responder wants to upgrade.
* The loop ends when all askers are matched.

#### 4. Array Combination and Comparison
The System.arraycopy method combines askers' and responders' matches into one array.
A comparison is made to determine how many elements in the first half of the array match the second half.

## Overall Time Complexity
The overall time complexity of the algorithm is O(n^2), ensuring efficiency in processing large-scale datasets.
