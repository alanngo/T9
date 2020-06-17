import com.sun.istack.internal.NotNull;
import cypher.Cypher;

import java.io.File;

import static java.lang.System.err;
import static java.lang.System.out;
public class TestT9
{
    private static int count = 0;
    private static void TEST(@NotNull String result, String actual)
    {
        if (result.equalsIgnoreCase(actual+" "))
            count++;
        else
            err.println("TEST FAILED! Expected "+actual+", Result: "+result);
    }

    public static void main(String[] args)
    {
        String [] source = T9.getSources(new File(T9.root));
        Cypher cy = new Cypher(source);

        TEST(cy.decode("277537026306726437"), "apples and oranges");
        TEST(cy.decode("2526"), "Alan");
        out.println("Accuracy: "+count);
    }

}
