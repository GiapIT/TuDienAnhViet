package nguyengiap.vietitpro.tudienanhviet.com.model;

/**
 * Created by GiapNN on 6/15/2016.
 */
public class DictEntity {
    private int id;
    private String sword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSword() {
        return sword;
    }

    public void setSword(String sword) {
        this.sword = sword;
    }

    public String getSphonetic() {
        return sphonetic;
    }

    public void setSphonetic(String sphonetic) {
        this.sphonetic = sphonetic;
    }

    public String getSmeaning() {
        return smeaning;
    }

    public void setSmeaning(String smeaning) {
        this.smeaning = smeaning;
    }

    public String getSsummary() {
        return ssummary;
    }

    public void setSsummary(String ssummary) {
        this.ssummary = ssummary;
    }

    public DictEntity(int id, String sword, String sphonetic, String ssummary) {

        this.id = id;
        this.sword = sword;
        this.sphonetic = sphonetic;
        this.ssummary = ssummary;
    }

    private String sphonetic;
    private String smeaning;
    private String ssummary;

    public DictEntity(int id, String sword) {
        this.id = id;
        this.sword = sword;
    }
}
