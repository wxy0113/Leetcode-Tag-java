import java.util.*;

// Most Common Word:
// Given a paragraph and a list of banned words,
// return the most frequent word that is not in the list of banned words.
// It is guaranteed there is at least one word that isn't banned,
// and that the answer is unique.

// Words in the list of banned words are given in lowercase,and free of punctuation.
// Words in the paragraph are not case sensitive. The answer is in lowercase.

class mostCommonWord {
    public static void main(String[] args) {
        String[] banned = new String[]{"hit"};
        System.out.print(mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", banned));
    }
    // Using HashMap and HashSet O(n)
    public static String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) return "";

        paragraph = paragraph.replaceAll("[!?',;.]", "");
        String[] strs = paragraph.toLowerCase().split(" ");

        Map<String, Integer> map = new HashMap<>();
        Set<String> ban = new HashSet<>(Arrays.asList(banned));

        for (String s: strs) {
            if (!ban.contains(s)) {
                map.put(s, map.getOrDefault(s, 0)+1);
            }
        }

        String res = "";
        int max = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                res = entry.getKey();
            }
        }

        return res;
    }
}