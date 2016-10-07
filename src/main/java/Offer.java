public class Offer {
  private String id;
  private String regex;
  private String tk;
  private String title;
  private boolean hasResultsOnTk;

  public void setId(String id) {
    this.id = id;
  }

  public void setRegex(String regex) {
    this.regex = regex;
  }

  public void setTk(String tk) {
    this.tk = tk;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public String getTk() {
    return tk;
  }

  public String getTitle() {
    return title;
  }

  public String getRegex() {
    return regex;
  }

  public boolean hasResultsOnTk() {
    return hasResultsOnTk;
  }

  public void setHasResultsOnTk(boolean hasResultsOnTk) {

    this.hasResultsOnTk = hasResultsOnTk;
  }

  @Override
  public String toString() {
    return "Offer{" +
        "id='" + id + '\'' +
        ", regex='" + regex + '\'' +
        ", tk='" + tk + '\'' +
        ", title='" + title + '\'' +
        ", setHasResultsOnTk=" + hasResultsOnTk +
        '}';
  }
}
