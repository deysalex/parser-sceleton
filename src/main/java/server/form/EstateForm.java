package server.form;

import java.io.Serializable;

/**
 * Created by Deys on 15.06.2015.
 */
public class EstateForm implements Serializable {

    private String id = "0";

    private String title = "";

    private String text = "";

    private String price = "";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
