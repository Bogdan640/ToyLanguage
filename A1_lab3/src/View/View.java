package View;

import Controller.ICtrl;
import Repository.IRepo;

import java.util.Scanner;

public class View {
    private IRepo repo;
    private ICtrl ctrl;

    public View(IRepo repo, ICtrl ctrl){
        this.repo=repo;
        this.ctrl=ctrl;
    }

    public void options() {
        System.out.println("1. Choose a program state from the list");
        System.out.println("2. Execute one step");
        System.out.println("3. Execute all steps");
        System.out.println("4. Exit");

    }

    public void run(){
        options();
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        while(option!=4){
            System.out.print("Choose an option: ");
            option = scanner.nextInt();
            switch(option){
                case 1:
                    System.out.print("Choose a program state from the list: ");
                    int index = scanner.nextInt();
                    repo.change_current_state(index-1);
                    System.out.println("Done");
                    break;
                case 2:
                    try {
                        ctrl.executeOneStep(repo.getCrtPrg());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        ctrl.executeAll();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
