package server.form;

import java.io.Serializable;

/**
 * Created by Deys on 15.06.2015.
 */
public class InfoRequestForm implements Serializable {

    private String url = "";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
