Aşağıda, gönderdiğiniz görsellerde yer alan tüm sınav soruları, el yazısı çözümler ve ders notları tek bir düzenli Markdown dosyasında derlenmiştir.

İçerik **Final Sınavı Soruları** (ekran görüntülerinden alınanlar) ve **Ders Çalışma Notları/Vize Konuları** (el yazısı kağıtları) olarak iki ana başlıkta toplanmıştır.

***

# OPSIS (Operating Systems) Sınav ve Ders Notları Dokümanı

## BÖLÜM 1: Final Sınavı Soruları ve Çözümleri

Bu bölümdeki sorular, bilgisayar ekran görüntüsü olan görsellerden (Questions 1-7) alınmış ve ilgili el yazısı çözümlerle eşleştirilmiştir.

---

### Soru 1: Readers-Writers Problem (Modified)

**Soru Metni:**
Considering the readers and writers problem, assume that rules have changed. The rule "Multiple readers can read at the same time" is now changed to "Up to 5 readers can read at the same time". Here is the pseudocode for the original readers-writers problem. Rewrite the code to meet the conditions for the new rule.

*Rules:*
1. Only one writer writes at a time.
2. While writing, reading is not allowed.
3. While reading, writing is not allowed.
4. (NEW) Up to 5 readers can read at the same time.

**Orijinal Kod (Referans):**
*   `wait(mutex)`, `readcount++`, `if readcount == 1 then wait(wrt)`, `signal(mutex)` ... (Standart yapı)

**El Yazısı Çözüm (Answer):**
Öğrencinin notlarında bu kural değişikliğine (maksimum 5 okuyucu) dair çözüm mantığı şu şekildedir:

Okuyucu (Reader) süreci için, okuma izni almadan önce halihazırda içeride kaç okuyucu olduğunu kontrol etmemiz gerekir.

```c
// Reader Process Değişikliği

wait(mutex);
readcount = readcount + 1;

if (readcount == 1) {
    wait(wrt); // İlk okuyucu ise yazarı blokla
}

// YENİ KURAL KISMI:
// Okuyucu sayısı 5'ten fazlaysa veya limiti aşıyorsa kontrol mekanizması.
// Not: El yazısı çözümde tam kod bloğu şu mantıkla kurgulanmış:
// "if readcount <= 5" kontrolü eklenerek işlem yapılıyor.

if (readcount <= 5) {
    signal(mutex);
    // ... reading is performed ...
    wait(mutex);
    readcount = readcount - 1;
    if (readcount == 0) {
        signal(wrt); // Son okuyucu çıktıysa yazara izin ver
    }
    signal(mutex);
} else {
    // Okuyucu limiti doluysa, readcount'u geri al ve bekle (veya reddet)
    readcount = readcount - 1;
    signal(mutex);
    // wait_loop or retry logic
}
```
*(Not: Tam bir "Counting Semaphore" çözümü daha temiz olurdu ancak el yazısında `readcount <= 5` mantığı vurgulanmış.)*

---

### Soru 2: Agent_Smith.c (Process Creation)

**Soru Metni:**
Here is the code for a program named Agent_Smith.c. Including the initial parent process:
A) How many Agent_Smith processes are created? Assume there are no errors.
B) Draw the process tree showing the up-to-date variables a and b.

**Kod:**
```c
int main() {
    pid_t smith;
    int a = 2; int b = 3;
    smith = fork();
    if (smith == 0) { // Child Process
        fork();
        a++;
        fork(); /* BEWARE */
    }
    else if (smith > 0) { // Parent Process
        b++;
        fork(); /* BEWARE */
    }
    printf("%d %d", a, b);
}
```

**El Yazısı Çözüm:**

**A) Oluşan Süreç Sayısı:**
`fork()` çağrılarını takip ettiğimizde:
1. İlk `fork()`: 1 Parent, 1 Child (Toplam 2)
2. `if (smith == 0)` bloğu (Child):
    *   Bir `fork()` daha -> Child ikiye bölünür.
    *   Sonra `fork()` daha -> Mevcut childlar tekrar bölünür.
3. `else if (smith > 0)` bloğu (Parent):
    *   Bir `fork()` daha -> Parent ikiye bölünür.

