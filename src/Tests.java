import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Tests {
    @Test
    public void testSearch()
    {
        String[] testPhrases = {"hello", "world", "Seattle", "hello world", "SEATTLE RAINS", "llo wor"};
        int[][] expected = {
                {0,1,3,4,5},
                {0,2,3,4,5},
                {4},
                {0,5},
                {4},
                {}
        };
        for (int i = 0; i < expected.length; i++)
        {
            List<Integer> results = search(testPhrases[i]);
            if (results == null)
                assertTrue(expected[i].length == 0);
            else
                for (int x = 0; x < results.size(); x++)
                    assertTrue(expected[i][x] == results.get(x));
        }
    }
    public List<Integer> search(String keyPhrase)
    {
        String[] documents = {"hello world", "hello", "world", "world world hello", "seattle rains hello abc world", "sunday hello world fun"};
        Dictionary dictionary = new Dictionary(100);
        for (int i = 0; i < documents.length; i++)
        {
            String[] splitDocument = documents[i].split(" ");
            int sum = 0;
            for (int z = splitDocument.length; z > 0; z--)
                sum += z;
            List<String> allPhrases = new ArrayList<String>();
            for (int x = 0; x < splitDocument.length; x++)
            {
                allPhrases.add(splitDocument[x]);
                for (int y = x + 1; y < splitDocument.length; y++)
                    allPhrases.add(allPhrases.get(allPhrases.size() - 1) + " " + splitDocument[y]);
            }
            for (int x = 0; x < allPhrases.size(); x++)
            {
                if (dictionary.contains(allPhrases.get(x))) {
                    List<Integer> value = dictionary.get(allPhrases.get(x));
                    if (value.get(value.size() - 1) != i) //making sure it does not repeat documents in value list
                        value.add(i);
                }
                else {
                    List<Integer> tempList = new ArrayList<Integer>();
                    tempList.add(i);
                    dictionary.add(allPhrases.get(x), tempList);
                }
            }
        }
        return dictionary.get(keyPhrase.toLowerCase());
    }
}
