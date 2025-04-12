public class StringBufferConcatenate {
    public static String concatenateStrings(String[] strings) {
        StringBuffer sb = new StringBuffer();
        for (String str : strings) {
            sb.append(str);
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        String[] strings = {"Hello", " ", "world", "!", " ", "How", " ", "are", " ", "wee"};
        System.out.println("Concatenated String: " + concatenateStrings(strings));
    }
}
