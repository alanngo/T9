package cypher;


import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import static java.util.Map.Entry;
import static java.lang.System.*;
import static helper.IOUtilities.*;

public class Cypher
{
    //static helper functions/members
    private static final String[] LETTERS = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    private static boolean IS_STRAY_CHARS(char c)
    {
        String STRAY_CHAR ="!@#$%^&*()-+,.?<>/";
        for (char ch: STRAY_CHAR.toCharArray())
        {
            if (c==ch)
                return true;
        }
        return false;
    }

    @NotNull
    private static Collection<String> enumerate(@NotNull Map<String, String> dictionary, String encoded)
    {
        Collection<String> ret = new ArrayList<>();
        for (Entry<String, String> e: dictionary.entrySet())
        {
            String og = e.getKey();
            String enc = e.getValue();
            if (encoded.equalsIgnoreCase(enc))
                ret.add(og);
        }
        return ret;
    }
    private static String chooseWord(@NotNull Collection<String> possibleWords)
    {
        Scanner s = new Scanner(in);

        out.print("Choose a word from "+possibleWords+": ");
        String word = s.nextLine();

        if (possibleWords.contains(word))
            return word;

        s.close();
        return "";
    }


    //non static helper functions/members
    /**
     * @Key: unencrypted character
     * @Value: numerical representation of the character
     * */
    private Map<Character, Integer> encodeTable;

    /**
     * @Key: original string
     * @Value: encoded string
     * */
    private Map<String, String> dictionary;


    //package constructor
    Cypher()
    {
        encodeTable = new HashMap<>();
        dictionary = new HashMap<>();

        //spaces count as 0
        encodeTable.put(' ', 0);

        //populate the rest of the letters
        for (int i =0; i<LETTERS.length; i++)
        {
            String s = LETTERS[i];

            for (char c: s.toCharArray())
               encodeTable.put(c, i+2);
        }
    }

    public Cypher(@NotNull String ...args)
    {
        this();
        for (String arg: args)
        {
            Map<String, String> tmp = readFile(new File(arg));
            dictionary.putAll(tmp);
        }
    }

    public String encode(String str)
    {
        String message = str.toLowerCase();
        StringBuilder sb = new StringBuilder();
        for (char c: message.toCharArray())
        {
            if (encodeTable.containsKey(c))
                sb.append(encodeTable.get(c));

            //add stray characters like ,.?
            if (IS_STRAY_CHARS(c))
                sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Decodes from T9 encryption
     * @param str the encoded T9 string
     * @return the decoded string
    * */
    public String decode(String str)
    {
        StringBuilder sb = new StringBuilder();
        String[] words = str.split("0"); //split at spaces

        for (String encoded: words)
        {
            Collection<String> possibleWords = enumerate(dictionary, encoded);
           String word = chooseWord(possibleWords);
           sb.append(word).append(" ");
        }
        return sb.toString();
    }

}
