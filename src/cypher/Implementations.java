package cypher;

import com.sun.istack.internal.NotNull;

import java.util.Collection;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.*;

public class Implementations
{
    /***different implementations for decoding algorithm**/

    //user choice
    static String chooseWordUser(@NotNull Collection<String> possibleWords)
    {
        if (possibleWords.size()==1)
        {
            String [] singleton = new String[possibleWords.size()];
            return possibleWords.toArray(singleton)[0];
        }
        Scanner s = new Scanner(in);

        out.print("Choose a word from "+possibleWords+": ");
        String word = s.nextLine();

        if (possibleWords.contains(word))
            return word;

        s.close();
        return "";
    }

    //random using dictionary
    static String chooseWordRandom(@NotNull Collection<String> possibleWords)
    {
        Random r = new Random();
        String ret;
        String [] arr = new String [possibleWords.size()];
        arr = possibleWords.toArray(arr);
        ret = arr[r.nextInt(arr.length)];
        return ret;
    }

}
