import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
        public static List<List<String>> groupStrings(String[] strings) {

            HashMap<String, String> stringMap = new HashMap<>();

            for (String string : strings) {
                int diff = (string.charAt(0) - 96) - 1;
                StringBuilder newString = new StringBuilder();
                for (int i = 0; i < string.length(); i++) {
                    newString.append((char) (string.charAt(i) - diff));
                }
               stringMap.put(newString.toString(), string);
                if (temp == null) {
                    temp = new ArrayList<>();
                }
                temp.add(string);
            }

            List<List<String>> ans = new ArrayList<>();
            for (Map.Entry<String, List<String>> map : stringMap.entrySet()) {
                ans.add(map.getValue());
            }

            return ans;
        }
}

