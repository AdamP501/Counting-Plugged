import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Word> words = new ArrayList<Word>();
        ArrayList<String> commonWords = new ArrayList<String>(0);


        //populate the commonWords arraylist
        File file1 = new File("commonWords.txt");
        Scanner scanner1 = new Scanner(file1);
        while (scanner1.hasNextLine()) {
            commonWords.add(scanner1.nextLine());
        }


        File file2 = new File("textOne.txt");
        Scanner scanner2 = new Scanner(file2);

        while (scanner2.hasNextLine()) {


            String regex = "[!._,'@? ]";
            String[] line = scanner2.nextLine().split(regex);


            for (String word : line){
                if (word.isEmpty()) continue;

                boolean wordExists = false;
                boolean wordCommon = false;
                Word newWord = null;
                int index = 0;

                //check if the word is already in the arraylist
                for (int i = 0; i < words.size(); i ++){
                    if (word.toLowerCase().equals(words.get(i).getWord().toLowerCase())){
                        wordExists = true;
                        index = i;
                    }
                }

                //check if the word is a common word
                for (int i = 0; i < commonWords.size(); i++){
                    if (word.toLowerCase().equals(commonWords.get(i).toLowerCase())){
                        wordCommon = true;
                    }
                }

                if (wordExists && !wordCommon){
                    words.get(index).setCount(words.get(index).getCount()+1);
                }

                else if (!wordCommon){
                    newWord = new Word(word, 1);
                    words.add(newWord);
                }
            }
        }

        Word temp;

        //bubble sort the words from highest to lowest
        for (int i = 0; i < words.size(); i++)
        {
            for (int j = 0; j < words.size()-1; j++)
            {
                if (words.get(j).getCount() < (words.get(j+1).getCount()))
                {
                    temp = words.get(j);
                    words.set(j, words.get(j+1));
                    words.set(j+1, temp);
                }
            }
        }

        for (Word word : words){
            System.out.println(word);
        }

    }


}
