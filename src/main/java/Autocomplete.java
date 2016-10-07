import java.io.Serializable;

public class Autocomplete implements Serializable{
  private String name;
  private String category;
  private String id;
  private String sub_category;
  private String logo_url;

  public Autocomplete() {
  }

  public Autocomplete(String name, String category, String id, String logo_url, String sub_category) {
    this.name = name;
    this.category = category;
    this.id = id;
    this.logo_url = logo_url;
    this.sub_category = sub_category;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getLogo_url() {
    return logo_url;
  }

  public void setLogo_url(String logo_url) {
    this.logo_url = logo_url;
  }

  public String getSub_category() {
    return sub_category;
  }

  public void setSub_category(String sub_category) {
    this.sub_category = sub_category;
  }
}
