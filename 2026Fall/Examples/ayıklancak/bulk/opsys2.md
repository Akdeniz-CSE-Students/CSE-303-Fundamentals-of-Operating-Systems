Aşağıda, gönderdiğin 4 sayfalık sınav kağıdındaki tüm soruların, şıkların, el yazısı cevapların ve çizimlerin detaylı bir transkripsiyonunu içeren Markdown dosyası bulunmaktadır.

***

# CSE303 - Fundamentals of Operating Systems - Final Exam
**Date:** January 11th, 2024 10:30
**Duration:** 90 Minutes

---

## Page 1

### Q1. Scheduling & Starvation
A scheduler uses a prioritized round-robin scheduling policy. New processes are assigned an initial quantum of length $q$. Whenever a process uses its entire quantum without blocking, its new quantum is set to twice its current quantum. If a process blocks before its quantum expires, its new quantum is reset to $q$. For the purposes of this question, assume that every process requires a finite total amount of CPU time.

**(a) Suppose the scheduler gives higher priority to processes that have larger quanta. Is starvation possible in this system, i.e., is it possible that a process will never be completed because it is neglected by the scheduler? Explain briefly.**

> **Student Answer:**
> No, starvation is not possible. it is still a Round Robin mechanism.

**(b) Suppose instead that the scheduler gives higher priority to processes that have smaller quanta. Is starvation possible in this system? Explain briefly.**

> **Student Answer:**
> Yes starvation possible. Because if new processes arrive regularly then old processes or existing processes cannot get a chance to use the cpu.

### Q2. Frames vs. Pages
**What are the differences and similarities between the frames and pages.**

> **Student Answer:**
> **Difference:** Physical memory blocks are frames, pages are virtual.
> **Similarity:** Their size are the same.

### Q3. Process Creation (fork)
**Considering the following C code; How many child processes created with respect to n? Mark the correct answer and show your solution!**

```c
for (i = 0; i < n; i++)
    fork();
```

**Options:**
a) $n$
b) $2^n - 1$ *(Marked by student)*
c) $2^n$
d) $n(n+1)/2$
e) $2n/2$
f) $n(n+1)(2n+1)/6$
h) $n^2/4$

> **Student's Diagram & Solution:**
> The student drew a process tree diagram:
> *   $1 \to P$ (Parent)
> *   $2 \to P \to C$
> *   Level 1: 1 process
> *   Level 2: 3 children (indicated with arrow "2nd level 3 child")
> *   Level 3: 7 children (indicated with arrow "3rd level 7 child")
> *   Final formula written: $\to 2^n - 1$

---

## Page 2

### Q4. Virtual Memory & Paging
A system implements a paged virtual address space for each process using a one-level page table. The maximum size of an address space is 16 megabytes. The page table for the running process includes the following entries:

| Virtual Page | Frame # |
| :--- | :--- |
| 0 | 4 |
| 1 | 8 |
| 2 | 16 |
| 3 | 17 |
| 4 | 9 |

The page size is 1024 bytes and the maximum physical memory size of the machine is 2 megabytes.

