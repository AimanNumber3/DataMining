import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class dataClassifier {
/*
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("src/pcos_dataset.csv"));
        String line = "";
        List<Data> Data1 = new LinkedList<>();
        br.readLine();
        while((line = br.readLine()) != null){
            String[] elements = line.split(",");
            Data Data2 = new Data(Integer.parseInt(elements[0]),
                    Double.parseDouble(elements[1]),
                    Integer.parseInt(elements[2]),
                    Double.parseDouble(elements[3]),
                    Integer.parseInt(elements[4]),
                    Integer.parseInt(elements[5]));
            Data1.add(Data2);
        }
        Data newData = new Data(300,300,300,300,300,3);
        List<Data> newData1 = new LinkedList<>();
        newData1 = Data.calculateDistance(Data1, newData);
        Collection.sort(newData1);

        newData1.stream().limit(3).forEach(System.out::println);
    }*/
}
