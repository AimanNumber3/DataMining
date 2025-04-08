class Data implements Comparable<Data> {
    private double age;
    private double bmi;
    private int menstrualIrregularity;
    private double testosteroneLevel;
    private int antralFollicleCount;
    private int pcosDiagnosis;
    private double distance; // digunakan untuk sorting KNN

    public Data(double age, double bmi, int menstrualIrregularity, double testosteroneLevel, int antralFollicleCount, int pcosDiagnosis) {
        this.age = age;
        this.bmi = bmi;
        this.menstrualIrregularity = menstrualIrregularity;
        this.testosteroneLevel = testosteroneLevel;
        this.antralFollicleCount = antralFollicleCount;
        this.pcosDiagnosis = pcosDiagnosis;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public int getMenstrualIrregularity() {
        return menstrualIrregularity;
    }

    public double getTestosteroneLevel() {
        return testosteroneLevel;
    }

    public void setTestosteroneLevel(double testosteroneLevel) {
        this.testosteroneLevel = testosteroneLevel;
    }

    public int getAntralFollicleCount() {
        return antralFollicleCount;
    }

    public void setAntralFollicleCount(int antralFollicleCount) {
        this.antralFollicleCount = antralFollicleCount;
    }

    public int getPcosDiagnosis() {
        return pcosDiagnosis;
    }

    public double calculateDistance(Data other) {
        return Math.sqrt(
                Math.pow(this.age - other.age, 2) +
                        Math.pow(this.bmi - other.bmi, 2) +
                        Math.pow(this.testosteroneLevel - other.testosteroneLevel, 2) +
                        Math.pow(this.antralFollicleCount - other.antralFollicleCount, 2)
        );
    }

    public void setDistance(double distance) {
        this.distance = distance;
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