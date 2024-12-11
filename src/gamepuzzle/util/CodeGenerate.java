package gamepuzzle.util;

import java.util.ArrayList;
import java.util.Random;

public class CodeGenerate {
    public static String generateCode() {
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char) ('a' + i));
            list.add((char) ('A' + i));
        }

        StringBuilder code = new StringBuilder();
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            code.append(list.get(r.nextInt(list.size())));
        }

        int num = r.nextInt(10);
        code.append(num);
        char[] chars = code.toString().toCharArray();
        int index = r.nextInt(chars.length);
        char temp = chars[4];
        chars[4] = chars[index];
        chars[index] = temp;
        return new String(chars);
    }
}
