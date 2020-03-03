import cypher.*;
import java.util.*;
import static java.lang.System.*;
public class T9
{
    private static final String root = "src/dictionary/";

    private static void menu()
    {
        out.println("0. encode");
        out.println("1. decode");
        out.println("exit ");
    }

    public static void main(String [] args)
    {
        Cypher cy = new Cypher(root+"words.txt", root+"names.txt");
        Scanner s = new Scanner(in);
        String word;
        menu();
        s.nextLine();
        int choice = s.nextInt();

        switch (choice)
        {
            case 0:
                word = s.nextLine();
                cy.encode(word);
                break;
            case 1:
                out.print("$");
                word = s.nextLine();
                cy.decode(word);
                break;
            default:
                exit(0);
        }
        s.close();
    }
}
