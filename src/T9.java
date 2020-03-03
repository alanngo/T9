import cypher.*;
import java.util.*;
import java.io.*;
import static java.lang.System.*;
public class T9
{
    private static final String root = "src/dictionary/";


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
    static String[] getSources(File f)
    {
        List<String> names = new ArrayList<>();
        if (f.isDirectory())
        {
            File [] files = f.listFiles();
            assert files != null;
            for (File x: files)
            {
                names.add(root+x.getName());
            }
        }
        String [] ret = new String[names.size()];
        return names.toArray(ret);
    }
    public static void main(String [] args)
    {
        String [] source = getSources(new File(root));
        Cypher cy = new Cypher(source);
        out.println(cy.decode("2526"));
    }
}
