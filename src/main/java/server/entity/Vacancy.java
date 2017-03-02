package server.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "vacancy")
public class Vacancy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private Calendar date;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "price")
    private String price;

    @Column(name = "url")
    private String url = "";

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "city_id")
    private String cityId;

    @Column(name = "export_state")
    private ExportState exportState = ExportState.NEW;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public ExportState getExportState() {
        return exportState;
    }

    public void setExportState(ExportState exportState) {
        this.exportState = exportState;
    }
}
