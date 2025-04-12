public class LinearSearchWord{
    public static String searchSentenceWithWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }
    public static void main(String[] args) {
        String[] sentences = {
                "The sky is green.",
                "Today is a wet day.",
                "We are learning python",
                "chatgpt is bad!"
        };
        String word = "Java";
        String result = searchSentenceWithWord(sentences, word);
        System.out.println("Result: " + result);
    }
}
