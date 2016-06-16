package nguyengiap.vietitpro.tudienanhviet.com.model;

/**
 * Created by Nguyen Giap on 9/11/2015.
 */
public class VerbEntity {
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getSimplePresent() {
        return SimplePresent;
    }

    public void setSimplePresent(String simplePresent) {
        SimplePresent = simplePresent;
    }

    public String getSimplePast() {
        return SimplePast;
    }

    public void setSimplePast(String simplePast) {
        SimplePast = simplePast;
    }

    public String getPastPraticiple() {
        return PastPraticiple;
    }

    public void setPastPraticiple(String pastPraticiple) {
        PastPraticiple = pastPraticiple;
    }

    public String getPresentParticiple() {
        return PresentParticiple;
    }

    public void setPresentParticiple(String presentParticiple) {
        PresentParticiple = presentParticiple;
    }

    public VerbEntity(int ID, String keyWord, String simplePresent, String simplePast, String pastPraticiple, String presentParticiple) {

        this.ID = ID;
        KeyWord = keyWord;
        SimplePresent = simplePresent;
        SimplePast = simplePast;
        PastPraticiple = pastPraticiple;
        PresentParticiple = presentParticiple;
    }

    private int ID;
    private String KeyWord;
    private String SimplePresent;
    private String SimplePast;
    private String PastPraticiple;
    private String PresentParticiple;



}
