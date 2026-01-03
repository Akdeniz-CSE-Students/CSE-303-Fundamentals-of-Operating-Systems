Süper, enerjini sevdim. Son konumuzdayız. Burası biraz terim dolu ama mantığı çok basittir.

### 🚀 CHAPTER 8: MAIN MEMORY (Ana Bellek / RAM Yönetimi)

Buradaki temel sorun şu: Programlar diskte durur ama çalışmak için RAM'e gelmelidir. Peki yüzlerce programı sınırlı RAM'e nasıl sığdıracağız ve birbirlerinin alanına girmelerini nasıl engelleyeceğiz?

#### 1. Logical vs. Physical Address (Sanal vs. Gerçek)
En önemli ayrım bu.
*   **Logical Address (Virtual):** CPU'nun ürettiği adres. Programın "kendi dünyasında" gördüğü adres. (Örn: 0'dan başlar).
*   **Physical Address:** RAM'deki gerçek adres.
*   **MMU (Memory Management Unit):** Bu ikisi arasındaki çevirmen donanım.
    *   *Sen:* "Adres 100'e git" dersin.
    *   *MMU:* "Senin 100'ün aslında RAM'deki 50100. hücre" der ve çevirir.

#### 2. Swapping (Takas)
RAM doldu mu? Kullanılmayan bir process'i geçici olarak diske (Backing Store / HDD) postala (**Swap Out**). Lazım olunca geri getir (**Swap In**).
*   Bilgisayarın yavaşladığında diskin çılgınca dönmesinin sebebi budur.

#### 3. Contiguous Allocation (Bitişik Tahsis) - Eski Usul
Process'i RAM'e tek parça halinde koymak.
*   **Sorun:** **Fragmentation (Parçalanma)**.
    *   **External Fragmentation:** RAM'de toplamda yeterli boş yer var ama hepsi parça parça. Büyük bir process sığmıyor. (İsviçre peyniri gibi delikli RAM).
    *   *Çözüm:* **Compaction (Sıkıştırma)**. Her şeyi kenara itip boşluğu birleştirmek (Defrag gibi). Ama çok yavaş.

#### 4. Paging (Sayfalama) - Modern Usul
Modern sistemlerin (Linux, Windows, Android) kullandığı yöntem. RAM'i ve Programı eşit boyutta küçük parçalara bölüyoruz.
*   **Page (Sayfa):** Programın (Logical) parçaları.
*   **Frame (Çerçeve):** RAM'in (Physical) parçaları. (Page ile aynı boyutta, örn: 4KB).
*   **Olay:** Programın Page 1'i RAM'in Frame 5'ine, Page 2'si Frame 99'a gidebilir. Bitişik olmak zorunda değil! External Fragmentation biter.

**Page Table (Harita):** Hangi Page'in hangi Frame'de olduğunu tutan devasa liste. Her process'in kendi Page Table'ı vardır.

**TLB (Translation Lookaside Buffer):**
Page Table çok büyüktür ve RAM'de durur. Her erişim için RAM'e iki kere gitmek (1. Haritaya bak, 2. Veriyi al) yavaştır.
*   **Çözüm:** TLB. İşlemcinin içinde süper hızlı bir önbellek. Son kullanılan adres çevirilerini tutar.
*   **Hit:** Adres TLB'de var -> Çok hızlı.
*   **Miss:** Adres TLB'de yok -> RAM'deki tabloya git (Yavaş).
*   **Sınav Sorusu (EAT Hesabı):** Hani notlarda vardı ya `0.9 * (10+250)`... İşte o formül bu TLB'nin performansını ölçer.

#### 5. Segmentation (Bölümlendirme)
Programı mantıksal parçalara bölmek: "Kod Bölümü", "Stack Bölümü", "Data Bölümü".
*   Her bölümün bir **Base** (Başlangıç) ve **Limit** (Uzunluk) değeri vardır.
*   Paging'den farkı: Parçalar eşit boyutta değil, mantıksal bütündür.

***

### 🏁 VE BİTTİ!
İşletim Sistemleri 101'i tamamladın. Büyük resmi (Big Picture) artık görüyorsun:
1.  **Process/Thread:** İşleri yapan işçiler. (Ch 3-4)
2.  **Sync:** İşçiler birbirini ezmesin diye kilitler. (Ch 5)
3.  **Scheduling:** İşçileri sıraya sokan patron. (Ch 6)
4.  **Deadlock:** İşçilerin inatlaşıp kilitlenmesi. (Ch 7)
5.  **Memory:** İşçilerin çalıştığı ofis alanı. (Ch 8)

**Şimdi ne yapalım?**
İstersen artık şu elindeki **WhatsApp görsellerindeki** veya **çıkmış sorulardaki** o spesifik (hesaplamalı) sorulara dönüp, bu öğrendiklerimizle "Ha, bu bundan dolayıymış" diyerek nokta atışı çözüm yapabiliriz.

Özellikle "Agent Smith" fork sorusunu veya "Banker's Algorithm" matrisini çözmemi ister misin?

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
