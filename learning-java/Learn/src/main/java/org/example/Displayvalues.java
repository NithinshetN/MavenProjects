package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;

public class Displayvalues extends Thread{
    private int score=0;
    public int show(@NotNull List<Quiz> quizList, Scanner sc){
        for (Quiz qz : quizList) {
            System.out.println("\n"+qz.getId()+". " + qz.getQuestion());
            System.out.println("Options:");
            int i=1;
            for (String option : qz.getOption()) {

                System.out.println((i++)+"."+option);
            }
            System.out.println("Enter you Option:");
            int answer = sc.nextInt();
            if(answer>=0&&answer<5){
                if(answer ==qz.getAnswer()){
                    score++;
                }
            }else{
                System.out.println("Enter the option between 1 and 4");
            }

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
        return score;
    }
    public void DisplayAnswer(@NotNull List<Quiz> quizList) {
        for (Quiz qz : quizList) {
            System.out.println("\n" + qz.getId() + ". " + qz.getQuestion());
            System.out.println("Options:");
            int i = 1;
            for (String option : qz.getOption()) {

                System.out.println((i++) + "." + option);
            }

            System.out.println("Answer:" + qz.getOption().get(qz.getAnswer()-1));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
