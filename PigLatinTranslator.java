
import javax.swing.*;
import java.util.Scanner;
import java.io.*;

public class PigLatinTranslator
{

    public static String stringToPigLatin(String toBeTranslated)
    {
        String currentWord;
        String alteredWord = "";
        String completedTranslation = "";

        char currentFirstLetter;
        char punctuation;

        String[] allWholeWords = toBeTranslated.split(" ");

        for (int i = 0; i < allWholeWords.length; i++)
        {
            currentWord = allWholeWords[i];
            currentFirstLetter = currentWord.charAt(0);

            //if first letter is vowel, don't move anything, add yay
            if (beginsWithVowel(currentFirstLetter))
            {
                //if word ends in punctuation, add before punctuation
                if(endsInPunctuation(currentWord))
                {
                    punctuation = currentWord.charAt(currentWord.length() - 1);
                    alteredWord = currentWord.substring(0, currentWord.length() - 1) + "-yay" + punctuation;
                    completedTranslation += alteredWord;
                }
                else
                {
                    alteredWord = currentWord + "-yay";
                    completedTranslation += alteredWord + " ";
                }
            }

            //if first letters are consonant cluster, move cluster to back of word
            //if word ends in punctuation, add before punctuation
            else if(beginsWithConsonantCluster(currentWord))
            {
                String consonantCluster = getFirstConsonantCluster(currentWord);

                if(endsInPunctuation(currentWord))
                {
                    punctuation = currentWord.charAt(currentWord.length() - 1);
                    if(consonantCluster.length() == 2)
                    {
                        alteredWord = currentWord.substring(2, currentWord.length() - 1) + "-" + consonantCluster + "ay" + punctuation;
                    }
                    if(consonantCluster.length() == 3)
                    {
                        alteredWord = currentWord.substring(3, currentWord.length() - 1) + "-" + consonantCluster + "ay" + punctuation;
                    }
                    completedTranslation += alteredWord;
                }
                else
                {
                    if(consonantCluster.length() == 2)
                    {
                        alteredWord = currentWord.substring(2, currentWord.length()) + "-" + consonantCluster + "ay";
                    }
                    if(consonantCluster.length() == 3)
                    {
                        alteredWord = currentWord.substring(3, currentWord.length()) + "-" + consonantCluster + "ay";
                    }
                    completedTranslation += alteredWord + " ";
                }
            }

            //else just move single letter to back of word, and add first letter + "ay"
            //if word ends in punctuation, add before punctuation
            else
            {
                if(endsInPunctuation(currentWord))
                {
                    punctuation = currentWord.charAt(currentWord.length() - 1);
                    alteredWord = currentWord.substring(1, currentWord.length() - 1) + "-" + currentFirstLetter + "ay" + punctuation;
                    completedTranslation += alteredWord;
                }
                else
                {
                    alteredWord = currentWord.substring(1, currentWord.length()) + "-" + currentFirstLetter + "ay";
                    completedTranslation += alteredWord + " ";
                }
            }
        }

        return completedTranslation;
    }

    private static boolean endsInPunctuation(String word)
    {
        if(word.charAt(word.length() - 1) == '!' || word.charAt(word.length() - 1) == '?' || word.charAt(word.length() - 1) == '.' || word.charAt(word.length() - 1) == ',' || word.charAt(word.length() - 1) == ':' || word.charAt(word.length() - 1) == ';')
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private static boolean beginsWithVowel(char firstLetter)
    {
        if (firstLetter == 'a' || firstLetter == 'e' || firstLetter == 'i' || firstLetter == 'o' || firstLetter == 'u' || firstLetter == 'A' || firstLetter == 'E' || firstLetter == 'I' || firstLetter == 'O' || firstLetter == 'U')
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    private static boolean beginsWithConsonantCluster(String word)
    {
        if (word.length() >= 3)
        {
            String firstTwoLetters = word.substring(0, 2);
            String firstThreeLetters = word.substring(0, 3);


            if (firstThreeLetters.matches("(?i)(?=[a-xz]{3})[^aeiouy]{3}"))
            {
                return true;
            }
            else if (firstTwoLetters.matches("(?i)(?=[a-xz]{2})[^aeiouy]{2}"))
            {
                return true;
            }
        }
        return false;
    }

    private static String getFirstConsonantCluster(String word)
    {
        String firstTwoLetters = word.substring(0, 2);
        String firstThreeLetters = word.substring(0, 3);

        if(firstThreeLetters.matches("(?i)(?=[a-xz]{3})[^aeiouy]{3}"))
        {
            return firstThreeLetters;
        }
        else
        {
            return firstTwoLetters;
        }
    }
}
