//package com.rangecontainer;

//Imports
import java.util.Scanner;

public class Manager{
	
	
	public static void printPrompt(RangeContainer range) {
		
		System.out.println("\n");
		System.out.print("Range Container: ");
		range.print();
		System.out.println("\n");
		System.out.print("[COMMAND] ENTER HELP FOR USAGE >>>  ");
		
	}
	
	public static void printHelp() {
		
		System.out.println("Usage: myprogram [command]\n"
				+ "\n"
				+ "Commands:\n"
				+ "  ADD     Add a new item\n"
				+ "  DELETE  Delete an existing item\n"
				+ "\n"
				+ "Options:\n"
				+ "  -h, --help     Show this help message and exit");
		
	}
	
	public static void clearScreen() {
		try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                // Clear screen for Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Clear screen for Unix/Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}
	
	public static void main(String[] args) {
		
				
		//Init RangecContainer obj to be modified
		RangeContainer ExperimentContainer = new RangeContainer();
		
		//Init scanner class to take input
		Scanner usrIn = new Scanner(System.in);
		String input, addInt;
		boolean endManager = false;
		
		while(!endManager){
			
			//Print and prompt for input 
			printPrompt(ExperimentContainer);
			input = usrIn.nextLine(); 
			
			if(input.toLowerCase().strip().equals("add")) {
				
				System.out.print("\n");
				System.out.print("ENTER INT TO ADD: ");
				addInt = usrIn.nextLine(); 
				ExperimentContainer.addInt(Integer.parseInt(addInt));
				clearScreen();
				
			}else if(input.toLowerCase().strip().equals("remove")) {
				
				System.out.print("\n");
				System.out.print("ENTER INT TO REMOVE: ");
				addInt = usrIn.nextLine(); 
				ExperimentContainer.removeRange(Integer.parseInt(addInt));
				clearScreen();
				
			}else if(input.toLowerCase().strip().equals("help")) {
				
				clearScreen();
				printHelp();
				
			}else if(input.toLowerCase().strip().equals("end")) {

				endManager = true;
				clearScreen();

			}else {
				clearScreen();
				System.out.println("NOT A VALID COMMAND, TYPE HELP FOR MORE INFO \n");
				
			}
			
			
		}
		
		
		
		
	}
	
}