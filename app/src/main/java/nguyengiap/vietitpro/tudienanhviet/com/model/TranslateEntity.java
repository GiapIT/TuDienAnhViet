package nguyengiap.vietitpro.tudienanhviet.com.model;

/**
 * Created by Nguyen Giap on 10/2/2015.
 */
public class TranslateEntity {
    private int id;

    public TranslateEntity(int id, String clientId, String clientSecret) {
        this.id = id;
        ClientId = clientId;
        ClientSecret = clientSecret;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClientId() {
        return ClientId;
    }

    public void setClientId(String clientId) {
        ClientId = clientId;
    }

    public String getClientSecret() {
        return ClientSecret;
    }

    public void setClientSecret(String clientSecret) {
        ClientSecret = clientSecret;
    }

    private String ClientId;
    private String ClientSecret;
}
