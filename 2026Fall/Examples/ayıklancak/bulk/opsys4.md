Aşağıda, sağladığınız PDF içerikleri, ekran görüntüleri ve metinlerin tamamının derlenmiş, düzenlenmiş ve Markdown formatına getirilmiş hali bulunmaktadır. Görsel içeren kısımlar (grafikler, ağaçlar) metin tabanlı olarak betimlenmiştir.

---

# İşletim Sistemleri Final Sınavı Çalışma Dokümanı

Bu doküman, University of Rochester (CSC2/456), CS3331 (Spring 2014) sınav çözümleri ve Akdeniz Üniversitesi el yazısı notları/online sınav sorularının derlemesini içerir.

---

## BÖLÜM 1: Karışık Test ve Klasik Sorular (Akdeniz Üniversitesi & Notlar)

### 1. Doğru / Yanlış Soruları

Aşağıdaki ifadeler tablodaki cevaplara göre işaretlenmiştir:

| Soru Kodu | İfade | Cevap | Açıklama |
| :--- | :--- | :--- | :--- |
| **Q1-1** | Sadece her kaynak türünden bir örneğin bulunduğu bir sistemde, bekleme grafiğinde (wait-for graph) bir döngünün varlığı "deadlock" (kilitlenme) tespiti için yeterlidir. | **True (Doğru)** | Tek instance (örnek) varsa döngü kesinlikle deadlock demektir. |
| **Q1-2** | Mutex kullanılarak çözülebilen ancak Semaphore kullanılarak çözülemeyen senkronizasyon problemleri vardır. | **True (Doğru)** | Mutex'ler sahiplik (ownership) ve priority inheritance gibi özelliklere sahiptir, semaforlarda bu yoktur. |
| **Q1-3** | "Convoy effect" (Konvoy etkisi), SJF (Shortest Job First) çizelgeleme algoritmasının bir sonucudur. | **False (Yanlış)** | Konvoy etkisi FCFS (First-Come, First-Served) algoritmasında, uzun bir işlemin CPU'yu tutmasıyla oluşur. |
| **Q1-4** | FCFS (First-Come, First-Served) kesintisiz (non-preemptive) bir çizelgeleme algoritmasıdır. | **True (Doğru)** | Bir işlem CPU'yu aldığında bitene veya bloke olana kadar bırakmaz. |
| **Q1-5** | Çok iş parçacıklı (multithreaded) programlamada her thread'in kendi kayıtçıları (registers), yığını (stack) ve kod segmenti vardır. | **False (Yanlış)** | Her thread'in kendi register ve stack'i vardır ancak **kod segmentini** (ve heap'i) paylaşırlar. |
| **Q1-6** | Round Robin (RR) çizelgelemesinde çok büyük bir kuantum (q) değeri kullanmak FIFO (FCFS) ile eşdeğerdir. | **True (Doğru)** | Kuantum süresi işlem süresinden büyükse kesme olmaz, işlem sırayla biter. |

---

### 2. Çizelgeleme (Scheduling) - Doubling Quantum
**Soru:** Bir işlemci öncelikli Round Robin politikası kullanıyor. Yeni işlemler $R$ uzunluğunda başlangıç kuantumu alır. Bir işlem bloke olmadan tüm kuantumunu kullanırsa, yeni kuantumu iki katına ($2*R$) çıkar. Kuantum bitmeden bloke olursa, kuantum tekrar $R$'ye düşer.

**a) Scheduler daha büyük kuantuma sahip işlemlere yüksek öncelik verirse starvation (açlık) olur mu?**
*   **Cevap:** Hayır, starvation mümkün değildir. İşlemlerin sonlu olduğu varsayıldığından, CPU bound (yüksek kuantumlu) işlemler eninde sonunda biter. Onlar bitince düşük öncelikli (I/O bound) işlemler çalışır.

**b) Scheduler daha küçük kuantuma sahip işlemlere yüksek öncelik verirse starvation olur mu?**
*   **Cevap:** Evet, starvation mümkündür. Sisteme sürekli yeni I/O bound (küçük kuantumlu) işlemler gelirse, kuantumu büyümüş olan CPU bound işlem (düşük öncelikli hale geldiği için) asla sıra bulamayabilir.

---

### 3. Semafor Yarış Durumu (Race Condition) Analizi
**Soru:** `Wait` ve `Signal` operasyonlarının atomik olmadığı varsayılsın. Aşağıdaki kod satırları veriliyor:

