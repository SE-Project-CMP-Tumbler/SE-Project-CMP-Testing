package Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Utils {
    public static List<String[]> SignUpCombineEmailData(String emailAddress) {
        String Pass = emailAddress.substring(0, 8);
        String Name = emailAddress.substring(9, 13);
        Random ran = new Random();
        String randString = new String("N" + String.valueOf(1 + ran.nextInt(500000)));
        randString += "Karim@mail.com";

        String[] emails = {"thisEmailAddressIsInvalid", "", randString};
        String[] pass = {"thisPasswordIsInvalid", "", Pass + "Aa"};
        String[] name = {"22@thisNameIsInvalid", "", "Karim"};
        String[] Age = {"12", "-1", "18"};
        List<String[]> combinations = new ArrayList<>();
        for (String e : emails) {
            for (String p : pass) {
                for (String n : name) {
                    for (String a : Age) {
                        combinations.add(new String[]{e, p, n, a});
                    }

                }

            }
        }
        return combinations;
    }
    public static List<String[]> LogInCombineEmailData() {

        String[] emails = {"invalid.com@", "", "tester11@tester.com"};
        String[] pass = {"weak", "", "tester11A"};
        ArrayList<String[]> combinations = new ArrayList<>();
        for (String e : emails) {
            for (String p : pass) {
                {
                    combinations.add(new String[]{e, p});
                }
            }
        }
        return combinations;
    }


}
