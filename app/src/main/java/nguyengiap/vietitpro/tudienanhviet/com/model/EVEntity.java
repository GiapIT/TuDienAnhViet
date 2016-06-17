package nguyengiap.vietitpro.tudienanhviet.com.model;

/**
 * Created by DungPT17 on 2016/06/16.
 */
public class EVEntity {
    private String word;
    private String means;
    private String phonic;
    private String simpleMeans;

    public EVEntity() {
    }

    public EVEntity(String word) {
        this.word = word;
    }

    public EVEntity(String word, String means) {
        this.word = word;
        this.means = means;
    }

    public EVEntity(String word, String means, String phonic) {
        this.word = word;
        this.means = means;
        this.phonic = phonic;
    }

    public EVEntity(String word, String means, String phonic, String simpleMeans) {
        this.word = word;
        this.means = means;
        this.phonic = phonic;
        this.simpleMeans = simpleMeans;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public String getPhonic() {
        return phonic;
    }

    public void setPhonic(String phonic) {
        this.phonic = phonic;
    }

    public String getSimpleMeans() {
        return simpleMeans;
    }

    public void setSimpleMeans(String simpleMeans) {
        this.simpleMeans = simpleMeans;
    }
}
