package helper;

import cypher.*;
import org.jetbrains.annotations.*;

import java.io.*;
import java.util.*;

public class IOUtilities
{
    @NotNull
    public static Map<String, String> readFile(File fi)
    {
        Map<String, String> ret = new HashMap<>();

        Scanner s = null;
        Cypher cy;
        try
        {
            s = new Scanner(fi);
            cy = new Cypher();

            while (s.hasNextLine())
            {
                String str = s.nextLine();
                ret.put(str, cy.encode(str));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            assert s != null;
            s.close();
        }
        return ret;
    }

}
