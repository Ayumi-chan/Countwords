import java.util.ArrayList;
import java.util.Collections;
import java.io.*;
import java.util.regex.*;

public class Countwords {

    private ArrayList<Pair<String, Integer>> wordList;
    public Countwords(String f) {

        wordList = new ArrayList<>();

        Matcher m = Pattern.compile("(\\w+)\\s").matcher(f);
        while (m.find())
        {
            String t = m.group(1);
            boolean b = false;
            for (Pair<String, Integer> word : wordList)
            {
                if (word.first.equals(t))
                {
                    ++word.second;
                    b = true;
                    break;
                }
            }


            if (!b)
            {
                wordList.add(new Pair<>(t, 1));
            }
        }

        Collections.sort(wordList, (arg0, arg1) -> {

            //highest to lowest would have arg1 to arg0
            //vice versa of lowest to highest
            return arg1.second.compareTo(arg0.second);
        });
    }

    public int getTotalWords()
    {
        int i = 0;
        for (Pair<String, Integer> word : wordList) {
            i += word.second.intValue();
        }
        return i;
    }

    public String toString()
    {
        System.out.println("Number of unique words = " + wordList.size());
        System.out.println("Total # of words = " + getTotalWords());
        System.out.println("Top 30 words: ");
        System.out.println("\tWord\tFrequency");

        for (int i = 0; i < 30 /*wordList.size()*/; i++)
        {
            System.out.printf("%d) %10s %8d\n", i + 1, wordList.get(i).first, wordList.get(i).second);
        }

        return "";
    }


    public static void main (String[] args) throws IOException {
        @SuppressWarnings("resource")
        BufferedReader b = new BufferedReader(new FileReader(new File("dream.txt")));

        String f = "";
        String l;
        while ((l = b.readLine()) != null)
        {
            f += l + " ";
        }
        //\w == a-zA-Z0-9 regex
        f = f.replaceAll("[^\\w ]", "").toLowerCase();

        System.out.println(new Countwords(f));
    }
}