*   L1: `void Wait(Semaphore S) {`
*   L2: `  while (S.count <= 0) {};`
*   L3: `  S.count = S.count - 1;`
*   L4: `}`
*   L5: `void Signal(Semaphore S) {`
*   L6: `  S.count = S.count + 1;`
*   L7: `}`

**Senaryo:** İki thread (T1 ve T2) aynı anda kritik bölgeye (CS) girerek "Mutual Exclusion" kuralını ihlal etsin.
**Başlangıç:** $S=1$

**Çözüm Senaryosu:**
`S=1; T1-L1:L2, CS, T2-L1:L4, CS, T1-L3:L4`

1.  **T1** çalışır (L1, L2): `S.count` (1) > 0 olduğunu görür, döngüye girmez.
2.  **Context Switch (CS)** olur.
3.  **T2** çalışır (L1, L2): `S.count` hala 1'dir. Döngüye girmez.
4.  **T2** devam eder (L3): `S.count`'u 0 yapar.
5.  **T2** (L4): Kritik bölgeye girer.
6.  **Context Switch (CS)** olur.
7.  **T1** kaldığı yerden devam eder (L3): `S.count` (şu an 0) değerini bir azaltır (-1 yapar). (Çünkü daha önce while döngüsünü geçmişti).
8.  **T1** (L4): Kritik bölgeye girer.
*   **Sonuç:** Hem T1 hem T2 kritik bölgededir.

---

### 4. Pizza Fabrikası (Producer-Consumer Problemi)
**Soru:** 10 adet pizza tabağı var.
*   `mutex = 1`
*   `fullBuffer = 3` (Başlangıçta dolu tabak sayısı)
*   `emptyBuffer = 7` (Başlangıçta boş tabak sayısı)

**Student (Tüketici) Kodu:**
```c
student() {
    wait(fullBuffer);    // Dolu tabak var mı bekle (en az 1 tane olmalı)
    wait(mutex);         // Pizza verisine erişmek için kilitle
    take_a_pizza();      // Pizzayı al
    post(mutex);         // Kilidi aç
    post(emptyBuffer);   // Boş tabak sayısını 1 artır
}
```

**Delivery Person (Üretici) Kodu:**
```c
delivery_person() {
    wait(emptyBuffer);   // Boş yer var mı bekle (en az 1 tane olmalı)
    wait(mutex);         // Pizza verisine erişmek için kilitle
    put_a_pizza();       // Pizzayı koy
    post(mutex);         // Kilidi aç
    post(fullBuffer);    // Dolu tabak sayısını 1 artır
}
```

---

### 5. C Programlama ve Fork() İşlemi

**Soru A: Agent_Smith Kodu**
```c
int main() {
    pid_t smith;
    int a=2; int b=3;
    smith = fork();
    if (smith == 0) { // Çocuk süreç
        fork();
        a++;
        fork(); /* BEWARE */
    }
    else if (smith > 0) { // Ebeveyn süreç
        b++;
        fork(); /* BEWARE */
    }
    printf("%d %d", a, b);
}
```
*Not: Bu soruda süreç ağacı (process tree) çizilmesi istenmiştir. Fork her çağrıldığında süreç sayısı 2 katına çıkar (kabaca). Kodun dallanması şöyledir:*
1.  İlk `fork`: P (Parent) ve C1 (Child) oluşur.
2.  C1 bloğu: `fork` (C1 -> C2), `a` artar, tekrar `fork` (C1->C3 ve C2->C4).
3.  P bloğu: `b` artar, `fork` (P -> C5).
*   **Toplam Süreç Sayısı:** 6 adet süreç oluşur.

**Soru B: While Döngüsü Kodu**
```c
int main(void) {
    int a, b;
    a=1; b=a+1; // b=2
    while(a < 4) {
        if(fork()) // Parent buraya girer (fork 0 olmayan döndürür)
            b++;
        else       // Child buraya girer (fork 0 döndürür)
            a++;
        a+=1;      // Her ikisi de a'yı artırır
    }
    printf("%d %d\n", a, b);
    return(0);
}
```
*   **Analiz:**
    *   Başlangıç: `a=1`. `while(1<4)` -> True. `fork()` yapıldı.
    *   **Parent:** `b` artar (`b=3`). `a+=1` (`a=2`). Döngü başa döner.
    *   **Child:** `a` artar (`a=2`). `a+=1` (`a=3`). Döngü başa döner.
    *   Bu şekilde `a < 4` olduğu sürece dallanma devam eder.
    *   **Olası Çıktı:** `3, 2` (Şıklardan biri).

---