**B) Process Tree (Ağaç Yapısı):**

El yazısı notlardaki ağaç yapısının metin hali:

*   **Kök (P0):** (Başlangıç: a=2, b=3)
    *   **Sol Dal (Child - P1):** `smith == 0` bloğuna girer.
        *   `fork()` çalışır -> **P2** oluşur.
        *   `a++` çalışır -> P1 ve P2'de `a=3` olur. `b=3` kalır.
        *   `fork()` çalışır -> P1'den **P3**, P2'den **P4** doğar.
        *   Çıktı verenler: P1, P2, P3, P4 (Hepsi `a=3, b=3` basar).
    *   **Sağ Dal (Parent - P5):** `smith > 0` bloğuna girer.
        *   `b++` çalışır -> `b=4` olur. `a=2` kalır.
        *   `fork()` çalışır -> **P6** doğar.
        *   Çıktı verenler: P5, P6 (Hepsi `a=2, b=4` basar).

---

### Soru 3: Non-Atomic Semaphore Operations

**Soru Metni:**
If the semaphore operations Wait and Signal are not executed atomically, then mutual exclusion may be violated. Assume that Wait and Signal are implemented as below (with line numbers Lx):

```c
L1 void Wait (Semaphore S) {
L2    while (S.count <= 0) {};
L3    S.count = S.count - 1;
L4 }

L5 void Signal (Semaphore S) {
L6    S.count = S.count + 1;
L7 }
```
Describe a scenario of context switches (CS) where two threads, T1 and T2, can both enter a critical section...

**El Yazısı Çözüm:**

**Senaryo:** `S` semaforunun başlangıç değeri `1` olsun (Mutex).
Amaç: İki sürecin de `S.count` değerini azaltıp (0 ve -1 gibi) Critical Section'a girmesini sağlamak.

**Adımlar (Trace):**
1.  **S = 1**
2.  **T1 çalışır:**
    *   L2: `while(1 <= 0)` kontrolünü geçer (döngüye girmez).
    *   **CONTEXT SWITCH (CS)** gerçekleşir. (T1, L3 satırında durdu, henüz azaltmadı).
3.  **T2 çalışır:**
    *   L2: `while(1 <= 0)` kontrolünü geçer (S hala 1 olduğu için).
    *   L3: `S.count = 1 - 1 = 0`.
    *   **T2 Critical Section'a girer.**
    *   **CONTEXT SWITCH (CS)** gerçekleşir.
