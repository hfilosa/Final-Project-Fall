import java.util.Scanner;

public class Driver{
    static Scanner sc = new Scanner(System.in);
    static String p1color,p2color;
    static String[] colors = {"black","red","green","yellow","blue","purple","cyan","white"};
    static boolean won = false;
    static board test;

    public static void colorSelect(){
	System.out.println("Select a color for player 1:");
	System.out.println("Color options:");
	for (int i=0;i<colors.length;i++){
	    System.out.print(colors[i]+" ");}
	System.out.println();
	p1color= sc.nextLine();
	System.out.println("Select a color for player 2:");
	p2color= sc.nextLine();
	boolean error1 = true;
	boolean error2 = true;
	boolean error3 = true;
	while (error1 || error2 || error3){
	    for (int i=0;i<colors.length;i++){
		if (p1color.equals(colors[i])) error1=false;}
	    for (int i=0;i<colors.length;i++){
		if (p2color.equals(colors[i])) error2=false;}
	    if (error1){
		System.out.println("Invalid color selected for player 1");
		System.out.println("Choose a color listed below:");
		for (int i=0;i<colors.length;i++){
		    System.out.print(colors[i]+" ");}
		System.out.println();
		p1color= sc.nextLine();
	    }
	    if (error2){
		System.out.println("Invalid color selected for player 2");
		System.out.println("Choose a color listed below:");
		for (int i=0;i<colors.length;i++){
		    System.out.print(colors[i]+" ");}
		System.out.println();
		p2color= sc.nextLine();
	    }
	    if (!p1color.equals(p2color))error3=false;
	    if (error3){
		error1=true;
		error2=true;
		System.out.println("The players cannot have the same color!");
		pause(1);
		System.out.println("Select a color for player1:");
		p1color= sc.nextLine();
		System.out.println("Select a color for player2:");
		p2color= sc.nextLine();
	    }
	}
	test = new board(p1color,p2color);
    }

    public static void pause(int seconds){
	try { 
	    Thread.sleep(seconds*1000); 
	} catch(InterruptedException e) { 
	}
    }

    public static void help(){
	System.out.println("Entering moves is easy in ultimate chess");
	pause(3);
	System.out.println("Simply enter the coordinates of the piece you wish to move and its desired destination");
	pause(3);
	System.out.println("Coordinates are entered letter first and then number. ex: a2 a3");
	pause(3);
	System.out.println("If a move is illegal it will fail and you will be asked to input a legal move");
	pause(3);
	System.out.println("Thats it! If you don't know how to play chess consult this website: http://www.dummies.com/how-to/content/chess-for-dummies-cheat-sheet.html");
    }

    public static void player1Turn(){
	System.out.println("It is player one's turn");
	System.out.println(test.print1());
    }


    public static void main(String[] args){
	System.out.println("Welcome to ultimate chess!");
	colorSelect();
	System.out.println("Game setup finished!");
	player1Turn();
	help();
	/*	while(!won){
	    System.out.println(test.print1());
	*/

    }
}
