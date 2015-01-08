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
	System.out.println("If a move is illegal it will fail and you will be asked to input a legal move");
	pause(2);
	System.out.println("When entering the coordinates of where to move a piece to, type ! to reselect the piece you want to move");
	pause(2);
	System.out.println("If you would like to see a list of the specific pieces that have been captured, type #.");
	pause(3);
	System.out.println("When selecting the piece you wish to move, type 0-0 for kingside castling(castling short) or 0-0-0 for queenside castling(castling long)");
	pause(3);
	System.out.println("Thats it! If you don't know how to play chess consult this website: http://www.dummies.com/how-to/content/chess-for-dummies-cheat-sheet.html");
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
	System.out.println("startRow: "+startRow+" startCol: "+startCol+"\nendRow: "+endRow+" endCol: "+endCol);
	if (!(test.moveExecutor(startRow,startCol,endRow,endCol,color,p1))){
	    return false;
	}
	return true;
    }

    public static void playerTurn(Boolean p1){
	String player = "one";
	String color = p1color;
	boolean check = false;
	if (!p1){
	    player="two"; 
	    color = p2color; 
	}
	test.passantRemove(color);
	System.out.println(test.print(p1));
	check = test.check(color,p1);
	System.out.println("It is player "+player+"'s turn");
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
	    if (pmove[0].equals("#")) {
		System.out.println("Player 1 has captured "+test.p1losses.size()+" pieces. They are:");
		System.out.println(test.p1losses);
		System.out.println("Player 2 has captured "+test.p2losses.size()+" pieces. They are:");
		System.out.println(test.p2losses);
		pmove[0] = sc.nextLine();
	    }
	    if (pmove[0].equals("0-0-0")){
		skip=true;
		done=true;
		if (!(test.longCastling(color,p1))){
		    pmove[0] = sc.nextLine();
		    skip=false;
		    done = false;
		}
	    }
	    if (pmove[0].equals("0-0")){
		skip=true;
		done=true;
		if (!(test.shortCastling(color,p1))){
		    pmove[0] = sc.nextLine();
		    skip=false;
		    done = false;
		}
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
	    }
	    if (!(piecePresent(pmove[1],color,true))){
		pmove[1] = sc.nextLine();
	    }
	    else {
		if (move(pmove[0],pmove[1],color,p1)){
		    done=true;
		}
		else {pmove[1]="";}	    
	    }
	}
	System.out.println("Player "+player+"'s turn is done");
    }
   
    public static void main(String[] args){
	System.out.println("Welcome to ultimate chess!");
	colorSelect();
	System.out.println("Game setup finished!");
	System.out.println("For help type: ?");
	pause(1);
	while (!won){
	     playerTurn(true);
	     playerTurn(false);
	}
	System.out.println("Game over!");
    }
}
