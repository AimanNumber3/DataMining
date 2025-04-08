public class Evaluation {
    private int TP; //true positive
    private int TN; //true negative
    private int FP; //false positive
    private int FN; //false negative

    public void setMetrics(int TP, int TN, int FP, int FN) {
        this.TP = TP;
        this.TN = TN;
        this.FP = FP;
        this.FN = FN;
    }

    public double getAccuracy() {
        return (double) (TP + TN) / (TP + TN + FP + FN);
    }

    public double getPrecision() {
        return (TP + FP) > 0 ? (double) TP / (TP + FP) : 0;
    }

    public double getRecall() {
        return (TP + FN) > 0 ? (double) TP / (TP + FN) : 0;
    }

    public double getF1Score() {
        double precision = getPrecision();
        double recall = getRecall();
        return (precision + recall) > 0 ? 2 * precision * recall / (precision + recall) : 0;
    }

    public void printEvaluation() {
        System.out.println("Accuracy: " + getAccuracy());
        System.out.println("Precision: " + getPrecision());
        System.out.println("Recall: " + getRecall());
        System.out.println("F1 Score: " + getF1Score());
    }
}