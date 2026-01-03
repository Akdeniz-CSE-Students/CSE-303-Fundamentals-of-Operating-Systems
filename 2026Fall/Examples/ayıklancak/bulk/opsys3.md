Aşağıda, gönderdiğin görsellerdeki tüm sınav sorularını, kod parçalarını ve şıklarını içeren detaylı ve eksiksiz **Markdown (.md)** dokümanı bulunmaktadır. Görseller iki ana kategoriye (Online Sınav Ekran Görüntüleri ve Kağıt Sınav Fotoğrafları) ayrılarak düzenlenmiştir.

```markdown
# CSE 303 Operating Systems - Sınav Soruları ve Çalışma Kağıtları Derlemesi

Bu doküman, sağlanan ekran görüntüleri ve kağıt sınav fotoğraflarından çıkarılan metinleri, kodları ve soruları içermektedir.

---

## BÖLÜM 1: Online Sınav Ekran Görüntüleri

Bu bölümdeki sorular bilgisayar ekranından çekilmiş test ve klasik soruları içerir.

### Question 1
**Which parts of the program is shared among the threads.** (4 Puan)

*   [x] **Code section, data section, open files** (Seçili Cevap)
*   [ ] Code section, registers, open files
*   [ ] Data section, stack, registers
*   [ ] program counter, registers, stack

### Question 2
**Explain the DIFFERENCE between the core dump and crash dump.** (3 Puan)

*   *(Metin kutusu boş, cevap girilmesi bekleniyor)*

### Question 3 (Theory)
**When using fork system call parent and child process have the same address space.** (3 Puan)

*   [x] **True** (İşaretli)
*   [ ] False

### Question 3 (Code Analysis)
**Considering the following code segment**
a) Draw the process tree. Each node should present the up to date values of the variables a,b and i
b) Provide the unordered list of print outs.
*(Dosya yükleme sorusu)*

*(Görseldeki kodun transkripsiyonu yapılamadı çünkü kod metni görselde tam olarak görünmüyor, ancak soru bir süreç ağacı (process tree) çizme sorusudur.)*

### Question 4 (Theory)
**Program code is stored in the text section of the program** (3 Puan)

*   [x] **True** (İşaretli)
*   [ ] False

### Question 4 (Problem)
**If 20% of an application can be executed in parallel, what will be the theoretical maximum speedup?** (3 Puan)

*   *(Metin kutusu boş, cevap girilmesi bekleniyor - Amdahl Yasası sorusu)*

### Question 4 (Code Output)
**Select the correct option for the outputs of the concurrent execution of process A and process B to be** (15 Puan)

**Initialization:**
```c
int x=2;
int y=3;
```

| Process A | Process B |
| :--- | :--- |
| `while (x==2) {do-nothing};` | `printf("C");` |
| `printf("S");` | `x=y*x;` |
| `y=x-y;` | `printf("E");` |
| `printf("3");` | `while (y==3) {do-nothing};` |
| `y=1;` | `printf("0");` |
| | `x=x-y;` |
| | `printf("3");` |

**Options:**
I. CS3E03
II. CES303
III. CSE303
IV. CE03S3
V. S3CE03

*   [x] **I, II, and III** (Seçili Cevap)

### Question 5 (Theory - Threads)
**Threads share the memory and the resources of the process by default** (4 Puan)

*   [x] **True** (İşaretli)
*   [ ] False

### Question 5 (Scheduler Problem)
**A doubling scheduler uses a prioritized round-robin scheduling policy. New processes are assigned an initial quantum of length R. Whenever a process uses its entire quantum without blocking, its new quantum is set to twice its current quantum (2*R). If a process blocks before its quantum expires, its new quantum is reset to R. For the purposes of this question, assume that every process requires a finite total amount of CPU time. If the scheduler gives higher priority to processes that have smaller quanta. Is starvation possible in this system? Explain briefly.** (10 Puan)

*   *(Metin kutusu boş, cevap girilmesi bekleniyor)*

### Question 6 (Theory)
**Binary semaphore equals to mutex variables.** (3 Puan)

*   [x] **True** (İşaretli)
*   [ ] False

### Question 6 (Banker's Algorithm)
**Consider the given snapshot of a system: Answer the following questions using the banker's algorithm:**

| | Allocation | Max | Available |
| :--- | :---: | :---: | :---: |
| | A B C D E | A B C D E | A B C D E |
| **P0** | 2 0 0 1 0 | 4 2 1 2 3 | 3 3 3 1 2 |
| **P1** | 0 1 2 1 1 | 3 2 5 2 3 | |
| **P2** | 2 1 0 3 1 | 2 3 1 7 3 | |
| **P3** | 1 3 1 0 2 | 1 4 2 4 4 | |
| **P4** | 1 4 3 2 2 | 3 6 6 5 4 | |

a) How many resources are there for each resource type in this system?
b) Is this system safe? If yes provide a safe sequence. If not explain why?
c) If a request from P4 arrives for (0,0,2,0,1) can the request be granted immediately? Explain why?
(20 Puan)

### Question 7 (Theory)
**In Direct Memory Access, one interrupt is generated per byte** (4 Puan)

*   [ ] True
*   [ ] False
*(Not: Görselde işaretli değil ancak doğru cevap False olmalıdır, blok başına bir interrupt üretilir.)*

### Question 7 (Scheduling)
**Considering the given processes and their CPU burst times, compare the FCFS and RR scheduling algorithms using the average waiting time metric. Quanta q=5.**

| Process | Burst Time |
| :---: | :---: |
| P1 | 10 |
| P2 | 29 |
| P3 | 3 |
| P4 | 7 |
| P5 | 12 |

(20 Puan)

### Question 8
**CPU registers have a faster access time than any other device including CPU cache memory.** (2 Puan)

*   [x] **True** (İşaretli)
*   [ ] False

### Question 9
**What are the main sections in the critical section problem?** (4 Puan)

*   **Cevap (Resim üzerindeki not):** entry section, critical section, exit section, remainder section

### Question 10
**In the case of concurrency, the execution of the threads will be interleaved over time** (3 Puan)

*   [x] **True** (İşaretli)

### Question 12
**The heap contains dynamically allocated memory during run time** (3 Puan)

*   [x] **True** (İşaretli)
*   [ ] False

### Question 13
**A trap or exception is a software-generated interrupt caused either by an error or a user request** (3 Puan)

*   [x] **True** (İşaretli)
*   [ ] False

### Question 14
**Give an example method for implicit threading?** (3 Puan)
*(Görselde soru üzerine eklenmiş notlar var)*

*   **Not:** Growing in popularity as numbers of threads increase, program correctness more difficult with explicit threads.
*   **Cevap (Resim üzerindeki not):** OpenMP, Thread Pools, Grand Central Dispatch

### Question 15
**Operations in Message Passing architecture is faster than shared memory architectures.** (4 Puan)

*   [ ] True
*   [x] **False** (İşaretli)

### Question 16
**The size of a character pointer equals the size of a double-pointer in C programming.**

*   [x] **True** (İşaretli)
*   [ ] False

### Question 17
**Ordinary pipes need a parent-child relationship.** (3 Puan)

*   [x] **True** (İşaretli)
*   [ ] False

### Question 18
**Write the pseudocode for Peterson's Solution for the critical section problem** (5 Puan)

*   **Cevap (Resim üzerindeki not):**
```c
do {
  flag[i] = true;
  turn = j;
  while (flag[j] && turn == j);
  critical section
  flag[i] = false;
  remainder section
} while (true);
```

### Question 19
**Synchronous threading does not involve data sharing among threads.** (2 Puan)

*   [ ] True
*   [x] **False** (İşaretli)

*   **Açıklama Notu (Resim üzerinde):** Parent thread creates one or more children and then must wait for all of its children to terminate before it resumes. Known as fork-join strategy. Threads can run concurrently but parent cannot continue until this work has been completed.

### Question 20
**Function parameters, return addresses, and local variables stored in a Hash data structure for processes** (3 Puan)

*   [ ] True
*   *(False seçeneği görünmüyor ama cevap False olmalı)*
*   **Düzeltme Notu (Resim üzerinde):** STACK

---

## BÖLÜM 2: Kod Analizi ve Klasik Sorular (Online Sınav Devamı)

### Problem: Semaphore Atomicity
**Question 3:** If the semaphore operations Wait and Signal are not executed atomically, then mutual exclusion may be violated. Assume that Wait and Signal are implemented as below: Lx represents the line numbers.

```c
L1 void Wait (Semaphore S) {
L2   while (S.count <= 0) {}
L3   S.count = S.count - 1;
L4 }
L5 void Signal (Semaphore S) {
L6   S.count = S.count + 1;
L7 }
```
Describe a scenario of context switches (CS) where two threads, T1 and T2, can both enter a critical section guarded by a single mutex semaphore as a result of a lack of atomicity. In your scenario clearly describe the initial value of the semaphore S, and sequence of operations using the line numbers Lx.
For example: `S=5; T1-L1:L3,CS,T2-L5,CS,T1-L4,CS,T2-L6:L7`...
(10 Puan)

### Problem: Readers-Writers
**Question 1:** Considering the readers and writers problem, assume that rules have changed. The rule "Multiple readers can read at the same time" is now changed to **"Up to 5 readers can read at the same time"**. Here is the pseudocode for the original readers-writers problem. Rewrite the code to meet the conditions for the new rule.

**Rules:**
1. Only one writer writes at a time.
2. While writing reading is not allowed.
3. While reading writing is not allowed.
4. (NEW) Up to 5 readers can read at the same time.

*(Sağ üstte orijinal kodun görseli verilmiş)*
(15 Puan)

### Problem: Process Forking (Agent_Smith.c)
**Question 2:** Here is the code for a program named `Agent_Smith.c`. Including the initial parent process,
A) How many Agent_Smith processes are created? Assume there are no errors.
B) Draw the process tree showing the up-to-date variables a and b.
(10 Puan)

```c
...
int main()
{
  pid_t smith;
  int a=2; int b=3;
  smith = fork();
  if (smith == 0) {
    fork();
    a++;
    fork(); /* BEWARE */
  }
  else if (smith > 0) {
    b++;
    fork(); /* BEWARE */
  }
  printf("%d %d", a,b);
}
```

---

## BÖLÜM 3: Kağıt Sınav Soruları (CSE 303 Midterm/Final)

Bu bölüm, kağıt üzerinde çekilmiş fotoğraflardaki soruları içerir. Bazı sorular online sınavdakilerle ortaktır.

### Q1. Definitions (8p)
Define the following terms and give an example;
a) Operating System:
   Example:
b) Pipeline:
   Example:

### Q2. True/False (13x2p = 26p)
Mark the following (T-F) questions using T for True, and F for False.

*   ( ) **Q2-1** A Multi-threaded process has two program counters per thread.
*   ( ) **Q2-2** The short-term scheduler controls the degree of multiprogramming.
*   ( ) **Q2-3** Interrupt-driven I/O provides better performance when moving large amounts of data than DMA.
*   ( ) **Q2-4** `$@` symbol in a Makefile represents the left side of the : symbol.
*   ( ) **Q2-5** A child process can only be an orphan process while its parent can be either orphan or a zombie process.
*   ( ) **Q2-6** There must be a space character in the beginning of any command in a Makefile.
*   ( ) **Q2-7** When using fork system call parent and child process have the same address space.
*   ( ) **Q2-8** With NUMA, some parts of memory may take longer to access than other parts.
*   ( ) **Q2-9** CD-R and DVD-R are examples for WORM devices.
*   ( ) **Q2-10** CPU registers has faster access time than any other device including CPU cache memory.
*   ( ) **Q2-11** Privilege escalation allows user to change file access permissions.
*   ( ) **Q2-12** Emulation used when source CPU type different from target type.
*   ( ) **Q2-13** Operations in Message Passing architecture is faster than shared memory architectures.

### Q3. Fill in the Blanks (12x2p = 24p)
Fill in the blanks with appropriate terms.

a) A .................... or .................... is a software-generated interrupt caused either by an error or a user request.
b) When an interrupt occurs, the operating system preserves the state of the CPU by storing every .................... and the ....................
c) The .................... instruction moves a byte or word from main memory to an internal register within the CPU while the .................... instruction moves the content of a register to main memory.
d) The sequence of steps that the CPU follows to process instructions is called as .................... Cycle.
e) In a multiprocessor environment all CPUs must have the most recent value in their cache which is known as ....................
f) In the context of Cloud Computing, Google Docs is an example for ....................
g) One of the example Shell program in a Linux/UNIX systems is ....................
h) One method for system call parameter passing is to use ....................
i) PID value of 1 is assigned to the .................... process on Linux Systems.

### Q4. (3p)
Explain the difference between the core dump and crush dump.

### Q5. (4p)
Write the manual compilation and linking steps to compile a project having `main.c`, `plib.c`, and `plib.h` files into `main.exe` using gcc compiler.

### Q6. (6p)
Draw a directed graph representing the process state model. Name all edges and vertices.

### Q7. (10p)
Considering the following code (Assume that fork does not fail.):

```c
int main() {
  fork();
  if(fork())
    printf(" A ");
  else
    fork();
  printf(" B ");
  wait(NULL);
  return(0);
}
```
a. How many times "A" is printed on the screen.
b. How many times "B" is printed on the screen.
c. Draw the process tree shown parent (Pa) and child (Cu) processes where n is the ID of the process.

### Q8. (9p)
What could the output of the concurrent execution of process A and process B be? (State **all possible** outputs)

**Initialization of shared variables:**
`int x=2;`
`int y=0;`

| Process A | Process B |
| :--- | :--- |
| `while (x==2) {do-nothing};` | `printf("L");` |
| `printf("E");` | `x=1;` |
| `y=1;` | `while (y==0) {do-nothing};` |
| `y=0;` | `printf("A");` |
| `printf("M");` | |
| `y=1;` | |

### Q9. (10p)
Consider P1 and P2 processes that require T1 to happen before T2. Show a semaphore based solution to this problem using the semaphore variable S.

### Q9. (10p) (Pizza Factory Problem)
Consider a pizza factory in Yakut Bazaar that has *10 pizza plates*. The producer is the delivery person and the consumer is the student. Initially the factory has *3 pizza ready to serve*. We use the following three semaphores:
`semaphore mutex;`
`semaphore fullBuffer;` /* Number of filled plates */
`semaphore emptyBuffer;` /* Number of empty plates */

Given functions (see code) `delivery_person()` and `student()`:
a) What will be the initial values of the semaphores?
b) Write a solution that guarantees mutual exclusion and no deadlocks. (Part of the code for student() function has been written)

**Initial Values:**
`#define NUM_PLATES` ....... (Write correct numbers here)
`semaphore mutex` = .......
`semaphore fullBuffer` = .......
`semaphore emptyBuffer` = .......

