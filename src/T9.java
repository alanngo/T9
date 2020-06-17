import com.sun.istack.internal.NotNull;
import cypher.Cypher;

import java.io.*;
import java.util.*;

import static java.lang.System.*;
public class T9
{
    static final String root = "src/dictionary/";

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
        return names.toArray(new String[0]);
    }
    public static void main(String [] args)
    {
        String [] source = getSources(new File(root));
        Cypher cy = new Cypher(source);
        out.println(cy.encode("apples and oranges"));
        out.println(cy.decode("222"));
    }
}
