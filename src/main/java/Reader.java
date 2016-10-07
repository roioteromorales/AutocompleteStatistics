import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Reader {


  public static final String AUTOCOMPLETE_URL = "https://ms-autocomplete.spain.schibsted.io/professions/";
  public static final String CSV = "exportmini.csv";

  public static void main(String[] args) throws IOException, UnirestException {
    String[] split = getTextFromFile();
    List<Offer> offerList = getOffers(split);

    int notFound = 0;
    int hasResults = offerList.size();
    for (Offer offer : offerList) {
      if ("NOT_FOUND_IN_DICTIONARY".equals(offer.getRegex())) {
        notFound++;
      }
      if (offer.hasResultsOnTk()) {
        hasResults--;
      }
    }

    offerList.forEach(System.out::println);
    System.out.println("Total offers: " + offerList.size());
    System.out.println("Not Found: " + notFound);
    System.out.println("% of not found: " + ((float) notFound / (float) offerList.size()) * 100f);

    System.out.println("Without results on Tk: " + hasResults);
    System.out.println("% of Without results on Tk: " + ((float) hasResults / (float) offerList.size()) * 100f);

  }

  private static List<Offer> getOffers(String[] split) throws UnirestException, IOException {
    List<Offer> offerList = new ArrayList<>();
    for (String s : split) {

      String[] fields = s.split("SEPARATOR");

      String id = fields[0].split(":")[1];
      String tk = fields[1].split(":")[1];
      String regex = fields[2].split(":")[1];
      String title = fields[3].split(":")[1];

      Offer offer = new Offer();
      offer.setId(id);
      offer.setTk(tk);
      offer.setRegex(regex);
      offer.setTitle(title);
      offer.setHasResultsOnTk(hasValuesInTk(tk));
      offerList.add(offer);
    }
    return offerList;
  }

  private static boolean hasValuesInTk(String keyword) throws UnirestException, IOException {
    System.out.println(keyword);
    String replace = keyword.replace("/", "%2F");
    System.out.println(replace);
    String response = Unirest.get(AUTOCOMPLETE_URL + replace).asJson().getBody().toString();

    System.out.println(response);
    ObjectMapper mapper = new ObjectMapper();
    List<Autocomplete> autocompleteValues = mapper.readValue(response, new TypeReference<List<Autocomplete>>() { });

    for (Autocomplete autocompleteValue : autocompleteValues) {
      if (autocompleteValue.getName().equals(keyword)) {
        System.out.println("keyword matched!! : " + keyword);
        return true;
      }
    }

    return false;
  }

  private static String[] getTextFromFile() {
    String file = new FileToString().getFile(CSV);
    file = file.replace("ET_EXTRA_INFO", "");
    file = file.replace(",\"\"", "SEPARATOR");
    file = file.replace("\"", "");
    file = file.replace("{", "");
    file = file.replace("}", "");
    return file.split("\n");
  }

//// TODO: 06/10/16
// CSV output
// Titulo y puesto vacante igual
//

// MANUAL para el excel: medir improvement de text kernel vs regex

}


