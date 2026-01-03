Tamamdır, harika gidiyoruz. Hız kesmeden devam. Sırada işletim sistemlerinin "kaos yönetimi" bölümü var.

### 🚀 CHAPTER 5: PROCESS SYNCHRONIZATION (Süreç Senkronizasyonu)

Bu bölümün ana fikri: **"Aynı tuvaleti kullanmaya çalışan 100 kişi varsa, kapıya kilit koymazsan felaket olur."**

#### 1. Race Condition (Yarış Durumu)
*   **Olay:** İki process/thread aynı veriye (paylaşılan değişken) aynı anda erişip değiştirmeye çalışırsa, sonuç "kimin son yazdığına" bağlı olur. Bu belirsizliğe **Race Condition** denir.
*   **Örnek:** Banka hesabında 1000 TL var.
    *   T1: 500 yatırıyor (Okudu: 1000 -> Yaptı: 1500)
    *   T2: 200 çekiyor (Okudu: 1000 -> Yaptı: 800)
    *   Eğer aynı anda çalışırlarsa, T1 yazar (1500), sonra T2 üzerine yazar (800). **500 TL buhar oldu!**

#### 2. Critical Section (Kritik Bölge)
Paylaşılan verinin değiştirildiği kod parçasına **Critical Section** denir.
*   **Kural:** Aynı anda sadece **BİR** process buraya girebilir.

**Critical Section Problemini Çözmek İçin 3 Şart (Ezberle):**
1.  **Mutual Exclusion (Karşılıklı Dışlama):** İçeride biri varken başkası giremez.
2.  **Progress (İlerleme):** İçerisi boşsa ve girmek isteyen varsa, kimin gireceğine sonsuza kadar karar verememe durumu olmamalı. İşler yürümeli.
3.  **Bounded Waiting (Sınırlı Bekleme):** Biri girmek istiyorsa, sonsuza kadar bekletilmemeli (Starvation olmamalı).

#### 3. Çözüm Araçları: Mutex & Semaphore
Bunlar yazılımsal kilitlerdir.

*   **Mutex (Mutual Exclusion Lock):**
    *   Basit anahtar. "Tuvalet anahtarı" gibi.
    *   `acquire()`: Kilidi al (Giremezsen bekle).
    *   `release()`: Kilidi bırak.
    *   **Boolean:** Sadece 0 veya 1 olur.

*   **Semaphore (Semafor):**
    *   Daha yetenekli sayaç. "Otopark girişindeki sayaç" gibi.
    *   **Counting Semaphore:** Değeri N olabilir. (Örn: 5 tane yazıcı var, 5 kişi aynı anda girebilir).
    *   **Binary Semaphore:** Değeri 0 veya 1'dir. Mutex ile aynı işi yapar.
    *   **Operasyonlar:**
        *   `wait()` (veya `P()`): Sayacı 1 azalt. Eğer 0'ın altına düşerse **BLOKLA** (Bekle).
        *   `signal()` (veya `V()`): Sayacı 1 artır. Bekleyen biri varsa onu **UYANDIR**.

#### 4. Klasik Problemler (Sınavın Gözbebekleri)
Bunların kod şablonlarını gözün ısırsa iyi olur:

*   **Producer-Consumer (Üretici-Tüketici):**
    *   Biri veriyi buffer'a koyar, diğeri alır. Buffer dolunca üretici, boşalınca tüketici beklemeli.
*   **Readers-Writers (Okuyan-Yazan):**
    *   Aynı anda birden fazla kişi okuyabilir (sorun yok).
    *   Ama biri yazarken kimse okuyamaz ve yazamaz.
    *   *Senin sorudaki trick:* "Max 5 okuyucu" denirse, Reader koduna sayaç koyup 5'i geçince bloklayacaksın.
*   **Dining Philosophers (Filozofların Akşam Yemeği):**
    *   5 filozof, 5 çatal. Yemek için 2 çatal lazım. Herkes sağındakini alırsa kimse yiyemez (**Deadlock**).

***

### 🧠 Chapter 5 Bitti.
Mantığı kavradıysan (Yarış, Kilit, Bekleme), sınavın en çok puan getiren kısmına, **Chapter 6: CPU Scheduling**'e geçelim mi? Orada hesap kitap var, kalem kağıt lazım olacak. Hazır mısın?

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
