import java.util.*;

public class KnnClassifier {
    private int k;
    private List<Data> trainingData;

    public KnnClassifier(int k) {
        this.k = k;
    }

    public void fit(List<Data> data) {
        this.trainingData = data;
    }

    public int predict(Data input) {
        // Buat priority queue untuk menyimpan data berdasarkan jarak terdekat lalu Hitung jarak dari input ke seluruh data training
        PriorityQueue<DataDistance> pq = new PriorityQueue<>(Comparator.comparingDouble(d -> d.distance));
        for (Data d : trainingData) {
            double distance = calculateDistance(d, input);
            pq.offer(new DataDistance(d, distance));
        }

        // Voting dari k tetangga terdekat
        int countPositive = 0;
        for (int i = 0; i < k && !pq.isEmpty(); i++) {
            DataDistance neighbor = pq.poll();
            if (neighbor.data.getPcosDiagnosis() == 1) {
                countPositive++;
            }
        }
        // Jika mayoritas bertetangga positif, prediksi = 1
        return (countPositive > k / 2) ? 1 : 0;
    }

    /*
    Menghitung jarak Euclidean antara dua objek Data berdasarkan 4 fitur numerik: Age, BMI, Testosteron Level, dan AFC
    lalu return jarak Euclidean.
    */
    private double calculateDistance(Data a, Data b) {
        double ageDiff = a.getAge() - b.getAge();
        double bmiDiff = a.getBmi() - b.getBmi();
        double testosteroneDiff = a.getTestosteroneLevel() - b.getTestosteroneLevel();
        double follicleDiff = a.getAntralFollicleCount() - b.getAntralFollicleCount();
        return Math.sqrt(ageDiff * ageDiff + bmiDiff * bmiDiff + testosteroneDiff * testosteroneDiff + follicleDiff * follicleDiff);
    }

    /*
    Menilai akurasi model KNN menggunakan metode hold-out, kemudian data akan diacak dan dibagi menjadi training dan testing.
    */
    public void evaluate(List<Data> dataset, double trainRatio) {
        Collections.shuffle(dataset, new Random());
        int trainSize = (int) (dataset.size() * trainRatio);

        List<Data> trainSet = dataset.subList(0, trainSize);
        List<Data> testSet = dataset.subList(trainSize, dataset.size());

        fit(trainSet); //training

        int TP = 0, FP = 0, TN = 0, FN = 0;

        // Lakukan prediksi pada test set
        for (Data test : testSet) {
            int actual = test.getPcosDiagnosis();
            int predicted = predict(test);

            // Update confusion matrix
            if (predicted == 1 && actual == 1) TP++;
            else if (predicted == 1 && actual == 0) FP++;
            else if (predicted == 0 && actual == 0) TN++;
            else if (predicted == 0 && actual == 1) FN++;
        }

        // Tampilkan metrik dari class evaluation
        Evaluation metrics = new Evaluation();
        metrics.setMetrics(TP, TN, FP, FN);
        metrics.printEvaluation();
    }

    private static class DataDistance {
        Data data;
        double distance;

        public DataDistance(Data data, double distance) {
            this.data = data;
            this.distance = distance;
        }
    }
}
