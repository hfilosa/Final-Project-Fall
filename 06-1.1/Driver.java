import java.util.Scanner;

public class Driver{
    static Scanner sc = new Scanner(System.in);
    static String p1color,p2color;
    static String[] colors = {"black","red","green","yellow","blue","purple","cyan","white"};
    static boolean won = false;
    static boolean p1turn,p2turn;
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
	pause(2);
	System.out.println("Simply enter the coordinates of the piece you wish to move and its desired destination");
	pause(3);
	System.out.println("Coordinates are entered letter first and then number. ex: a2 a3");
	pause(2);
	System.out.println("To cancel an order type !");
	pause(2);
	System.out.println("If a move is illegal it will fail and you will be asked to input a legal move");
	pause(3);
	System.out.println("Thats it! If you don't know how to play chess consult this website: http://www.dummies.com/how-to/content/chess-for-dummies-cheat-sheet.html");
    }

    //Inputs are the coordinates that are to be checked and the color it should be
    public static boolean piecePresent(String coor,String color,boolean move){
	if (coor.length()!=2){
	    return false;
	}
	int col=coor.charAt(0);
	col-='a';
	int row=coor.charAt(1);
	row-='0';
	row-=8;
	row*=(-1);
	System.out.println("row: "+row +" col:  " +col);
	if (row<8 && row>=0 && col<8 && col>=0){
	    System.out.println(test.getPiece(row,col));
	    if (test.getPiece(row,col).equals(" ") && !move){
		return false;
	    }
	    if (move && !(color.equals(test.getPieceColor(row,col)))){return true;}
	    if (color.equals(test.getPieceColor(row,col))){return true;}
	    else {return false;}
	}
	else {return false;}
    }

    public static void player1Turn(){
	System.out.println("It is player one's turn");
	pause(1);
	System.out.println(test.print1());
	String[] p1move = new String[2];
	pause(1);
	System.out.println("Please enter the coordinates of the piece you wish to move");
	p1move[0] = sc.nextLine();
	boolean done = false;
	while (!done){	   
	    if (p1move[0].equals("?")){
		help();
		System.out.println("Please enter the coordinates of the piece you wish to move");
		p1move[0] = sc.nextLine();
	    }
	    if (!(piecePresent(p1move[0],p1color,false))){
		System.out.println("Invalid coordinate! Reenter the piece you wish to move");
		p1move[0] = sc.nextLine();
	    }
	    else {done=true;}
	}
	done = false;
	pause(1);
	System.out.println("Please enter the coordinates of the place you wish to move to");
	p1move[1] = sc.nextLine();
	while (!done){
	    if (p1move[1].equals("?")){
		help();
		System.out.println("Please enter the coordinates of the piece you wish to move");
		p1move[1] = sc.nextLine();	    
	    }
	    if (p1move[1].equals("!")){
		System.out.println("Enter the coordinates of the piece you wish to move");
		boolean done2 = false;
		while(!done2){
		    p1move[0]=sc.nextLine();
		    if (!(piecePresent(p1move[0],p1color,false))){
			System.out.println("Invalid coordinate! Reenter the piece you wish to move");
			p1move[0] = sc.nextLine();
		    }
		    else {done2 = true;}
		}
	    }
	    if (!(piecePresent(p1move[1],p2color,true))){
		System.out.println("Invalid coordinate! Reenter the place you wish to move to");
		p1move[1] = sc.nextLine();
	    }
	    else {done=true;}
	    
	}
	System.out.println("Player one's turn is done");
    }

    public static void player2Turn(){
	System.out.println("It is player two's turn");
	pause(1);
	System.out.println(test.print2());
	String[] p2move = new String[2];
	pause(1);
	System.out.println("Please enter the coordinates of the piece you wish to move");
	p2move[0] = sc.nextLine();
	boolean done = false;
	while (!done){
	    if (p2move[0].equals("?")){
		help();
		System.out.println("Please enter the coordinates of the piece you wish to move");
		p2move[0] = sc.nextLine();
	    }
	    if (!(piecePresent(p2move[0],p2color,false))){
		System.out.println("Invalid coordinate! Reenter the piece you wish to move");
		p2move[0] = sc.nextLine();
	    }
	    else {done=true;}
	}
	done = false;
	pause(1);
	System.out.println("Please enter the coordinates of the place you wish to move to");
	p2move[1] = sc.nextLine();
	while (!done){
	    if (p2move[1].equals("?")){
		help();
        	System.out.println("Please enter the coordinates of the piece you wish to move");
		p2move[0] = sc.nextLine();
	    }
	    if (p2move[1].equals("!")){
		System.out.println("Enter the coordinates of the piece you wish to move");
		boolean done2 = false;
		while(!done2){
		    p2move[0]=sc.nextLine();
		    if (!(piecePresent(p2move[0],p2color,false))){
			System.out.println("Invalid coordinate! Reenter the piece you wish to move");
			p2move[0] = sc.nextLine();
		    }
		    else {done2 = true;}
		}
	    }
	    if (!(piecePresent(p2move[1],p1color,true))){
		System.out.println("Invalid coordinate! Reenter the place you wish to move to");
		p2move[1] = sc.nextLine();
	    }
	    else {done=true;}
	    
	}
	System.out.println("Player two's turn is done");
    }

    public static void main(String[] args){
	System.out.println("Welcome to ultimate chess!");
	colorSelect();
	System.out.println("Game setup finished!");
	System.out.println("For help type: ?");
	pause(1);
	while (!won){
	    player1Turn();
	    player2Turn();
	}
	System.out.println("Game over!");
    }
}