**Code:**
```c
delivery_person() {               student() {
  .......................
  .......................           .......................
  .......................           .......................
  .......................           wait(fullBuffer);
  put_a_pizza();                    wait(mutex);
  .......................           take_a_pizza();
  .......................           .......................
  .......................           .......................
  .......................           .......................
}                                 }
```

### Bonus +5p
What is the latest official versions of the following software:
a. Linux kernel version : ....................
b. Android OS version : ....................
c. Windows OS version : ....................
d. gcc version : ....................
e. JDK version : ....................

---

## BÖLÜM 4: Başka Bir Sınav Kağıdı (Farklı Sorular)

### Q1. (6x2p)
Mark the following (T-F) questions using T for True, and F for False into the given table above.
*   **Q1-1** ( ) In a system with only one instance of each resource type, the presence of a cycle in the wait for graph is sufficient to detect a deadlock.
*   **Q1-2** ( ) There are solutions to synchronization problems that can be implemented using mutexes, but cannot be implemented using semaphores.
*   **Q1-3** ( ) The convoy effect is a result of using SJF scheduling algorithm.
*   **Q1-4** ( ) FCFS is a non-preemptive scheduling algorithm.
*   **Q1-5** ( ) In multithreaded programming each thread has its own registers, stack and code segment.
*   **Q1-6** ( ) A very large quantum (q) value in RR scheduling is equivalent to FIFO.

