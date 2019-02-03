import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        String guesses="";
        String wordToGuess;
        wordToGuess=loadRandomWord("/Users/karthik/IdeaProjects/Hangman/src/words.txt");

        int tries=0;
        boolean win=false;

        do{
            clearConsole();
            printMessage("HANG MAN", true, true);
            drawHangMan(tries);
            printAvailableLetters(guesses);
            printMessage("Guess The Word",true,true);
            win=printWordAndCheckWin(wordToGuess,guesses);
            if(win==true)
                break;

            String currInput;
            Scanner in=new Scanner(System.in);
            System.out.print(">> ");
            currInput=in.nextLine();
            currInput=currInput.toUpperCase();

            if(!guesses.contains(currInput)){
                guesses+=currInput;
            }

            tries=triesLeft(wordToGuess,guesses);


        }while (tries<10);

        if(win==true)
            printMessage("You WON ! Ps- i knew you would win ;)",true,true);
        else {
            printMessage("Such a dissapointment to the dynasty of Geeks", true, true);
            printMessage("Word: "+wordToGuess,true,true);
        }
    }

    private static int triesLeft(String word, String guessed){
        int errors=0;
        for(int i=0;i<guessed.length();i++){
            if(!word.contains(Character.toString(guessed.charAt(i)))){
                errors++;
            }
        }
        return errors;

    }

    private static void clearConsole()
    {
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
        }
    }


    private static String loadRandomWord(String path){
        int lineCount=0;
        String word="";
        List<String> list=new LinkedList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    path));
            String line = reader.readLine();
            while (line != null) {
                list.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Random random=new Random();
        return list.get(random.nextInt(list.size()));

    }

    private static boolean printWordAndCheckWin(String word, String guess){
        boolean won=true;
        String s="";
        for(int i=0;i<word.length();i++){
            if(!guess.contains(Character.toString(word.charAt(i)))){
                won=false;
                s+="_ ";
            }else{
                s+=word.charAt(i);
                s+=" ";
            }
        }
        printMessage(s,false,true);
        return won;

    }

    private static void printLetters(String input, char from, char to){
        String s="";
        for(char i=from;i<=to;i++){
            if(!input.contains(Character.toString(i))){
                s+=i;
                s+="  ";
            }else{
                s+="  ";
            }
        }
        printMessage(s,false,false);
    }
    private static void drawHangMan(int guessCount) {
        if(guessCount>=1)
            printMessage("|",false,false);
        else
            printMessage("",false,false);

        if(guessCount>=2)
            printMessage("|",false,false);
        else
            printMessage("",false,false);
        if(guessCount>=3)
            printMessage("O",false,false);
        else
            printMessage("",false,false);

        if(guessCount==4)
            printMessage("/ ",false,false);
        if(guessCount==5)
            printMessage("/|",false,false);
        if(guessCount>=6)
            printMessage("/|\\",false,false);

        if(guessCount>=7)
            printMessage("|",false,false);
        else
            printMessage("",false,false);

        if(guessCount==8)
            printMessage("/ ",false,false);
        if(guessCount>=9)
            printMessage("/ \\",false,false);
    }

    private static void printAvailableLetters(String taken){
        printMessage("Available Letters",true, true);
        printLetters(taken,'A','I');
        printLetters(taken,'J','Q');
        printLetters(taken,'R','Z');
    }
    private static void printMessage(String msg, boolean printTop, boolean printBottom) {
        if(printTop) {
            System.out.println("+------------------------------+");
            System.out.print("|");
        }
        else
            System.out.print("|");

        boolean front=true;
        for(int i=msg.length();i<30;i++){
            if(front){
                msg=" "+msg;
            }
            else
                msg=msg+" ";
            front=!front;
        }



        System.out.print(msg);

        if(printBottom) {
            System.out.print("|");
            System.out.println();
            System.out.println("+------------------------------+");

        }
        else{
            System.out.print("|");
            System.out.println();
        }
    }

}
