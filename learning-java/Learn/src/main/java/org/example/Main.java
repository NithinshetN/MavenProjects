package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Displayvalues ds=new Displayvalues();
        Scanner sc=new Scanner(System.in);
        PersonalInformation pi=new PersonalInformation();
        String[] str={"gk_quiz.json","java_quiz.json","sport_quiz.json"};
        JsonMapper js=new JsonMapper();

        System.out.println("Enter you name");
        pi.setName(sc.nextLine());

        System.out.println("Select any options");
        System.out.println("1.gk_quiz.json  2.java_quiz.json  3.sport_quiz.json");
        pi.setOptions(sc.nextInt());

        try{
            int opt= pi.getOptions();
            List<Quiz> quizList=js.display(str[opt-1]);
            int score=ds.show(quizList,sc);

            String name= pi.getName();
            System.out.println("\n"+name+"!!\nThank you for participating in the quiz.");
            System.out.println("Your score:"+score);
            System.out.println("\nDo you want to know the answers:\n(y-yes|n-no)");
            char c=sc.next().charAt(0);
            if(c==('Y'|'y')){
            ds.DisplayAnswer(quizList);
            }else{
                System.out.println("Thank you");
            }

        }catch (IndexOutOfBoundsException e) {System.out.println("Enter the correct option and Try again");}
        catch(Exception e) {System.out.println("Internal error"+e);}
    }
}