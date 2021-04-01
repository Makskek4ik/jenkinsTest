package task8.utils;

public class RandomData {
    private static final String UPP_CASE_LATTER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER_CASE_LATTER = "abcdefghijklmnopqrstuvwxyz";
    private static final String ALL_FIGURE = "123456789";

    public RandomData() {
    }

    public static String randomString(int length) {
        String symbol = UPP_CASE_LATTER + LOWER_CASE_LATTER+ALL_FIGURE;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(symbol.charAt((int) (Math.random()*symbol.length())));
        }
        return sb.toString();
    }
}

