
import java.util.ArrayList;
import java.util.Scanner;


//Курсов проект "Система за отпуски"
public class Main {
    public static void printMenu() {
        String menu = "--------------------------------------------------------------------\n" +
                "1. Request leave\n" +
                "2. See all leaves\n" +
                "3. See an employee leave\n" +
                "4. Change leave status\n" +
                "5. Exit\n" +
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
        System.out.print("personalIDNum: "); //провери дали дължината на този стринг е от 10 знака (всяко ЕГН съдържа 10 знака)
        employeeData[2] = scan.nextLine();
        while (employeeData[2].length() != 10) {
            System.out.println("Invalid PIN");
            employeeData[2] = scan.nextLine();
        }
        System.out.print("startDate (dd-mm-yyyy): ");
        employeeData[3] = scan.nextLine();
        System.out.print("endDate (dd-mm-yyyy): ");
        employeeData[4] = scan.nextLine();
        System.out.print("typeOfLeave (paid, unpaid): ");
        employeeData[5] = scan.nextLine();

        return employeeData;
    }

    //При избиране на 2, на екрана се показват във формата на таблица, всички         направени до сега заявки.
    //   Всички заявки се помнят при изключване на програмата.
    public static String[][] fillMatrixLeaveData(int numLeaveRequests) {
        //n брой заявени отпуски
        String[][] matrixLeaveData = new String[numLeaveRequests][8];
        String[] employeeData;
        int idLeaveRequest = 0;
        for (int i = 0; i < numLeaveRequests; i++) {
            employeeData = inputLeaveData();
            for (int j = 0; j < 6; j++) {
                matrixLeaveData[i][j] = employeeData[j];
            }
            idLeaveRequest++;
            matrixLeaveData[i][6] = Integer.toString(idLeaveRequest);
            matrixLeaveData[i][7] = "pending";
        }
        return matrixLeaveData;
    }

    public static void printAllLeaveRequests(String[][] matrixLeaveData) {
       /* String [] matrixHeader={"Name", "email","PIN","start date", "end date", "type: paid/unpaid"};
        for (String m : matrixHeader) {
            System.out.print(m + "; ");
        }
        System.out.println( );*/
        for (int i = 0; i < matrixLeaveData.length; i++) {
            for (int j = 0; j < matrixLeaveData[0].length - 2; j++) {
                System.out.print(matrixLeaveData[i][j] + "   ");
            }
            System.out.println();
        }
    }

    public static void printAllLeaveRequestsWithIdAndStatus(String[][] matrixLeaveData) {
       /* String [] matrixHeader={"Name", "email","PIN","start date", "end date", "type: paid/unpaid", "idLeaveRequest", "Status"};
        for (String m : matrixHeader) {
            System.out.print(m + "; ");
        }
        System.out.println( );*/
        for (int i = 0; i < matrixLeaveData.length; i++) {
            for (int j = 0; j < matrixLeaveData[0].length; j++) {
                System.out.print(matrixLeaveData[i][j] + "   ");
            }
            System.out.println();
        }
    }

    //При избиране на 3, потребителят въвежда името на служител и на екрана излиза таблица,
    //     но само със заявките за този служител.
    public static void printEmlpoyeeLeaveRequests(String name, String[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < 6; j++) {
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
         */

    public static void printMatrixWithlIDAndStatus(int numLeaveRequests, String[][] matrixWithIDAndStatus) {
        printAllLeaveRequestsWithIdAndStatus(matrixWithIDAndStatus);

        Scanner scan = new Scanner(System.in);
        System.out.println("Start change leave requests status. For exit enter 0:");
        System.out.print("Enter leave request id:");
        int leaveRequestId = scan.nextInt();
        System.out.print("Change leave request status (approved or rejected):");
        String fake = scan.nextLine();
        String leaveRequestStatus = scan.nextLine();

        do {
            for (int i = 0; i < matrixWithIDAndStatus.length; i++) {
                for (int j = 0; j < matrixWithIDAndStatus[0].length; j++) {
                    if (matrixWithIDAndStatus[i][6].equals(Integer.toString(leaveRequestId))) {
                        matrixWithIDAndStatus[i][7] = leaveRequestStatus;
                    }
                }
            }
            System.out.print("Enter leave request id:");
            leaveRequestId = scan.nextInt();
            fake = scan.nextLine();
            System.out.print("Change leave request status (approved or rejected):");
            leaveRequestStatus = scan.nextLine();
        } while (leaveRequestId != 0);

        printAllLeaveRequestsWithIdAndStatus(matrixWithIDAndStatus);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Leave management application\nEnter number of requested leaves: ");

        int numLeaveRequests = scan.nextInt();
        String[] employee = new String[6];
        String[][] matrix = new String[numLeaveRequests][8];
        String name;
        String fake;

        printMenu();
        System.out.print("Enter a choice: ");
        int choice = scan.nextInt();
        int i = 0;
        while (choice != 5) {
            switch (choice) {
                case 1:
                    matrix = fillMatrixLeaveData(numLeaveRequests);
                    break;
                case 2:
                    printAllLeaveRequests(matrix);
                    break;
                case 3:
                    System.out.print("Enter an employee name: ");
                    fake = scan.nextLine();
                    name = scan.nextLine();
                    printEmlpoyeeLeaveRequests(name, matrix);
                    break;
                case 4:
                    printMatrixWithlIDAndStatus(numLeaveRequests, matrix);
                    break;

            }
            System.out.print("Enter a choice: ");
            choice = scan.nextInt();
        }
    }
}