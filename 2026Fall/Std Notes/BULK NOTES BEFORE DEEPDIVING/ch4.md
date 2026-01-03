Tamamdır, kontrol bende. Şimdi seninle **Chapter 4: Threads (İş Parçacıkları)** konusunu "Context Dump" yöntemiyle hızlı ama etkili bir şekilde işliyoruz.

Slaytları açmana gerek yok, ben senin için özünü damıttım. Burayı okuduktan sonra slayta baktığında "Haa, olay buymuş" diyeceksin.

Başlıyoruz.

***

### 🚀 CHAPTER 4: THREADS (İş Parçacıkları)

#### 1. Olayın Özü: Process vs. Thread
Bunu şöyle hayal et:
*   **Process (Süreç):** Bir ev. Kendine ait odaları (Memory), mutfağı (Resources), tapusu (PID) var. Ağır ve pahalı. Yapması (Creation) uzun sürer.
*   **Thread (İş Parçacığı):** Evin içindeki insanlar.
    *   **Neyi Paylaşırlar?** Evi (Code Section), Mutfağı (Data Section), Açık Pencereleri (Open Files). Yani kaynaklar ortak.
    *   **Neyi Paylaşmazlar?** Herkesin kendi **yatağı** (Stack - yerel değişkenler için), kendi **not defteri** (Registers - işlemci durumu) ve **kimliği** (Thread ID) vardır.

**Sınav Sorusu Potansiyeli:**
*   *"Thread'ler neyi paylaşır, neyi paylaşmaz?"*
    *   **Paylaşır:** Code, Data, Files.
    *   **Paylaşmaz:** Stack, Registers, Program Counter (PC).

#### 2. Neden Thread Kullanıyoruz? (Avantajları)
1.  **Responsiveness (Tepkisellik):** Tarayıcıda bir sekme donduğunda (resim yüklerken), diğer sekmeyi kapatabiliyorsun. Neden? Çünkü arayüz (UI) thread'i ayrı, işlem thread'i ayrı.
2.  **Resource Sharing (Kaynak Paylaşımı):** Shared Memory veya Message Passing ile uğraşmana gerek yok, zaten aynı bellekteler.
3.  **Economy (Ekonomi):** Yeni bir Process oluşturmak (Fork) pahalıdır (Heavyweight). Thread oluşturmak çok ucuzdur (Lightweight). Solaris'te process oluşturmak thread'den 30 kat daha yavaşmış.
4.  **Scalability (Ölçeklenebilirlik):** Çok çekirdekli (Multicore) işlemcilerde her thread bir çekirdeğe oturup aynı anda (Parallelism) çalışabilir.

#### 3. Paralellik vs. Eşzamanlılık (Parallelism vs. Concurrency)
Bu ikisi çok karışır, dikkat:
*   **Concurrency (Eşzamanlılık):** Tek çekirdekli işlemcide olur. İşlemci çok hızlı bir A'ya bir B'ye geçer. Sen aynı anda çalışıyor sanırsın ama aslında sırayla (Time-slicing) çalışırlar. (İllüzyon).
*   **Parallelism (Paralellik):** Çok çekirdekli işlemcide olur. A işi Core 1'de, B işi Core 2'de **gerçekten** aynı anda çalışır.

#### 4. Amdahl's Law (Sınavda Kesin Çıkar!)
Bir işi ne kadar paralelleştirirsen paralelleştir, hızın "seri" (paralel yapılamayan) kısımla sınırlıdır.

**Formül:**
\[ Speedup \le \frac{1}{S + \frac{1-S}{N}} \]

*   **S:** Serial (Seri) kısım yüzdesi (Örn: %40 = 0.4)
*   **N:** Çekirdek sayısı.