**(a) How many bits are required for each page table entry?**
> **Student Answer:**
> To address any frame we need **11 bits**.
> *(Note: Physical memory 2MB = $2^{21}$ bytes. Page size 1024 = $2^{10}$ bytes. Number of frames = $2^{21} / 2^{10} = 2^{11}$. Thus, 11 bits needed for Frame #).*

**(b) What is the maximum number of entries in a page table?**
> **Student Answer:**
> $2^{14} = 16384$
> *(Note: Max virtual address 16MB = $2^{24}$. Page size $2^{10}$. Total pages = $2^{24} / 2^{10} = 2^{14}$).*

**(c) How many bits are there in a virtual address?**
> **Student Answer:**
> **24 bits**
> *(From 16MB max address space).*

**(d) To which physical address will the virtual address 1524 translate to? (show how you get)**
> **Student Answer:**
> $1524 // 1024 = 1$ (VPN) , $500$ bytes left (offset) $\to$ vict page 1 $\to$ 8
> $8 \times 1024 + 500 = \mathbf{8692}$

**(e) Which virtual address will translate to physical address 10020? (show how you get)**
> **Student Answer:**
> $10020 // 1024 = 9$ (Frame #), offset = $804$.
> $9 \to$ virt page 4.
> $4 \times 1024 + 804 = \mathbf{4900}$

### Q5. Banker's Algorithm (Deadlock Avoidance)
Consider the following snapshot of a system where P = Processes, R = Resources. Beware of the representation!

**Given Tables:**
*   **Max:** P1(3,6,3,4), P2(2,1,1,2), P3(2,3,4,2)
*   **Allocated:** P1(1,6,2,0), P2(0,1,1,0), P3(0,2,1,2)
*   **Avail:** (0,1,1,1)
*   **Total:** (9,3,?, 6) *(Note: Total column seems partially obscured or implied by sum, student wrote calculations aside)*

**Calculations on paper (Need Matrix):**
Max - Allocation = Need
*   P1: (3-1, 6-6, 3-2, 4-0) = (2, 0, 1, 4)
*   P2: (2-0, 1-1, 1-1, 2-0) = (2, 0, 0, 2)
*   P3: (2-0, 3-2, 4-1, 2-2) = (2, 1, 3, 0)
*   *Note: Student wrote a different Need matrix below, likely re-interpreting the columns/rows order or P indices. Let's transcribe exactly what is written in the "Need" section:*

**What is the content of the matrix Need? (write the matrix below)**
> **Student Answer (Handwritten Matrix):**
> $P_1$: 2 2 2 ? (Crossed out) -> Student wrote: **2 0 1**
> $P_2$: 0 0 1 ? -> Student wrote: **0 0 1**
> $P_3$: 1 0 3 ? -> Student wrote: **1 0 3**
> $P_4$: 4 2 0 ? -> Student wrote: **4 2 0**
> *(Note: The student seems to have derived 4 processes (P1..P4) and 3 resource types based on the scribbles "6 2 3", "8 3 4", "9 3 6" next to the table. The "Total" column in the question lists 4 rows but the labels only show P1, P2, P3. There is an ambiguity in the print vs student interpretation. Student assumes P4 exists).*

**Is the system in a safe state? If yes, give a safe sequence of processes. If your answer is no, explain why.**
> **Student Answer:**
> YES. Safe state = $(P_2, P_3, P_4, P_1)$

### Q6. Deadlock Conditions
**What are the four conditions that can lead to a deadlock state?**
> **Student Answer:**
> 1) Mutual Exclusion
> 2) Hold and Wait
> 3) No preemption
> 4) Circular Wait

---

## Page 3

### Q7. Synchronization
Two processes X and Y need to access a critical section. Consider the following synchronization construct used by both the processes.

**Process X:**
```c
/* other code */
while (true) {
    varP = true;
    while (varQ == true) {
        /* Critical Section */
        varP = false;
    }
}
/* other code */
```

**Process Y:**
```c
/* other code */
while (true) {
    varQ = true;
    while (varP == true) {
        /* Critical Section */
        varQ = false;
    }
}
/* other code */
```
Here, `varP` and `varQ` are shared variables and both are initialized to false. Which one of the following statements is true?

**(B) The proposed solution guarantees mutual exclusion but fails to prevent deadlock.**
*(Student circled option B)*

### Q8. Scheduling Algorithms (Calculations)
An operating system uses a preemptive scheduler algorithm. For the values given table below;

| Proc. ID | Submission Time | Required Computing Time |
| :--- | :--- | :--- |
| A | 0 | 4 |
| B | 1 | 6 |
| C | 3 | 3 |
| D | 4 | 2 |

**(a) Assuming a scheduling quantum of 2 time units and the running process is preempted only if its quantum expires or it receives its required computing time and exits. What is the response time and turnaround time for each process? Show your solution.**

> **Student's Gantt Chart:**
> `| A (0-2) | B (2-4) | C (4-6) | D (6-8) | A (8-10) | B (10-12) | C (12-13) | B (13-15) |`
> *(Note: The timeline is drawn from 0 to 15)*

