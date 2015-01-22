import java.io.*;
import java.util.*;
import java.util.EnumMap;
/**
 * Created by stevenw2 on 22/01/15.
 */
public class BadWordsChecker {

    public static Map badWordMap;

    public static void checkFile(File file)
    {
        try
        {
            int lineNum = 0;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String input = "";

            while( (input = br.readLine()) != null)
            {
                StringTokenizer strtok = new StringTokenizer(input);
                while (strtok.hasMoreElements())
                {
                    String nextToken = strtok.nextToken();
                    if(badWordMap.containsKey(nextToken))
                    {
                        System.err.println(file.getAbsoluteFile() + " found instance of " + nextToken + " on line " +lineNum+ " " + input);
                    }
                }
                lineNum++;
            }


        }
        catch (Exception e)
        {
            //e.printStackTrace();
        }

    }

    public static void findFiles(File[] files)
    {
        for(File file : files)
        {
            if (file.isDirectory())
            {
                findFiles(file.listFiles());
            }
            else
            {
                //System.out.println("Found file "+ file.getAbsoluteFile());
                checkFile(file);
            }
        }
    }

    public static void main (String[] args) throws Exception
    {

        System.out.println("Steve's bad words checker");

        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        badWordMap = new HashMap(10000);

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("badwords.txt")));
        String in = "";

        while((in = br.readLine()) != null)
        {
            badWordMap.put(in,in);
        }

        if(args.length != 1)
        {
            System.out.println("Usage : BadwordsChecker <directory>");
            System.out.println("        where <directory> is top level directory to search files in for bad words");
        }
        else
        {
            File topLevelDir = new File(args[0]);
            findFiles(topLevelDir.listFiles());
        }

    }
}