4.  **T1 devam eder:**
    *   Kaldığı yer L3: `S.count = S.count - 1`. (Hafızada S=0 olmuştu ama registerda eski değeri tutuyor olabilir veya doğrudan hafızadan okuyup 0'ı -1 yapar).
    *   T1 de döngüden çıktığı için Critical Section'a girer.

**Sonuç:** Hem T1 hem T2 aynı anda Critical Section'dadır. Mutual Exclusion ihlal edilmiştir.

---

### Soru 4: Concurrent Execution (Process A & B)

**Soru Metni:**
Select the correct option for the outputs of the concurrent execution of Process A and Process B.

**Initialization:** `int x=2; int y=3;`

| Process A | Process B |
| :--- | :--- |
| `while(x==2) {do-nothing};` | `printf("C");` |
| `printf("S");` | `x = y * x;` |
| `y = x - y;` | `printf("E");` |
| `printf("3");` | `while(y==3) {do-nothing};` |
| `y = 1;` | `printf("0");` |
| | `x = x - y;` |
| | `printf("3");` |

**Seçenekler:** I. CS3E03, II. CES303, III. CSE303, IV. CE03S3 ...

**El Yazısı Analiz:**
1.  **Başlangıç:** `x=2`, `y=3`.
2.  **Process A:** `while(x==2)` döngüsünde takılı kalır. İlerleyemez.
3.  **Process B:** Mecburen başlamalı.
    *   `printf("C")` -> Ekrana **C** basıldı.
    *   `x = y * x` -> `x = 3 * 2 = 6`. (Artık x 2 değil, A süreci serbest kaldı).
4.  **Kritik Ayrım:** Buradan sonra Process A döngüden çıkabilir veya Process B devam edebilir.
    *   **Yol 1 (Process B devam ederse):**
        *   `printf("E")` -> **CE**...
        *   `while(y==3)` -> y hala 3, Process B burada takılır.
        *   Mecburen Process A çalışmalı.
        *   A: `printf("S")` -> **CES**...
        *   A: `y = x - y` -> `y = 6 - 3 = 3`. (y değişmedi, B hala takılı).
        *   A: `printf("3")` -> **CES3**...
        *   A: `y = 1` -> y artık 1. Process B serbest kalır.
        *   B: `printf("0")` -> **CES30**...
        *   B: `x = x - y` -> x = 6 - 1 = 5.
        *   B: `printf("3")` -> **CES303**.
    *   **Yol 2 (Process A araya girerse):**
        *   B, `x=6` yaptıktan hemen sonra A çalışırsa:
        *   A: `printf("S")` -> **CS**...
        *   A: `y = x - y` -> `y = 3`.
        *   A: `printf("3")` -> **CS3**...
        *   A: `y = 1`.
        *   B: `printf("E")` -> **CS3E**...
        *   B: `while(y==3)` -> y=1 olduğu için takılmaz.
        *   B: `printf("0")` -> **CS3E0**...
        *   B: `printf("3")` -> **CS3E03**.

**Doğru Cevap:** I (CS3E03) ve II (CES303) olasıdır. Görseldeki el yazısı notta **I, II and III** seçeneği işaretlenmiştir.

---

### Soru 5: Doubling Scheduler (Round Robin Variation)

**Soru Metni:**
A doubling scheduler uses a prioritized round-robin scheduling policy.
*   New processes: Initial quantum `R`.
*   Quantum used fully without blocking: New quantum = `2*R` (Doubled).
*   Process blocks before quantum expires: New quantum = `R` (Reset).
*   **Priority:** Higher priority to processes with **smaller quanta**.

**Soru:** Is starvation possible in this system? Explain briefly.

**El Yazısı Çözüm:**
**Cevap: Evet, açlık (starvation) mümkündür.**

**Açıklama:**
Sisteme sürekli olarak yeni I/O ağırlıklı (I/O bound) süreçler gelirse veya mevcut süreçler bloklanıp kuantumlarını sürekli `R` seviyesinde tutarlarsa (küçük kuantum = yüksek öncelik), CPU ağırlıklı (CPU bound) ve kuantumu büyümüş (`2R`, `4R`...) bir süreç asla sıra bulamayabilir.
Yani; `R` kuantumlu işler önceliklidir. Kuantumu `2R` olan bir iş, sürekli gelen `R` kuantumlu işlerin arkasında sonsuza kadar bekleyebilir.

---

### Soru 6: Banker's Algorithm (Deadlock Avoidance)

**Soru Metni:**
Consider the given snapshot.
**Allocation** | **Max** | **Available**
P0: 2 0 0 1 0 | 4 2 1 2 3 | 3 3 3 1 2
P1: 0 1 2 1 1 | 3 2 5 2 3 |
P2: 2 1 0 3 1 | 2 3 1 7 3 |
P3: 1 3 1 0 2 | 1 4 2 4 4 |
P4: 1 4 3 2 2 | 3 6 6 5 4 |

a) How many resources are there for each resource type in this system?
b) Is this system safe? If yes, provide a safe sequence.
c) If a request from P4 arrives for (0,0,2,0,1) can be granted immediately?

**El Yazısı Çözüm:**

**a) Toplam Kaynak Sayısı:**
Toplam = (Tüm Allocation Toplamı) + Available
*   Allocation Toplamı (Sütun sütun topla):
    *   A: 2+0+2+1+1 = 6
    *   B: 0+1+1+3+4 = 9
    *   C: 0+2+0+1+3 = 6
    *   D: 1+1+3+0+2 = 7
    *   E: 0+1+1+2+2 = 6
*   Available: 3 3 3 1 2
*   **Toplam Kaynaklar:**
    *   A: 6+3 = 9
    *   B: 9+3 = 12
    *   C: 6+3 = 9
    *   D: 7+1 = 8
    *   E: 6+2 = 8
    *   **Sonuç:** A=9, B=12, C=9, D=8, E=8.