> **Student's Calculations:**
> **Response Time:**
> A: $0 - 0 = 0$
> B: $2 - 1 = 1$
> C: $4 - 3 = 1$
> D: $6 - 4 = 2$
>
> **Turnaround Time:**
> A: $9 - 0 = 9$ *(Matches finish time on chart)*
> B: $14 - 1 = 13$
> C: $12 - 3 = 9$
> D: $7 - 4 = 3$ *(Correction: Chart shows D runs 6-8, so finish is 8? Student wrote 7. Let's trust the transcription of handwriting: 7-4=3)*

**(b) Assuming that the scheduler is non-preemptive and uses the shortest-job-first (SJF) heuristic. What is the response time and turnaround time for each process?**

> **Student's Gantt Chart:**
> `| A (0-4) | D (4-6) | C (6-9) | B (9-15) |`
>
> **Student's Calculations:**
> **Response Time:**
> A: $0 - 0 = 0$
> B: $8 - 1 = 7$ *(Note: Chart shows start at 9, but calculation says 8. Wait, if A(4), then D(2) -> 6, C(3) -> 9, B(6) -> 15. Start times: A=0, D=4, C=6, B=9. Response: A=0, D=4-4=0, C=6-3=3, B=9-1=8. Student wrote different numbers: B: 8-1=7, C: 5-3=2, D: 4-4=0. There seems to be a slight error in student's calculation or chart).*
>
> **Turnaround Time:**
> A: $4 - 0 = 4$
> B: $14 - 1 = 13$
> C: $8 - 3 = 5$
> D: $5 - 4 = 1$

### Q9. Scheduling Categories
**Group the CPU scheduling algorithms that you know in the following two categories. You may include some of the algorithms in both of the groups.**

| Preemptive | NonPreemptive |
| :--- | :--- |
| Round Robin | FCFS (First Come First Served) |
| SRTF (Shortest Remaining Time First) | SJF (Shortest Job First) |
| Multilevel Feedback | Priority |

---

## Page 4

### Q10. Memory Mapping Calculation
Consider mapping a virtual memory of 1GB = $2^{30}$ bytes onto a physical memory organized into 256 frames of 4KB each. Moreover, assume that the smallest addressable unit is 1 byte. The memory management strategy always keeps at least 1 frame per active process resident in main memory for paging purposes (i.e. at most 256 active processes). Calculate the following parameters.

**(a) Page Size:**
> **Student Answer:**
> $4KB = 2^{12}$ bytes

**(b) Number of bits to represent a frame:**
> **Student Answer:**
> **8 bits**
> *(Reasoning: 256 frames = $2^8$)*

**(c) Physical memory size in MB:**
> **Student Answer:**
> $2^8 \times 2^{12} = 2^{20}$ bytes = **1 MB**

**(d) Maximum number of pages ($2^?$):**
> **Student Answer:**
> $2^{30} / 2^{12} = \mathbf{2^{18}}$

**(e) Number of bits to address a byte (used as offset) $2^?=$:**
> **Student Answer:**
> **12 bits** ($2^{12}$)

> **Diagram:**
> Student sketched a Physical Memory map showing 4KB blocks and indices $2^8$.

### Q11. Process Terms
**Explain the following terms for a process:**

**(a) Text Segment:**
> **Student Answer:** Instructions

**(b) Program counter:**
> **Student Answer:** Address of the next instruction to execute

**(c) Stack:**
> **Student Answer:** Function parameters, return values, local variables

**(d) Data segment:**
> **Student Answer:** Global variables

**(e) Heap:**
> **Student Answer:** Dynamically allocated variables

### Q12. Amdahl's Law
**When running on a supercomputer having billions of cpus, a process has a speedup value less than 5. What will be the speedup value if this process is executed only on 4 cpu?**

> **Student's Derivation:**
> Formula: $S \le \frac{1}{s + \frac{1-s}{N}}$
> As $N \to \infty$, $S \to \frac{1}{s}$
> Given $S < 5$, so $\frac{1}{s} < 5 \Rightarrow s > \frac{1}{5} \Rightarrow s > 0.2$ (Serial portion is 20%)
>
> **Calculation for N=4:**
> Speedup = $\frac{1}{0.2 + \frac{0.8}{4}}$
> $= \frac{1}{0.2 + 0.2} = \frac{1}{0.4}$
> $= \mathbf{2.5}$

### Q13. Readers-Writers Problem
**Modify the Readers-Writers problem so that at most N readers can read simultaneously. Write the updated pseudocode for both Reader and the Writer process.**

> **Student Answer (Pseudocode):**

**Writer:**
```c
wait(rwmutex)
write();
signal(rwmutex)
```

**Reader:**
*(Note: Student added a semaphore `readmutex` initialized to N at the top)*
```c
wait(mutex) // Looks like 'readmutex' or similar limiting semaphore
readers++
if (readers == 1)
    wait(rwmutex)
signal(mutex)

// Reading logic...
read();

wait(mutex)
readers--;
if (readers == 0)
    signal(rwmutex)
signal(mutex) // Matches the semaphore used at start
```

*(Correction/Observation on Q13: The student crossed out several lines. The core logic visible is guarding the read entry with a semaphore to limit N readers. There is a `readmutex = N` note. The code `wait(readmutex)` [likely meant the N-counter semaphore] is placed before `wait(mutex)` [the variable protection]. However, the specific variable names in the handwriting are slightly messy (e.g., `mutex` vs `readmutex`). The logic `wait(readmutex)` at the very start of Reader and `signal(readmutex)` at the very end would correctly limit to N readers.)*
