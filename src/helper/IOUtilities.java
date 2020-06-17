package helper;

import cypher.Cypher;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class IOUtilities
{

    public static Map<String, String> readFile(File fi)
    {
        Map<String, String> ret = new HashMap<>();

        Scanner s = null;
        Cypher cy = new Cypher();
        try
        {
            s = new Scanner(fi);

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