### Q2. (20p)
An operating system uses a preemptive scheduler algorithm. For the values given table below;

| Proc. ID | Submission Time | Required Computing Time |
| :---: | :---: | :---: |
| A | 0 | 4 |
| B | 1 | 6 |
| C | 3 | 3 |
| D | 4 | 1 |

(a) Assuming a scheduling quantum of 2 time units and the running process is preempted only if its quantum expires or it receives its required computing time and exits. What is the response time and turnaround time for each process?

(b) Assuming that the scheduler is non-preemptive and uses the shortest-job-first (SJF) heuristic, What is response time and turnaround time for each process?

### Q3. (12p) (Doubling Scheduler)
*(Bu soru yukarıdaki Online Sınav'daki Question 5 ile aynı metne sahiptir)*
(a) ... Is starvation possible in this system...
(b) Suppose instead that the scheduler gives higher priority to processes that have smaller quanta. Is starvation possible in this system? Explain briefly.

### Q4. (15p)
Considering the following C code;
a. Draw the process tree showing the up to date variable values.
b. What would be a possible output?
c. How many lines are printed on the screen (Assume that fork does not fail.)

```c
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
int main(void)
{
  int a,b;
  a=1;
  b=a+1;
  while(a<4) {
    if(fork())
      b++;
    else
      a++;
    a+=1;
  }
  printf("%d %d\n", a, b);
  return(0);
}
```
*Görsel üzerinde el yazısı ile notlar: b) 3,2 c) 2,3 3,4*

---
```
