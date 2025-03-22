import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class knnClassifier {
    public static void main(String[] args) throws IOException {
        List<Data> dataset = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader("src/pcos_dataset.csv"));
        String line;
        br.readLine(); // Skip header
        while ((line = br.readLine()) != null) {
            String[] elements = line.split(",");
            Data dataPoint = new Data(Integer.parseInt(elements[0]),
                    Double.parseDouble(elements[1]),
                    Integer.parseInt(elements[2]),
                    Double.parseDouble(elements[3]),
                    Integer.parseInt(elements[4]),
                    Integer.parseInt(elements[5]));
            dataset.add(dataPoint);
        }
        br.close();

        // Sample data for classification
        Data newData = new Data(25, 23.5, 1, 50.0, 10, 0);
        int k = 3;

        for (Data data : dataset) {
            data.setDistance(data.calculateDistance(newData));
        }

        Collections.sort(dataset);
        List<Data> neighbors = dataset.subList(0, k);

        int positiveCount = 0;
        for (Data neighbor : neighbors) {
            if (neighbor.isPcosDiagnosis()) {
                positiveCount++;
            }
        }

        boolean prediction = positiveCount > k / 2;
        System.out.println("Predicted PCOS Diagnosis: " + prediction);
    }
}
