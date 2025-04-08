public class Main {
    public static void main(String[] args) {
        // Load data dan otomatis lakukan EDA + Normalisasi
        database db = new database();

        // Klasifikasi menggunakan KNN
        KnnClassifier knn = new KnnClassifier(3);
        knn.evaluate(db.getData(), 0.8);
    }
}
