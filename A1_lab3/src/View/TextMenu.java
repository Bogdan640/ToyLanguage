package View;

import Model.DataStructures.Interfaces.MyIDictionary;
import View.Commands.Command;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TextMenu {
    private Map<String, Command> commands;
    public TextMenu(){
        this.commands = new HashMap<>();
    }
    public void addCommand(Command c){
        this.commands.put(c.get_key(), c);
    }
    private void printMenu(){
        for(Command c : this.commands.values()){
            String line = String.format("%4s : \n%s\n", c.get_key(), c.get_description());
            System.out.println(line);
        }
    }
    public void show(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            printMenu();
            System.out.printf("Input the option: ");
            String key = scanner.nextLine();
            Command c = this.commands.get(key);
            if(c == null){
                System.out.println("Invalid option");
                continue;
            }
            c.execute();
        }
    }
}
