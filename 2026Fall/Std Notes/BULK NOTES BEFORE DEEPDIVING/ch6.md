Harika, "sonra detaylandırırız" diyorsan ana iskeleti kurmaya devam.

Geldik sınavın en teknik, en hesap kitaplı ve Taner Hoca'nın "Kesin soracağım" dediği bölüme.

### 🚀 CHAPTER 6: CPU SCHEDULING (İşlemci Zamanlama)

Buranın olayı şu: Elinde yapılacak 5 tane iş (Process) var ama tek bir CPU'n var. **"Hangi sırayla yaparsam herkes en az bekler?"**

#### 1. Kriterler (Hedefimiz Ne?)
*   **CPU Utilization:** İşlemciyi boş yatırma, hep çalışsın (%100'e yakın olsun).
*   **Throughput:** Birim zamanda kaç iş bitirdik? (Çok olsun).
*   **Turnaround Time (Tamamlanma Süresi):** İşin sisteme girmesiyle bitmesi arasında geçen süre. (Az olsun).
*   **Waiting Time (Bekleme Süresi):** İşin sırada beklediği toplam süre. (En önemlisi bu, **AZ** olsun).
*   **Response Time:** İlk tepkiyi verme süresi (Kullanıcı etkileşimi için önemli).

#### 2. Algoritmalar (Sınavın Yıldızları)

**A. FCFS (First-Come, First-Served) - Gelene Ver**
*   Mantık: Bakkal sırası. İlk gelen ilk hizmeti alır.
*   *Sorunu:* **Convoy Effect**. En öne fil gibi (çok uzun) bir iş gelirse, arkadaki sinek gibi (küçük) işler onu bekler. Ortalama bekleme süresi çok artar.
*   *Tipi:* Non-preemptive (Kesintisiz). Giren bitmeden çıkmaz.

**B. SJF (Shortest Job First) - Kısaya Ver**
*   Mantık: İşi en kısa sürecek olanı öne al.
*   *Özelliği:* Ortalama bekleme süresini **MİNİMİZE** eden en iyi algoritmadır (Optimal).
*   *Sorunu:* Geleceği göremezsin (Bir işin ne kadar süreceğini bilemezsin, tahmin edersin).
*   *Tipi:*
    *   **Non-preemptive:** İş başladıysa bitene kadar kesilmez.
    *   **Preemptive (SRTF - Shortest Remaining Time First):** O an çalışan işten daha kısa sürecek yeni bir iş gelirse, mevcut işi durdurup yenisini alır. (Sınavda buna dikkat!).

**C. Priority Scheduling - Önemliye Ver**
*   Mantık: Her işin bir rütbesi (priority) vardır. Generaller erlerden önce girer.
*   *Sorunu:* **Starvation (Açlık)**. Düşük öncelikli bir iş, sürekli yüksek öncelikli işler gelirse sonsuza kadar bekleyebilir.
*   *Çözümü:* **Aging (Yaşlandırma)**. Bekleyen işin rütbesini zamanla artırırsın (Kıdem alır), sonunda sırası gelir.

**D. Round Robin (RR) - Dönme Dolap**
*   Mantık: Herkese eşit süre (**Time Quantum**, örn: 4ms) ver. Süren doldu mu? Sıranın en arkasına geç.
*   *Özelliği:* Time-sharing sistemler (Windows, Linux masaüstü) için en iyisidir. Kimse sonsuza kadar beklemez.
*   *Tipi:* Preemptive (Kesintili). Süre dolunca atılırsın.
*   *Quantum Ayarı:*
    *   Çok büyük yaparsan -> FCFS olur.
    *   Çok küçük yaparsan -> Sürekli iş değiştirme (Context Switch) maliyeti sistemi yorar.

#### 3. Multilevel Queue (Çok Katlı Kuyruk)
*   İşleri sınıflara ayır: "Sistem işleri", "Öğrenci işleri", "Arka plan işleri".
*   Her kuyruğun kendi algoritması olur (Örn: Sistem RR, Öğrenci FCFS).
*   Kuyruklar arası geçiş olabilir (**Multilevel Feedback Queue**): Çok CPU harcayanı alt kuyruğa düşür, çok bekleyeni üst kuyruğa al. (Hocanın "orijinal soru" dediği yer burası olabilir, senin sınav kağıdındaki "Doubling Quantum" sorusu bunun bir varyasyonuydu).

***

### 🧠 Chapter 6 Bitti.
Hesaplama kısmına (Gantt Chart çizimi) sonra detaylı bakarız. Mantığı (Kısa iş, Eşit süre, Öncelik) anladıysan; işletim sisteminin "trafik polisliği" bitmiştir.

Şimdi sırada **Chapter 7: Deadlocks (Ölümcül Kilitlenme)** var. Trafiğin tamamen kilitlendiği o an! Geçelim mi? 🚦

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
