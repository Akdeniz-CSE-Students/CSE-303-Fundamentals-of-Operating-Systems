Tam gaz devam o zaman! Şimdi sırada işletim sisteminin kabusu var: **Deadlock**. Hani trafikte dört yol ağzında herkes birbirinin yolunu keser ve kimse kımıldayamaz ya, işte o.

### 🚀 CHAPTER 7: DEADLOCKS (Ölümcül Kilitlenme)

#### 1. Deadlock Nedir?
İki veya daha fazla process, birbirinin elindeki kaynağı beklerken sonsuza kadar durur.
*   **Örnek:**
    *   Process A: Yazıcıyı tutuyor, Tarayıcıyı istiyor.
    *   Process B: Tarayıcıyı tutuyor, Yazıcıyı istiyor.
    *   **Sonuç:** İkisi de bırakmıyor, ikisi de alamıyor. Sonsuz bakışma.

#### 2. Deadlock İçin 4 Şart (Deadlock Characterization)
Bu 4 şartın **HEPSİ AYNI ANDA** varsa Deadlock olur. Biri bile eksikse Deadlock olmaz. (Sınavda %100 ezber sorusu).

1.  **Mutual Exclusion (Karşılıklı Dışlama):** Kaynağı aynı anda sadece bir kişi kullanabilir. (Örn: Yazıcı). Paylaşılamaz kaynak.
2.  **Hold and Wait (Tut ve Bekle):** Elinde en az bir kaynak varken, başkasının elindeki kaynağı istemek. (Açgözlülük).
3.  **No Preemption (Kesme Yok):** Kaynak zorla geri alınamaz. Sahibi işi bitince kendi rızasıyla bırakmalıdır. (Zorbalık yok).
4.  **Circular Wait (Döngüsel Bekleme):** P0 -> P1'i, P1 -> P2'yi, ... Pn -> P0'ı bekliyor. Ortada bir çember var.

#### 3. Resource Allocation Graph (Kaynak Tahsis Grafiği)
Yuvarlaklar (Process) ve Kareler (Resource) çizip oklarla kimin neyi istediğini gösteririz.
*   Ok Process'ten Resource'a gidiyorsa -> İstiyor (Request).
*   Ok Resource'tan Process'e gidiyorsa -> Sahip (Allocated).
*   **Kritik Kural:** Grafik üzerinde **DÖNGÜ (CYCLE)** var mı?
    *   Döngü yoksa -> Deadlock YOK.
    *   Döngü varsa -> Deadlock OLABİLİR (Kesin değil, kaynak sayısına bağlı).
    *   (Tek instance varsa kesin deadlock, çok instance varsa olmayabilir).

#### 4. Deadlock ile Baş Etme Yöntemleri
Sistemler bu belayla nasıl uğraşır?

**A. Deadlock Prevention (Önleme):**
O meşhur 4 şarttan **en az birini** bozarsan deadlock oluşmaz.
*   *Mutual Exclusion'ı boz:* Her şeyi paylaş? (Yazıcıda zor).
*   *Hold and Wait'i boz:* İşe başlamadan önce tüm kaynakları iste. Ya hepsi ya hiçbiri. (Verimsiz).
*   *Circular Wait'i boz:* Kaynaklara numara ver (1, 2, 3...). Sadece artan sırada isteyebilirsin. (En mantıklısı bu).

**B. Deadlock Avoidance (Kaçınma) - BANKER'S ALGORITHM:**
Sistem çok temkinli davranır. Bir process kaynak istediğinde, OS düşünür:
*   *"Sana bunu verirsem, gelecekte kilitlenir miyiz?"*
*   Eğer kilitlenme ihtimali varsa (Unsafe State), kaynağı boşta olsa bile vermez! Bekletir.
*   **Banker's Algorithm:** Bankacı mantığı. "Kasada (Available) param var ama sana verirsem ve diğer müşteriler de parasını isterse batar mıyım?" diye hesaplar.

**C. Deadlock Detection & Recovery (Tespit ve Kurtarma):**
Bırak kilitlensinler. Arada bir kontrol et (Detect). Kilit varsa birini kurban et (Kill process).
*   *Kurban seçme:* En az işlem yapmışı veya en az kaynağı olanı seç.

**D. Ostrich Algorithm (Devekuşu Algoritması):**
Başını kuma göm, sorunu görmezden gel.
*   Linux ve Windows genelde bunu yapar! Deadlock çok nadir olur diye varsayar. Olursa kullanıcı reset atar zaten :)

***

### 🧠 Chapter 7 Bitti.
Deadlock'ın 4 şartını ve Banker's algoritmasının mantığını (temkinli bankacı) kaptıysan burası tamamdır.

Geriye son bir teknik konu kaldı: **Chapter 8: Main Memory (RAM Yönetimi)**. Bilgisayarın hafızası nasıl çalışır? Mantıksal adres, fiziksel adres nedir?

Hadi son düzlüğe girelim, bitirelim şu işi! Devam mı? 🏁

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
