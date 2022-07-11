import java.util.Scanner;
import java.io.File;
import java.io.IOException;

//Курсов проект "Система за отпуски"
public class Main {
    public static void printMenu() {
        String menu = "--------------------------------------------------------------------\n" +
                "1. Заяви отпуска\n" +
                "2. Виж всички отпуски\n" +
                "3. Виж отпуска за служител\n" +
                "4. Промени статус на отпуска\n" +
                "5. Изход\n" +
                "-------------------------------------------------------------------\n";
        System.out.println(menu);
    }

    //При избиране на 1, потребителят въвежда последователно
//     Име, имейл, ЕГН, две дати - за начало и край на отпуската, тип на отпуската - платена или неплатена.
//     При грешен формат на данните да излизат подходящи съобщения.
    public static String[] inputLeaveData() {
        Scanner scan = new Scanner(System.in);
        String[] employeeData = new String[6];
        System.out.print("Name: ");
        employeeData[0] = scan.nextLine();
        System.out.print("email: ");
        employeeData[1] = scan.nextLine();
        System.out.print("personalIDNum: ");
        employeeData[2] = scan.nextLine();
        System.out.print("startDate (dd-mm-yyyy): ");
        employeeData[3] = scan.nextLine();
        System.out.print("endDate (dd-mm-yyyy): ");
        employeeData[4] = scan.nextLine();
        System.out.print("typeOfLeave (paid, unpaid): ");
        employeeData[5] = scan.nextLine();

        return employeeData;
    }

    //При избиране на 2, на екрана се показват във формата на таблица, всички         направени до сега заявки.

    //    KAK???? Всички заявки се помнят при изключване на програмата.
    public static String[][] fillMatrixLeaveData(int n) {
        //n брой заявени отпуски
        //matrixLeaveData да е ArrayList от масив от стрингове или n да е 1000
        //параметър за поредната попълвана отпуска, който ще се пази в main

        String[][] matrixLeaveData = new String[n][6];
        String[] employeeData;

        for (int i = 0; i <n; i++) {
            employeeData= inputLeaveData();
            for (int j = 0; j < 6; j++) {
                matrixLeaveData[i][j] = employeeData[j];
            }
        }
        return matrixLeaveData;
    }

    public static void printAllLeaveRequests(String[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + "   ");
            }
            System.out.println();
        }
    }

    //При избиране на 3, потребителят въвежда името на служител и на екрана излиза таблица,
    //     но само със заявките за този служител.
    public static void printEmlpoyeeLeaveRequests(String name, String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (name.equals(matrix[i][0])) {
                    System.out.print(matrix[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }
/*Всички заявки да си имат уникален номер. Към таблиците от опция 2 и 3 да се добави и нова колона “Статус”.
     Статусът може да бъде approved, rejected or pending. При избиране на 4, излиза таблица подобна на опция 2,
     но с допълнителна колона - номер на заявката. Потребителят може да въведе номер на заявка и статус,
     което ще промени статуса на съответната заявка.

    public static String [] [] copiesMatrix(int numEmployes, String[][] matrixLeaveData) {
        String [] [] newMatrix = new String[numEmployes][matrixLeaveData[0].length+3];
        for (int i=0; i< matrixLeaveData.length; i++) {
            for (int j = 0; j < matrixLeaveData[0].length; j++) {
               newMatrix[i][j]=matrixLeaveData[i][j];
                }
            }
            System.out.println();
        }

    }

 */


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Система за заявяване на отпуски\nВъведи брой служители в компанията: ");

        int numEmployes = scan.nextInt();
        String[] employee = new String[6];
        String[][] matrix = new String[numEmployes][6];
        String name;
        String fake;

        /*try {
            File file = new File("C:\\newfile.txt");
            boolean isCreated = file.createNewFile();
            if (isCreated) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
            }
        } catch (IOException e) {
            System.out.println("Exception Occurred:");
            e.printStackTrace();
        }*/

        printMenu();
        System.out.print("Въведи избор: ");
        int choice = scan.nextInt();
        int i = 0;
        while (choice != 5) {
            switch (choice) {
                case 1:
                    matrix = fillMatrixLeaveData(numEmployes);
                    break;
                case 2:
                    printAllLeaveRequests(matrix);
                    break;
                case 3:
                    System.out.print("Въведи име на служител: ");
                    fake = scan.nextLine();
                    name = scan.nextLine();
                    printEmlpoyeeLeaveRequests(name, matrix);
                    break;
                case 4:

            }
            System.out.print("Въведи избор: ");
            choice = scan.nextInt();
        }


    }
}