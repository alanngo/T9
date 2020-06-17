package cypher;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static cypher.Implementations.chooseWordRandom;
import static helper.IOUtilities.readFile;
import static java.util.Map.Entry;

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



    private static Collection<String> enumerate(Map<String, String> dictionary, String encoded)
    {
        if (dictionary==null|| encoded == null)
            throw new NullPointerException("dictionary is null");
        Collection<String> ret = new HashSet<>();
        for (Entry<String, String> e: dictionary.entrySet())
        {
            String og = e.getKey();
            String enc = e.getValue();
            if (encoded.equalsIgnoreCase(enc))
                ret.add(og);
        }
        return ret;
    }

    //non static helper functions/members
    /**
     * @Key: unencrypted character
     * @Value: numerical representation of the character
     * */
    private final Map<Character, Integer> encodeTable;

    /**
     * @Key: original string
     * @Value: encoded string
     * */
    private final Map<String, String> dictionary;

    //default constructor
    public Cypher()
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

    public Cypher( String ... args)
    {
        this();
        if (args== null)
            throw new NullPointerException("args is null");
        for (String arg: args)
        {
            Map<String, String> tmp = readFile(new File(arg));
            dictionary.putAll(tmp);
        }
    }

    public String encode(String str)
    {
        if (str == null)
            throw new NullPointerException("str is null");
        String message = str.toLowerCase(); //make case insensitive
        StringBuilder sb = new StringBuilder();

        for (char c: message.toCharArray())
        {
            //add normal characters
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
        if (str==null)
            throw new NullPointerException("str is null");
        StringBuilder sb = new StringBuilder();
        String[] words = str.split("0"); //split at spaces

        for (String encoded: words)
        {
            Collection<String> possibleWords = enumerate(dictionary, encoded);
            String word = chooseWordRandom(possibleWords);
            sb.append(word).append(" ");
        }
        return sb.toString();
    }

}
