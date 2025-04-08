import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

//Konstruktor: memuat data dari file CSV dan langsung menjalankan EDA serta normalisasi
public class database {
    private ArrayList<Data> data = new ArrayList<>();
    private String filename = "C:\\DataProcessing\\src\\pcos_dataset.csv";
    private Path path = Path.of(filename);

    // Variabel untuk normalisasi
    private double minAge, maxAge;
    private double minBMI, maxBMI;
    private double minTestosterone, maxTestosterone;
    private int minFollicle, maxFollicle;

    public database() {
        open();
        lakukanEDA();
        normalisasiData();
    }

    public ArrayList<Data> getData() {
        return data;
    }

    /*
    Membaca file dataset PCOS dari path yang telah ditentukan dan mem-parsing setiap baris
    menjadi objek `Data`. File diasumsikan berformat CSV dengan urutan kolom:
    Age, BMI, Menstrual Irregularity, Testosterone Level, Antral Follicle Count, PCOS Diagnosis.
    Dataset akan disimpan ke dalam list `data`
     */
    public void open() {
        System.out.println("\nMembaca dataset PCOS...");
        try { // Baca semua baris dari file
            List<String> lines = Files.readAllLines(path);
            data = new ArrayList<>();

            // Lewati header (mulai dari index 1)
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] element = line.split(",");

                // Parsing setiap kolom menjadi tipe data yang sesuai
                int age = Integer.parseInt(element[0]);
                double bmi = Double.parseDouble(element[1]);
                int menstrualIrregularity = Integer.parseInt(element[2]);
                double testosteroneLevel = Double.parseDouble(element[3]);
                int antralFollicleCount = Integer.parseInt(element[4]);
                int pcosDiagnosis = Integer.parseInt(element[5]);
                Data pcos = new Data(age, bmi, menstrualIrregularity, testosteroneLevel, antralFollicleCount, pcosDiagnosis);
                data.add(pcos);
            }
            System.out.println("Dataset berhasil dibaca!\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
    Melakukan Exploratory Data Analysis (EDA) sederhana untuk menghitung dan mencetak nilai rata-rata untuk fitur numerik.
     */
    public void lakukanEDA() {
        System.out.println("=== Exploratory Data Analysis (EDA) ===");
        System.out.println("Jumlah data          : " + data.size());
        if (!data.isEmpty()) {
            System.out.println("Jumlah atribut       : 6 (age, bmi, menstrualIrregularity, testosteroneLevel, antralFollicleCount, pcosDiagnosis)");

            double totalAge = 0, totalBMI = 0, totalTestosterone = 0;
            int totalFollicle = 0;
            int totalPCOS = 0;

            for (Data d : data) {
                totalAge += d.getAge();
                totalBMI += d.getBmi();
                totalTestosterone += d.getTestosteroneLevel();
                totalFollicle += d.getAntralFollicleCount();
                if (d.getPcosDiagnosis() == 1) totalPCOS++;
            }

            System.out.printf("Rata-rata usia       : %.2f\n", totalAge / data.size());
            System.out.printf("Rata-rata BMI        : %.2f\n", totalBMI / data.size());
            System.out.printf("Rata-rata testosteron: %.2f\n", totalTestosterone / data.size());
            System.out.printf("Rata-rata follicle   : %.2f\n", (double) totalFollicle / data.size());
            System.out.printf("Jumlah positif PCOS  : %d\n", totalPCOS);
            System.out.printf("Jumlah negatif PCOS  : %d\n", data.size() - totalPCOS);
        }
        System.out.println("=======================================\n");
    }

    /*
    Melakukan normalisasi Min-Max pada fitur numerik: Age, BMI, Testosterone Level, dan Antral Follicle Count.
     */
    public void normalisasiData() {
        System.out.println("Melakukan normalisasi data (Min-Max)...");

        // Inisialisasi nilai min dan max
        minAge = Double.MAX_VALUE; maxAge = Double.MIN_VALUE;
        minBMI = Double.MAX_VALUE; maxBMI = Double.MIN_VALUE;
        minTestosterone = Double.MAX_VALUE; maxTestosterone = Double.MIN_VALUE;
        minFollicle = Integer.MAX_VALUE; maxFollicle = Integer.MIN_VALUE;

        // Cari nilai min dan max
        for (Data d : data) {
            if (d.getAge() < minAge) minAge = d.getAge();
            if (d.getAge() > maxAge) maxAge = d.getAge();

            if (d.getBmi() < minBMI) minBMI = d.getBmi();
            if (d.getBmi() > maxBMI) maxBMI = d.getBmi();

            if (d.getTestosteroneLevel() < minTestosterone) minTestosterone = d.getTestosteroneLevel();
            if (d.getTestosteroneLevel() > maxTestosterone) maxTestosterone = d.getTestosteroneLevel();

            if (d.getAntralFollicleCount() < minFollicle) minFollicle = d.getAntralFollicleCount();
            if (d.getAntralFollicleCount() > maxFollicle) maxFollicle = d.getAntralFollicleCount();
        }

        // Transformasi data (normalisasi)
        for (Data d : data) {
            d.setAge(normalisasi(d.getAge(), minAge, maxAge));
            d.setBmi(normalisasi(d.getBmi(), minBMI, maxBMI));
            d.setTestosteroneLevel(normalisasi(d.getTestosteroneLevel(), minTestosterone, maxTestosterone));

            double follicleNorm = normalisasi(d.getAntralFollicleCount(), minFollicle, maxFollicle);
            d.setAntralFollicleCount((int) Math.round(follicleNorm * (maxFollicle - minFollicle) + minFollicle));
        }

        System.out.println("Normalisasi selesai.\n");
    }

    private double normalisasi(double value, double min, double max) {
        if (max - min == 0) return 0;
        return (value - min) / (max - min);
    }

    //Menampilkan data setelah proses normalisasi.
    public void showData() {
            database db = new database(); // otomatis lakukan normalisasi
            ArrayList<Data> dataList = db.getData();

            System.out.println("=== Data Setelah Normalisasi ===");
            for (int i = 0; i < dataList.size(); i++) {
                Data d = dataList.get(i);
                System.out.printf(
                        "Data %d: Age = %.2f, BMI = %.2f, Menstrual = %d, Testosterone = %.2f, Follicle = %d, PCOS = %d\n",
                        i + 1,
                        d.getAge(),
                        d.getBmi(),
                        d.getMenstrualIrregularity(),
                        d.getTestosteroneLevel(),
                        d.getAntralFollicleCount(),
                        d.getPcosDiagnosis()
                );
            }
            System.out.println("================================\n");
    }
}
