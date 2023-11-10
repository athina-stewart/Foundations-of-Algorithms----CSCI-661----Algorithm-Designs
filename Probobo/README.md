# Job Selection Algorithm

## Problem Description
As a contractor seeking to maximize your client base and contribute to a non-profit organization, the task is to design an O(n log n) algorithm to determine the maximum number of non-overlapping jobs you can undertake. At least one of the accepted jobs must be pro bono, denoted by a value of 1 in the job descriptor.

### Input Specification
* The first line contains n, the total number of jobs.
* This is followed by n lines, each representing a job with three integers separated by spaces:
1. Starting time of the job.
2. Ending time of the job.
3. A value of 1 (pro bono) or 0 (not eligible for pro bono work).

### Output Specification
The output contains a single line with a single number: the maximum number of non-overlapping jobs that can be chosen, ensuring at least one of the chosen jobs has pro bono status.

## Algorithm Explanation
### Approach
The algorithm employs a greedy approach to find the maximum number of jobs without considering pro bono potential. It then integrates pro bono jobs into the solution, ensuring compatibility with the greedy jobs.

### Steps
1. Sort Jobs:
* Sort the jobs by both start and end times to facilitate greedy job selection. (Time Complexity: O(n log n))

2. Greedy Job Selection:
* Find the maximum number of non-overlapping jobs using a greedy algorithm.
* This includes selecting jobs that finish at the same time as others begin.

3. Integrated Pro Bono Jobs:
* For each pro bono job, find the index of the preceding and succeeding greedy jobs.
* Use a modified binary search to efficiently locate compatible jobs.
* Ensure that the overall solution guarantees the inclusion of at least one pro bono job.

## Overall Time Complexity
The overall time complexity is O(n log n), achieved through sorting the jobs and implementing the greedy algorithm. The integration of pro bono jobs maintains this efficiency, ensuring a practical solution for large datasets.

*** 

### Example
Consider the example where jobs are sorted by both start and end times. Pro bono jobs are marked in red, non-pro bono jobs in blue, and shaded jobs represent the greedy solution.

_Test Case 3_

12 <br> 0 10 1 <br> 7 13 1 <br> 11 17 1 <br> 14 20 1 <br> 0 4 0 <br> 5 8 0 <br> 11 12 0 <br> 10 13 0 <br> 11 16 0 <br> 12 15 0 <br> 16 18 0 <br> 19 20 0

![Probono](https://github.com/athina-stewart/Foundations-of-Algorithms----CSCI-665----Algorithm-Designs/assets/49656095/24c4abe8-2a97-4a1b-a777-a865c66873ea)

The algorithm ensures an optimal selection of non-overlapping jobs while incorporating pro bono opportunities, meeting both business and civic objectives.
