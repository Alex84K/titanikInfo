package ait.titan;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TitanStatistickAppl {
    public static void main(String[] args) {

        double allFare = 0;
        double allTarif1 = 0;
        double allTarif2 = 0;
        double allTarif3 = 0;
        int quantity1class = 0;
        int quantity2class = 0;
        int quantity3class = 0;
        int survived = 0;
        int nonSurvived = 0;
        int survivedMan = 0;
        int survivedWoman = 0;
        int survivedChilds = 0;

        try (BufferedReader br =new BufferedReader(new FileReader("train.csv"))){
            String str = br.readLine();
            //System.out.println(str);
            String[] cells = str.split(";");
            printCells(cells);

            /*
             Calculate total fares.
             Calculate average fare for 1,2,3 classes of travel.
             Calculate total quantity of survived and non survived passengers.
             Calculate total quantity of survived and non survived men, women and children(under 18 years old).
             * */


            str = br.readLine();
            while (str != null) {
                cells = str.split(",");

                //printCells(cells);
                str = br.readLine();

                allFare += Double.parseDouble(cells[10]);//Calculate total fares.

                double age = 0;
                if (cells[6] != "") {
                    age = Double.parseDouble(cells[6]);
                } else {
                    age = 0;
                }

                //total quantity of survived and non survived men, women and children
                if(cells[1].equals("1") && age < 18) {
                    survivedChilds++;
                }
                if(cells[1].equals("1") && cells[5].equals("male") && age >= 18) {
                    survivedMan++;
                }
                if(cells[1].equals("1") && cells[5].equals("female") && age >= 18) {
                    survivedWoman++;
                }

                //Calculate average fare for 1,2,3 classes of travel.
                switch (cells[2]) {
                    case "1":
                        quantity1class++;
                        allTarif1 += Double.parseDouble(cells[10]);
                        break;
                    case "2":
                        quantity2class++;
                        allTarif2 += Double.parseDouble(cells[10]);
                        break;
                    case "3":
                        quantity3class++;
                        allTarif3 += Double.parseDouble(cells[10]);
                        break;
                }

                //Calculate total quantity of survived and non survived passengers.
                if(cells[1].equals("1")) {
                    survived++;
                } else {
                    nonSurvived++;
                }
            }
            ////==== OUT ====////
            System.out.println("========================");
            System.out.println("All Fare: " + allFare);
            System.out.println("========================");
            System.out.println("average fare for 1 classes: " + allTarif1 / quantity1class);
            System.out.println("average fare for 2 classes: " + allTarif2 / quantity2class);
            System.out.println("average fare for 3 classes: " + allTarif3 / quantity3class);
            System.out.println("========================");
            System.out.println("survived passengers: " + survived);
            System.out.println("non survived passengers: " + nonSurvived);
            System.out.println("========================");
            System.out.println("survived man: " + survivedMan);
            System.out.println("survived woman: " + survivedWoman);
            System.out.println("survived childs: " + survivedChilds);
            System.out.println("========================");

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printCells(String[] cells) {
        for (String s: cells) {
            System.out.print(s + "\t");
        }
        System.out.println();
    }
}
