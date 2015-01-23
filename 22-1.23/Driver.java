import java.util.Scanner;

public class Driver{
    static Scanner sc = new Scanner(System.in);
    static String p1color,p2color;
    static String p1name, p2name;
    static String[] colors = {"black","red","green","yellow","blue","purple","cyan","white"};
    static boolean won = false;
    static boolean p1turn,p2turn;
    static board test;
    static boolean p1Victory;
    static String lastMove2;
    static String lastMove1 = "!";

    public static void colorSelect(){
	System.out.println("What is your name, player 1?");
	p1name=sc.nextLine();
	System.out.println("What is your name, player 2?");
	p2name=sc.nextLine();
	System.out.println("Select a color for "+p1name+":");
	System.out.println("Color options:");
	for (int i=0;i<colors.length;i++){
	    System.out.print(colors[i]+" ");}
	System.out.println();
	p1color= sc.nextLine();
	System.out.println("Select a color for "+p2name+":");
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
		System.out.println("Invalid color selected for "+p1name);
		System.out.println("Choose a color listed below:");
		for (int i=0;i<colors.length;i++){
		    System.out.print(colors[i]+" ");}
		System.out.println();
		p1color= sc.nextLine();
	    }
	    if (error2){
		System.out.println("Invalid color selected for "+p2name);
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
		System.out.println("Select a color for "+p1name+":");
		p1color= sc.nextLine();
		System.out.println("Select a color for "+p2name+":");
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
    //Taken directly from Stack Overflow
    public final static void clearConsole(){
	final String ANSI_CLS = "\u001b[2J";
        final String ANSI_HOME = "\u001b[H";
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.flush();
    }

    public static void help(){
	System.out.println("Entering moves is easy in ultimate chess");
	pause(2);
	System.out.println("Simply enter the coordinates of the piece you wish to move and its desired destination");
	pause(3);
	System.out.println("Coordinates are entered letter first and then number. ex: a2, d6, etc");
	pause(2);
	System.out.println("Enter the coordinates of the piece you want to move, then press enter");
	pause(2);
	System.out.println("Then enter the coordinates of where you wish to move the piece");
	pause(2);
	System.out.println("If a move is illegal it will fail and you will be asked reenter where you wish to move to");
	pause(2);
	System.out.println("When entering the coordinates of where to move a piece to, type ! to reselect the piece you want to move");
	pause(3);
	System.out.println("When selecting the piece you wish to move, type 0-0 for kingside castling(castling short) or 0-0-0 for queenside castling(castling long)");
	pause(3);
	System.out.println("Because this program cannot detect checkmate or stalemate, type @ to forfeit and stop the program");
	pause(2);
	System.out.println("That's it! If you don't know how to play chess consult this website: http://www.dummies.com/how-to/content/chess-for-dummies-cheat-sheet.html");
    }
  
    public static int convertCol(String coor) {
	int col=coor.charAt(0);
	col-='a';
	return col;
    }

    public static int convertRow(String coor) {
	int row=coor.charAt(1);
	row-='0';
	row-=8;
	row*=(-1);
	return row;
    }

    //Inputs are the coordinates that are to be checked and the color it should be
    public static boolean piecePresent(String coor,String color,boolean move){
	if (coor.length()!=2){
	    System.out.println("Invalid input. Proper input: a3, g6, etc.");
	    return false;
	}
	int col=convertCol(coor);
	int row=convertRow(coor);
	if (row<8 && row>=0 && col<8 && col>=0){
	    if (move){
		if (!(color.equals(test.getPieceColor(row,col)))){return true;}
		else {
		    System.out.println("Invalid coordinate, cannot move to a place occupied by one of your own pieces! Reenter the place you wish to move to");
		    return false;
		}
	    }
	    else{
		if (test.getPiece(row,col).equals(" ")){
		    System.out.println("You must select one of your own pieces");
		    return false;
		}	 
		if (color.equals(test.getPieceColor(row,col))){return true;}	       
		System.out.println("You must select one of your own pieces");
		return false;
	    }
	}
	System.out.println("Not a space on the board!");
	return false;
    }

    public static boolean move(String startCoor,String endCoor,String color,boolean p1){
	int startCol=convertCol(startCoor);
	int startRow=convertRow(startCoor);
	int endCol=convertCol(endCoor);
	int endRow=convertRow(endCoor);
	if (!(test.moveExecutor(startRow,startCol,endRow,endCol,color,p1))){
	    return false;
	}
	return true;
    }

    public static void playerTurn(Boolean p1){
	clearConsole();
	String player = p1name;
	String otherPlayer = p2name;
	String color = p1color;
	if (!p1) {
	    player = p2name;
	    otherPlayer = p1name;
	    color = p2color;
	} 
	boolean check = false;
	test.passantRemove(color);
	System.out.println(test.print(p1));
	check = test.check(color,p1);
	System.out.println("It is "+player+"'s turn\n");
	if (!lastMove1.equals("!")){
	    String lastPiece = test.getPiece(convertRow(lastMove2),convertCol(lastMove2));
	    System.out.println(otherPlayer+" moved a "+lastPiece+" from "+lastMove1+" to "+lastMove2);
	}
	pause(1);
	if (check){
	    System.out.println("You are in check!");
	    pause(1);
	    System.out.println("You must remove yourself from check this turn!");
	}
	String[] pmove = new String[2];
	System.out.println("Please enter the coordinates of the piece you wish to move");
	pmove[0] = sc.nextLine();
	boolean done = false;
	boolean skip = false;
	while (!done){	   
	    if (pmove[0].equals("?")){
		help();
		System.out.println("Please enter the coordinates of the piece you wish to move");
		pmove[0] = sc.nextLine();
	    }
	    if (pmove[0].equals("@")){
		System.out.println("Are you sure you wish to forfeit? Type @ again to forfeit or enter the coordinates of the piece you wish to move");
		pmove[0]=sc.nextLine();
		if (pmove[0].equals("@")){
		    won = true;
		    if (p1){
			p1Victory=false;
		    }
		    else {p1Victory=true;}
		    return;
		}
	    }
	    if (pmove[0].equals("0-0-0")){
		if (!(test.longCastling(color,p1))){
		    pmove[0] = sc.nextLine();
		}
		else {return;}
	    }
	    if (pmove[0].equals("0-0")){
		if (!(test.shortCastling(color,p1))){
		    pmove[0] = sc.nextLine();
		}
		else {return;}
	    }
	    if (!(piecePresent(pmove[0],color,false))){
		System.out.println("Reenter the piece you wish to move");
		pmove[0] = sc.nextLine();
	    }
	    else {done=true;}
	}
	done = false;
	System.out.println("Please enter the coordinates of the place you wish to move to");
	pmove[1] = sc.nextLine();
	while (!done && !skip){
	    if (pmove[1].equals("?")){
		help();
		System.out.println("Please enter the coordinates of the place you wish to move to");
		pmove[1] = sc.nextLine();	    
	    }
	    if (pmove[1].equals("@")){
		System.out.println("Are you sure you wish to forfeit? Type @ again to forfeit or enter where you wish to move");
		pmove[1]=sc.nextLine();
		if (pmove[1].equals("@")){
		    won = true;
		    if (p1){
			p1Victory=false;
		    }
		    else {p1Victory=true;}
		    return;
		}
	    }
	    if (pmove[1].equals("!")){
		System.out.println("Enter the coordinates of the piece you wish to move");
		boolean done2 = false;
		while(!done2){
		    pmove[0]=sc.nextLine();
		    if (!(piecePresent(pmove[0],color,false))){
			System.out.println("Reenter the piece you wish to move");
			pmove[0] = sc.nextLine();
		    }
		    else {done2 = true;}
		}
		System.out.println("Please enter the coordinates of the place you wish to move to");
		pmove[1] = sc.nextLine();
	    }
	    if (!(piecePresent(pmove[1],color,true))){
		pmove[1] = sc.nextLine();
	    }
	    else {
		if (move(pmove[0],pmove[1],color,p1)){
		    done=true;
		}
		else {System.out.println("Enter the coordinates of the place you wish to move to");
		    pmove[1]=sc.nextLine();}	    
	    }
	}
	lastMove1=pmove[0];
	lastMove2=pmove[1];
	if (test.promotion(p1)){
	    String upgrade = sc.nextLine();
	    while (!test.promote(p1,upgrade)){
		upgrade=sc.nextLine();
	    }
	}
	System.out.println(player+"'s turn is done");
	pause(1);
    }
   
    public static void main(String[] args){
	clearConsole();
	System.out.println("Welcome to ultimate chess!");
	colorSelect();
	System.out.println("Game setup finished!");
	System.out.println("For help type: ?");
	pause(2);
	while (!won){
	     playerTurn(true);
	     if (won){break;}
	     playerTurn(false);
	}
	clearConsole();
	System.out.println("Game over!");
	pause(1);
	if (p1Victory){
	    System.out.println("Congratulations "+p1name+"! You are a chess pro!");
	}
	else {System.out.println("Congratulations "+p2name+"! You are a chess pro!");}
	pause(3);
	clearConsole();
    }
}