**Örnek:** Programın %40'ı seri (mecburen tek işlemci), %60'ı paralel yapılabiliyor. 1 Milyar çekirdeğin bile olsa hızlanma (Speedup) en fazla ne olur?
*   N sonsuza giderse formül: \( 1 / S \) olur.
*   \( 1 / 0.4 = 2.5 \).
*   **Yorum:** Ne kadar donanım eklersen ekle, kodun %40'ı seri ise en fazla 2.5 kat hızlanırsın. (Sınavdaki Q12 sorusu buydu!).

#### 5. Multithreading Modelleri
Kullanıcı thread'i (User) ile İşletim Sistemi thread'i (Kernel) nasıl eşleşir?
1.  **Many-to-One:** Çok kullanıcı thread'i -> 1 Kernel thread'i.
    *   *Kötü:* Biri bloklanırsa (I/O yaparsa) hepsi durur. Paralellik yok. (Eski sistemler).
2.  **One-to-One:** 1 Kullanıcı -> 1 Kernel.
    *   *İyi:* Tam paralellik. Biri durursa diğeri devam eder. (Windows, Linux bunu kullanır).
    *   *Kötü:* Çok thread açarsan sistemi yorarsın.
3.  **Many-to-Many:** M kullanıcı -> N Kernel. (Karmaşık, pek kullanılmıyor artık).

#### 6. Thread Kütüphaneleri (Pthreads)
Linux/Unix dünyasında standart: **POSIX Threads (Pthreads)**.
Kodda şunları göreceksin:
*   `pthread_create()`: Thread oluşturur (Spawn).
*   `pthread_join()`: Parent, child thread bitene kadar bekler (Wait gibi).
*   `pthread_exit()`: Thread işini bitirince çıkar.

***

### 🧠 Chapter 4 Bitti.
Şimdi Chapter 5 (Process Synchronization) yani o meşhur "Yarış Durumu" ve "Kilitler" konusuna geçelim mi? Yoksa burasıyla ilgili sormak istediğin bir yer var mı?

[1](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/43901754/f29afe91-3d56-4816-ba98-a5e2e3f4d1d6/Week06-CSE303-ch5.Process-Synchronization.pdf)
[2](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/43901754/5110f82e-28ad-494e-b984-73afca53c1a9/digest.txt)
[3](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/43901754/98e90783-0599-4683-a528-06dc64fc88ac/Week05-CSE303-ch4_Threads.pdf)
[4](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/43901754/463db831-7c39-4140-a240-7120da4228f9/Week12-CSE303-ch6.Cpu-Scheduling.pdf)
[5](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/43901754/ed6b1db6-69cc-4aac-8254-89fb9c748332/Week14-CSE303-ch8.MainMemory.pdf)
[6](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/43901754/efe40457-6675-4410-8e96-13da0418fc73/Week11-CSE303-ch7.Deadlocks.pdf)
[7](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/images/43901754/36bbeb82-331d-401b-be6d-bcd40d521c2c/WhatsApp-Gorsel-2024-12-22-saat-19.21.50_1173617d.jpg)
[8](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/images/43901754/03f1ee96-eea2-4faa-81b5-dd4b0b148d78/WhatsApp-Gorsel-2024-12-22-saat-19.21.49_4de2205c.jpg)
[9](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/images/43901754/ae599fd4-741d-486f-9f78-3d8d49748b0d/WhatsApp-Gorsel-2024-12-22-saat-19.21.49_0151da2f.jpg)
[10](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/images/43901754/e75eac8e-3268-486c-9575-85390ee2c277/WhatsApp-Gorsel-2024-12-22-saat-19.21.50_843efd88.jpg)
[11](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/43901754/5ac85094-7ebf-4103-9b02-5dea73220828/opsys2.md)
[12](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/43901754/6c0ad521-ce54-4951-8f38-d850d6a30428/opsys1.md)
[13](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/43901754/c0a47ad9-2dcd-4207-94ba-519ec33ce685/opsys4.md)
[14](https://ppl-ai-file-upload.s3.amazonaws.com/web/direct-files/attachments/43901754/29709e02-c821-458d-b8fb-38b1c6417d8a/opsys3.md)