**b) Sistem Güvenli mi?**
Önce **Need** Matrisini hesaplayalım (Need = Max - Allocation):
*   P0: (4 2 1 2 3) - (2 0 0 1 0) = **2 2 1 1 3**
*   P1: (3 2 5 2 3) - (0 1 2 1 1) = **3 1 3 1 2**
*   P2: (2 3 1 7 3) - (2 1 0 3 1) = **0 2 1 4 2**
*   P3: (1 4 2 4 4) - (1 3 1 0 2) = **0 1 1 4 2**
*   P4: (3 6 6 5 4) - (1 4 3 2 2) = **2 2 3 3 2**

**Algoritma Adımları (Available: 3 3 3 1 2):**
1.  **P0:** Need (2 2 1 1 3) <= Avail (3 3 3 1 2)? E (3) > Avail E (2) olduğu için **HAYIR**.
2.  **P1:** Need (3 1 3 1 2) <= Avail? A (3) <= 3, B(1)<=3... E(2)<=2. **EVET**.
    *   P1 çalışır. Kaynakları bırakır.
    *   Yeni Avail = (3 3 3 1 2) + P1 Alloc (0 1 2 1 1) = **3 4 5 2 3**.
3.  **P0:** Need (2 2 1 1 3) <= Avail (3 4 5 2 3)? **EVET**.
    *   P0 çalışır.
    *   Yeni Avail = (3 4 5 2 3) + P0 Alloc (2 0 0 1 0) = **5 4 5 3 3**.
4.  **P4:** Need (2 2 3 3 2) <= Avail (5 4 5 3 3)? **EVET**.
    *   P4 çalışır.
    *   Yeni Avail = (5 4 5 3 3) + P4 Alloc (1 4 3 2 2) = **6 8 8 5 5**.
5.  **P2:** Need (0 2 1 4 2) <= Avail? **EVET**.
    *   P2 çalışır.
6.  **P3:** Need (0 1 1 4 2) <= Avail? **EVET**.
    *   P3 çalışır.

**Safe Sequence:** `<P1, P0, P4, P2, P3>` (Notlarda bulunan sıra).
Cevap: Evet, sistem güvenlidir.

**c) P4 İsteği (0,0,2,0,1):**
1.  Request <= Need? (0,0,2,0,1) <= (2,2,3,3,2) -> EVET.
2.  Request <= Available? (0,0,2,0,1) <= (3,3,3,1,2) -> EVET.
3.  Sanal olarak tahsis et ve güvenliği tekrar kontrol et.
    *   Notlarda bu kısım için "Yes, it can easily granted" notu düşülmüş, çünkü Available kaynaklar çok genişliyor.

---

### Soru 7: CPU Scheduling (FCFS vs RR)

**Soru Metni:**
Processes:
*   P1: Burst 10
*   P2: Burst 29
*   P3: Burst 3
*   P4: Burst 7
*   P5: Burst 12
Compare FCFS and RR (q=5) using average waiting time metric.

**El Yazısı Çözüm:**

**1. FCFS (First Come First Served):**
*   Gantt Şeması Sırası: [P1] -> [P2] -> [P3] -> [P4] -> [P5]
*   Bitiş Zamanları (Tahmini):
    *   P1: 0-10 (Bekleme: 0)
    *   P2: 10-39 (Bekleme: 10)
    *   P3: 39-42 (Bekleme: 39)
    *   P4: 42-49 (Bekleme: 42)
    *   P5: 49-61 (Bekleme: 49)
*   Ortalama Bekleme: (0 + 10 + 39 + 42 + 49) / 5 = **28 ms**.

**2. Round Robin (q=5):**
Süreçler parçalanır:
*   P1 (10) -> P1(5), P1(5)
*   P2 (29) -> P2(5), P2(5), P2(5), P2(5), P2(5), P2(4)
*   P3 (3) -> P3(3) (Biter)
*   P4 (7) -> P4(5), P4(2)
*   P5 (12) -> P5(5), P5(5), P5(2)