### 6. Effective Access Time (EAT) Hesabı
**Verilenler:**
*   Hit Ratio ($\alpha$): %90 (0.9)
*   TLB Search ($\epsilon$): 10ns
*   Memory Access ($m$): 250ns

**Formül:**
$EAT = \alpha \times (\epsilon + m) + (1 - \alpha) \times (\epsilon + m + m)$
*(Hit durumunda: TLB + Memory. Miss durumunda: TLB + Memory(Page Table) + Memory(Data))*

**Hesaplama:**
1.  **Hit:** $0.9 \times (10 + 250) = 0.9 \times 260 = 234$ ns
2.  **Miss:** $0.1 \times (10 + 250 + 250) = 0.1 \times 510 = 51$ ns
3.  **Toplam:** $234 + 51 = 285$ ns

**Cevap:** 285 ns

---

### 7. Scheduling Algoritmaları Karşılaştırması (Gantt Şeması Sorusu)
**Verilen İşlemler:**
*   P1: 10ms, P2: 29ms, P3: 3ms, P4: 7ms, P5: 12ms.
*   Quantum ($q$) = 5ms.

**Ortalama Bekleme Süresi (Average Waiting Time) Hesabı:**

1.  **FCFS (First Come First Served):**
    *   Sıra: P1, P2, P3, P4, P5
    *   P1 Bekleme: 0
    *   P2 Bekleme: 10
    *   P3 Bekleme: 39
    *   P4 Bekleme: 42
    *   P5 Bekleme: 49
    *   Ortalama: $(0+10+39+42+49)/5 = 28$ ms

2.  **SJF (Shortest Job First - Non-preemptive):**
    *   Sıralama (Küçükten büyüğe): P3(3), P4(7), P1(10), P5(12), P2(29).
    *   Gantt: [P3: 0-3] [P4: 3-10] [P1: 10-20] [P5: 20-32] [P2: 32-61]
    *   Beklemeler: P3=0, P4=3, P1=10, P5=20, P2=32.
    *   Ortalama: $(0+3+10+20+32)/5 = 13$ ms

3.  **Round Robin (q=5):**
    *   İşleyiş sırayla her işleme 5ms verir.
    *   Sıra: P1, P2, P3, P4, P5, P1, P2, P4, P5, P2...
    *   Her işlemin "Finish Time - Arrival Time - Burst Time" formülüyle bekleme süreleri hesaplanır.
    *   Notlardaki Gantt şeması P1(0-5), P2(5-10), P3(10-13 biter), P4(13-18), P5(18-23)... şeklinde ilerler.

---

### 8. Banker's Algorithm (Deadlock Avoidance)
**Sistem Durumu (Snapshot):**
Resource Types: A, B, C, D, E.

**Allocated:**
*   P0: 2 0 0 1 0
*   P1: 0 1 2 1 1
*   P2: 2 1 0 3 1
*   P3: 1 3 1 0 2
*   P4: 1 4 3 2 2

**Available:** 3 3 3 1 2 (Notlarda düzeltilmiş haliyle).

**Sorular:**
**a) Her kaynak türünden toplam kaç adet vardır?**
*   Toplam = (Sum of Allocated) + Available.
*   Örneğin A için: $(2+0+2+1+1) + 3 = 9$.

**b) Sistem güvenli (safe) midir?**
*   **Need Matrisi Hesabı:** (Max - Allocation).
*   Algoritma: Available kaynaklarıyla ihtiyacı (Need) karşılanabilen bir işlem bulunur, çalıştırılır, kaynaklar geri alınır (Available += Allocation).
*   **Güvenli Sıra (Safe Sequence):** `<P1, P0, P4, P2, P3>` (Notlardaki çözüm).

**c) P4 işleminden gelen (0,0,2,0,1) isteği hemen karşılanabilir mi?**
*   Request <= Need? Ve Request <= Available? kontrolü yapılır.
*   Eğer koşullar sağlanıyorsa, kaynaklar sanal olarak dağıtılır ve sistemin hala "safe" olup olmadığına bakılır.

---

### 9. Readers-Writers Problemi (Değiştirilmiş Kural)
**Yeni Kural:** "En fazla 5 okuyucu (reader) aynı anda okuyabilir." (Normalde sınırsızdır).

**Çözüm Mantığı:**
Normal Readers-Writers kodunda `readcount` kontrolü yapılır. Değişiklik şurada olmalıdır:

Okuyucu (Reader) Kodu:
```c
wait(mutex);
readcount = readcount + 1;
if (readcount == 1) { // İlk okuyucu yazarı bloklar
    wait(wrt);
}
if (readcount > 5) { // EK KURAL: 5'ten fazlaysa bekleme mantığı
    // Burada ya ek bir semafor kullanılır (initial value = 5)
    // Ya da while döngüsü ile bekleme yapılır.
    // Notlardaki çözüm: "if readcount <= 5" kontrolü eklemiş.
}
signal(mutex);
// ... okuma yapılıyor ...
wait(mutex);
readcount = readcount - 1;
if (readcount == 0) {
    signal(wrt);
}
signal(mutex);
```
*Doğru Yaklaşım:* Okuyucular için `semaphore readersLimit = 5;` tanımlanır. Okumaya başlamadan önce `wait(readersLimit)` ve bitince `signal(readersLimit)` denilerek 5 kişi sınırı donanımsal olarak sağlanır.

---

## BÖLÜM 2: University of Rochester (Final Exam 2002) Çözümleri

### 1. Kısa Cevaplar
**(a) Büyük Çizelgeleme Kuantumu (Large Quantum):**
*   **Avantaj:** Daha az context switch (bağlam değişimi) olur, bu da işletim sistemi yükünü azaltır (compute-bound işlemler için iyidir).
*   **Dezavantaj:** Etkileşimli (interactive) işlemlerin tepki süresi (response time) uzar, sistem yavaş hissedilir.

**(b) Büyük Bellek Sayfa Boyutu (Large Page Size):**
*   **Avantaj:** Page table (sayfa tablosu) boyutu küçülür, bellek yönetimi yükü azalır. TLB hit oranı artabilir.
*   **Dezavantaj:** "Internal Fragmentation" (İç parçalanma) artar. Sayfanın sonundaki kullanılmayan boşluklar israf olur.

**(c) Büyük Disk Blok Boyutu:**
*   **Avantaj:** Dosya okuma/yazma işlemi daha az sayıda komutla yapılır, throughput (verim) artar.
*   **Dezavantaj:** Küçük dosyalar için büyük alan israfı (internal fragmentation).
*   **Unix FFS Çözümü:** Blokları "fragment" adı verilen daha küçük parçalara böler ve bunları farklı dosyalara atayabilir.

### 2. Çizelgeleme (Scheduling) - Prioritized Round Robin
(Bu soru yukarıdaki Bölüm 1 - Madde 2 ile aynıdır, detaylar orada verilmiştir.)

### 3. Çok İşlemcili Çizelgeleme (Multiprocessor Scheduling)
**(a) Processor Affinity (İşlemci Yakınlığı):**
*   **Tanım:** Bir işlemin daha önce çalıştığı işlemcide çalışmaya devam etme eğilimidir.
*   **Önemi:** İşlemcinin cache (önbellek) verileri o işleme ait verilerle doludur. Başka işlemciye geçmek cache miss oranını artırır ve performansı düşürür.

**(b) Global Run Queue vs Per-Processor Queue:**
*   Tek kuyrukta ölçeklenebilirlik sorunu: Tüm işlemciler aynı kuyruğa erişmek için kilit (lock) bekler (contention).
*   Çoklu kuyruk çözümü: Her işlemcinin kendi kuyruğu olması kilit çekişmesini (contention) ortadan kaldırır.

**(c) Neden hala kilit (locking) gerekli?**
*   İş yükü dengelemesi (Load balancing) yapmak için. Bir işlemci boş kaldığında diğerinin kuyruğundan iş çalmak (work stealing) isteyecektir, bu sırada kuyruğu kilitlemesi gerekir.

### 4. Senkronizasyon (Barrier)
**(a) Hatalı Barrier Kodu:**
*   Problem: Bir "race condition" (yarış durumu) vardır. Hızlı bir thread bariyeri geçip döngüye tekrar girdiğinde, `count` değişkenini veya `sense` değişkenini diğer threadler henüz çıkmadan değiştirebilir. Bu deadlock'a veya yanlış senkronizasyona yol açar.

**(b) Düzeltme:**
*   Thread'lerin "sense" (yön) değişkenini lokal olarak kopyalaması ve global değişkeni tersine çevirmesi gerekir.

### 5. Bellek Yönetimi (Clock Algorithm)
**(a) Local vs Remote Disk:**
*   Sayfaların "Remote" (uzak) veya "Local" (yerel) olduğunu belirten bir bit eklenir. Uzak diskten veri getirmek pahalı olduğu için, algoritma yerel sayfaları atmayı (victim) tercih etmelidir.
*   Tercih sırası (Reference, Dirty, Remote) bitlerine göre ayarlanır. (0,0,0) en iyi adaydır (Remote değil).

