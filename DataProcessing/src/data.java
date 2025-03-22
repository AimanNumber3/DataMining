import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Data implements Comparable<Data> {
    private int age;
    private double bmi;
    private boolean menstrualIrregularity;
    private double testosteroneLevel;
    private int antralFollicleCount;
    private boolean pcosDiagnosis;
    private double distance;

    public Data(int age, double bmi, int menstrualIrregularity, double testosteroneLevel, int antralFollicleCount, int pcosDiagnosis) {
        this.age = age;
        this.bmi = bmi;
        this.menstrualIrregularity = menstrualIrregularity == 1;
        this.testosteroneLevel = testosteroneLevel;
        this.antralFollicleCount = antralFollicleCount;
        this.pcosDiagnosis = pcosDiagnosis == 1;
    }

    public double calculateDistance(Data other) {
        return Math.sqrt(Math.pow(this.age - other.age, 2) +
                Math.pow(this.bmi - other.bmi, 2) +
                Math.pow(this.testosteroneLevel - other.testosteroneLevel, 2) +
                Math.pow(this.antralFollicleCount - other.antralFollicleCount, 2));
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public boolean isPcosDiagnosis() {
        return pcosDiagnosis;
    }

    @Override
    public int compareTo(Data o) {
        return Double.compare(this.distance, o.distance);
    }

    @Override
    public String toString() {
        return "Data{" +
                "age=" + age +
                ", bmi=" + bmi +
                ", menstrualIrregularity=" + menstrualIrregularity +
                ", testosteroneLevel=" + testosteroneLevel +
                ", antralFollicleCount=" + antralFollicleCount +
                ", pcosDiagnosis=" + pcosDiagnosis +
                '}';
    }
}