**El Yazısındaki Hesaplamalar:**
*   FCFS Average Waiting Time: **28 ms**
*   SJF (Shortest Job First - *Ekstra çözülmüş*): **13 ms**
*   RR (Round Robin): El yazısında Gantt şeması çizilmiş ve waiting time hesaplanmış ancak nihai ortalama değer net okunmuyor. Genellikle RR, uzun proseslerin (P2 gibi) bekleme süresini artırırken kısa proseslerin tepki süresini iyileştirir. Notta P1->0, P2->?, P3->? şeklinde bekleme süreleri toplanıp 5'e bölünmüş.

---

## BÖLÜM 2: Ders Notları ve Diğer Konular

Bu bölüm, el yazısı kağıtlarında bulunan tanımları ve vize/final hazırlık notlarını içerir.

### 1. Effective Access Time (EAT) Hesaplaması
*Soru:* TLB Hit ratio = 90% ($\alpha = 0.9$). TLB search time ($\epsilon$) = 10ns. Memory access ($m$) = 250ns.
*Formül:* $EAT = \alpha \times (\epsilon + m) + (1-\alpha) \times (\epsilon + m + m)$
*   Hit durumunda: TLB'de bul (10) + Veriyi al (250) = 260.
*   Miss durumunda: TLB'de ara (10) + Page Table'a git (250) + Veriyi al (250) = 510.
*   Hesap: $0.9 \times 260 + 0.1 \times 510 = 234 + 51 = 285 \text{ ns}$.

### 2. Kısa Tanımlar (Midterm Notes)
*   **Operating System:** Kullanıcı ve donanım arasında aracı (intermediary) olan yazılım. Kaynakları yönetir (Resource allocator).
*   **Pipeline:** Unix sistemlerde süreçler arası iletişim (IPC) mekanizması. Bir sürecin çıktısı diğerinin girdisi olur (`|` sembolü). Örn: `ls | grep .txt`.
*   **Trap vs Exception:** Yazılım kaynaklı interrupt (hata veya kullanıcı isteği).
*   **Interrupt-driven I/O:** DMA'dan farklı olarak CPU her byte transferinde müdahil olur, daha yavaştır (büyük veriler için).
*   **Context Switch:** CPU'nun bir süreçten diğerine geçerken mevcut durumunu (registerlar, PC) kaydedip yeni sürecin durumunu yüklemesi.
*   **Core Dump:** Bellek erişim hatası (segmentation fault) sonucu oluşan bellek dökümü. Debug için kullanılır.
*   **Crash Dump:** Kernel çöktüğünde tüm RAM içeriğinin diske yazılması.
*   **Derleme Adımları (Compilation Steps):**
    1.  Preprocessing (.c -> .i)
    2.  Compiling (.i -> .s)
    3.  Assembling (.s -> .o)
    4.  Linking (.o -> .exe)

### 3. Process State Diagram (Süreç Durumları)
Döngü şu şekildedir:
*   **New** -> (admitted) -> **Ready**
*   **Ready** -> (scheduler dispatch) -> **Running**
*   **Running** -> (interrupt) -> **Ready**
*   **Running** -> (I/O wait) -> **Waiting**
*   **Waiting** -> (I/O completion) -> **Ready**
*   **Running** -> (exit) -> **Terminated**

### 4. Boşluk Doldurma Soruları (Fill in the blanks)
1.  **Trap** or exception is a software-generated interrupt.
2.  OS preserves the state of CPU by storing **registers**.
3.  **Data** instruction moves a byte from main memory to register, **Load** instruction moves...
4.  Steps CPU follows: **Instruction Cycle**.
5.  Multiprocessor cache consistency: **Cache Coherence**.
6.  Cloud computing example: **SaaS** (Google Docs).
7.  Shell program example: **Bash**.
8.  Parameter passing method: **Registers**.
9.  PID 1 is assigned to: **Init** process.

---

**Not:** Bu doküman, sağladığınız görsellerdeki tüm okunabilir metinlerin, kodların ve şemaların dijital ortamda düzenlenmiş halidir. Sınav soruları ve çalışma notları eksiksiz aktarılmaya çalışılmıştır.
