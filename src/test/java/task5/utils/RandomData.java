package task5.utils;

import aquality.selenium.elements.interfaces.ICheckBox;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RandomData {
    private static final String UPP_CASE_LATTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public RandomData() {
    }

    public static String randomString(int length) {

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            int a = (int)(33 + Math.random() * 223);
            if (a >= 127 && a <= 160) {
                i--;
            } else {
                sb.append((char)a);
            }
        }
        return sb.toString();
    }

    public static ArrayList<ICheckBox> randomInterest(List<ICheckBox> checkBoxes, int amountInterests) {
        Set<ICheckBox> result = new HashSet<>();
        while(result.size() < amountInterests) {
            int randomInterest = (int)(Math.random() * checkBoxes.size());
            String interest = (checkBoxes.get(randomInterest)).getText();
            if (!interest.equals("Unselect all") && !interest.equals("Select all")) {
                result.add(checkBoxes.get(randomInterest));
            }
        }
        return new ArrayList<>(result);
    }

    public static char randomUpperCaseLatter() {
        return UPP_CASE_LATTER.charAt((int) (Math.random()*UPP_CASE_LATTER.length()));
    }

    public static int randomFigure() {
        return (int)(Math.random() * 10);
    }

    public static String[] randomPassEmail() {
        StringBuilder password = new StringBuilder();
        StringBuilder email = new StringBuilder();

        char generalLatter = randomUpperCaseLatter();

        password.append(randomString(10 + randomFigure()));
        password.append(generalLatter);
        password.append(randomFigure());

        email.append(randomString(10 + randomFigure()));
        email.append(generalLatter);

        return new String[]{password.toString(), email.toString()};
    }
}