### 6. Cihaz Yönetimi (Disk I/O)
**(a) Disk Erişim Maliyeti:**
`Disk Access = Seek Time + Rotational Delay + Transfer Time`

**(b) Free Data Mining:**
*   **Fikir:** Disk kafası, etkileşimli kullanıcıların istediği veriye giderken (seek yaparken) veya dönerken (rotational delay), data mining uygulamasının istediği blokların üzerinden geçiyorsa, o blokları "bedavaya" okuyup belleğe almaktır. Öncelik normal kullanıcıdadır, data mining sadece "yol üzerindeyse" servis edilir.

### 7. Dağıtık Deadlock Tespiti
**(a) Lokal Graf ile Tespit Mümkün mü?**
*   Evet. Eğer lokal grafta dışarı giden bir istek (P_ex) ve dışarıdan gelen bir istek üzerinden bir döngü oluşursa, potansiyel deadlock tespit edilebilir. Ancak kesin tespit için global bilgi gerekebilir.

---

## BÖLÜM 3: CS3331 Exam Solutions (Spring 2014)

### 1. Senkronizasyon Temelleri
**(a) Peterson Algoritması Benzeri Çözüm:**
*   Verilen kodun "Mutual Exclusion" sağladığı kanıtlanmalıdır.
*   Kod, Peterson algoritmasına çok benzer (`flag` ve `turn` kullanımı). Eğer $P_0$ kritik bölgedeyse `flag[0]=IN_CS` ve `turn` ya 0'dır ya da $P_1$ ilgilenmiyordur. Bu durum çelişki yaratmadan aynı anda iki işlemin girmesini engeller.

**(b) Race Condition Tanımı:**
*   İki veya daha fazla thread'in paylaşılan bir veriye eş zamanlı erişmesi ve sonucun işlem sırasına bağlı olarak değişmesi durumudur.
*   Örnek: `count++` ve `count--` işlemi makine kodunda (Load, Add/Sub, Store) atomik değildir. Karışırlarsa veri kaybı olur.

### 2. Semaforlar
**(a) Wait Atomik Değilse:**
*   (Bu soru Bölüm 1 - Madde 3'teki soruyla aynıdır. Çözüm yukarıdadır.)

**(b) Sigara İçenler Problemi (Cigarette Smokers Problem):**
*   Bu problemde deadlock mümkündür. Eğer masaya tütün ve kağıt konursa, ama tütüne ihtiyacı olan (kağıt ve kibrite sahip) kişi başka bir şeyi bekliyorsa döngüsel bekleme oluşabilir.

### 3. Problem Çözme (Pipeline Benzeri Senkronizasyon)
*   **Senaryo:** Thread $T_i$, $T_{i-1}$'den `a[i-1]` değerini alır, `a[i]`'yi hesaplar.
*   **Çözüm:** Her `a[i]` dizisi elemanı için bir semafor (veya condition variable) gerekir. $T_i$ hesaplamadan önce `sem[i-1].wait()` yapar, hesapladıktan sonra `sem[i].signal()` yapar. Bu "Linear" bir veri akışıdır (Consumer-Producer zinciri gibi).

### 7. (CS3331) Readers-Writers Varyasyonu (Unisex Bathroom)
*   **Kural:** Erkek ve kadın aynı anda banyoda olamaz. Aynı cinsiyetten olanlar aynı anda olabilir.
*   **Çözüm:** Reader-Writer problemine benzer.
    *   Erkekler = Reader, Kadınlar = Writer (veya tam tersi, ikisi de simetrik gruptur).
    *   `MaleCounter`, `FemaleCounter` tutulur.
    *   Erkek geldiğinde: `MaleCounter++`. Eğer `MaleCounter == 1` ise `BathroomSem.Wait()` (Banyoyu kilitle).
    *   Erkek çıkarken: `MaleCounter--`. Eğer `MaleCounter == 0` ise `BathroomSem.Signal()` (Banyoyu serbest bırak).
    *   Kadınlar için de aynısı simetrik olarak yazılır.
    *   `MaleMutex` ve `FemaleMutex` sayaçları korumak için gereklidir.

---

**Önemli Not:** Yukarıdaki içerik, sağladığınız tüm kaynaklardan (PDF metinleri, el yazısı notlar, ekran görüntüleri) elde edilen bilgilerin sentezlenmiş halidir. Tekrarlayan sorular birleştirilmiştir. Çizim gerektiren sorular (Process Tree, Gantt Chart) metinle açıklanmıştır.
