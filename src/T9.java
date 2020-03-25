import cypher.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.io.*;
import static java.lang.System.*;
public class T9
{
    static final String root = "src/dictionary/";

    private static void handleChoices(Cypher cy, int choice)
    {
        Scanner s = new Scanner(in);
        String word;
        switch (choice)
        {
            case 0:
                out.print("encode: ");
                word = s.nextLine();
                out.println(cy.encode(word));
                break;
            case 1:
                out.print("decode: ");
                word = s.nextLine();
                out.println(cy.decode(word));
                break;
            default:
                exit(0);
        }
        s.close();
    }
    @NotNull
    static String[] getSources(@NotNull File f)
    {
        List<String> names = new ArrayList<>();
        if (f.isDirectory())
        {
            File [] files = f.listFiles();
            assert files != null;
            for (File x: files)
                names.add(root+x.getName());

        }
        return names.toArray(new String[names.size()]);
    }
    public static void main(String [] args)
    {
        String testTxt = root+"test.txt";
        String [] source = getSources(new File(root));
        Cypher cy = new Cypher(source);
        out.println(cy.encode("apples and oranges"));
        out.println(cy.decode("2526"));
    }
}
