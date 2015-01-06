public class board{
    private piece[][] board = new piece[8][8];
    private String p1header = "   a   b   c   d   e   f   g    h\n";
    private String p2header = "   h   g   f   e   d   c   b    a\n";
    private String divider = "  ---------------------------------\n";
    // for En Passant move - the pawn that would be captured
    private piece[][] pCapture;
    private boolean forward;

    //Board is set up with the black pieces at the 'top'
    //This configuration is read as facing the p1
    public board(String p1color,String p2color){
	for (int row=0;row<8;row++){
	    for (int col=0;col<8;col++){
		board[row][col] = new piece();
	    }
	}
	board[0][0].setname("R");
	board[0][1].setname("N");
	board[0][2].setname("B");
	board[0][3].setname("Q");
	board[0][4].setname("K");
	board[0][5].setname("B");
	board[0][6].setname("N");
	board[0][7].setname("R");
	for (int col=0;col<8;col++){
	    board[1][col].setname("p");
	    board[1][col].setcolor(p2color);
	    board[0][col].setcolor(p2color);
	}
	board[7][0].setname("R");
	board[7][1].setname("N");
	board[7][2].setname("B");
	board[7][3].setname("Q");
	board[7][4].setname("K");
	board[7][5].setname("B");
	board[7][6].setname("N");
	board[7][7].setname("R");
	for (int col=0;col<8;col++){
	    board[6][col].setname("p");
	    board[6][col].setcolor(p1color);
	    board[7][col].setcolor(p1color);
	}
    }

    //this prints the board facing player 1 if boolean p1 is true
    public String print(boolean p1){
	String ans = "";
	if (p1){
	    ans = p1header;
	    ans+=divider;
	    for (int row=0;row<8;row++){
		ans+=(char)('0'+(8-row))+" ";
		for (int col=0;col<8;col++){
		    ans+="| "+board[row][col]+" ";
		}
		ans+="| "+(char)('0'+(8-row))+"\n";
		ans+=divider;
	    }
	    ans+=p1header;
	}
	if (!p1){
	    ans = p2header;
	    ans+=divider;
	    for (int row=7;row>=0;row--){
		ans+=(char)('0'+(8-row))+" ";
		for (int col=7;col>=0;col--){
		    ans+="| "+board[row][col]+" ";
		}
		ans+="| "+(char)('0'+(8-row))+"\n";
		ans+=divider;
	    }
	    ans+=p2header;
	}
	return ans;
    }

    public String getPiece(int row, int col){
	if (row<8 && row>=0 && col<8 && col>=0){
	    return board[row][col].getname();
	}
        return " ";
    }
   
    public String getPieceColor(int row, int col){
	return board[row][col].getcolor();
    }
    
    //This is used to move the piece AFTER the move has been authenticated
    public void move(int startRow,int startCol,int endRow,int endCol){
	board[endRow][endCol] = board[startRow][startCol];
	board[startRow][startCol] = new piece();
	board[endRow][endCol].setmoved(true);
    }

    //removes passants of a players color
    public void passantRemove(String color){
	for (int row=0;row<8;row++){
	    for (int col=0;col<8;col++){
		if (board[row][col].getname().equals("pas")){
		    if (board[row][col].getcolor().equals(color)){
			board[row][col].setname(" ");
			board[row][col].setcolor(null);
		    }
		}
	    }
	}
    }

    public boolean pawnCheck(int krow,int kcol,String color,boolean p1){
	int pawnRow = 0;
	if (p1){
	    pawnRow = -1;
	}
	if (!p1){pawnRow = 1;}
	if (getPiece(krow+pawnRow,kcol+1).equals("p") && !getPieceColor(krow+pawnRow,kcol+1).equals(color) || getPiece(krow+pawnRow,kcol-1).equals("p") && !getPieceColor(krow+pawnRow,kcol-1).equals(color)){
	  return true;
	}
	return false;
    }
    /*This searches the tiles from the starting coordinates (krow,kcol) and increments in the x and y coordinates by the values provided as it searches for the piece. If it finds it before being blocked it returns true. This sweep is used specifically for determing if check is present*/
    public boolean checksweep(int krow,int kcol,String piece,String color,int x,int y){
	while (krow<8 && krow>=0 && kcol<8 && kcol>=0){
	    krow+=x;
	    kcol+=y;
	    if (getPiece(krow,kcol).equals(piece) && !getPieceColor(krow,kcol).equals(color)){
	        return true;
	    }
	    //As long as its not the piece we are looking for and the space is not empty then that piece is consered blocked
	    else if (!getPiece(krow,kcol).equals(" ")){
		return false;
	    }
	}
	return false;
    }


    public boolean diagonalCheck(int krow,int kcol,String color){
	if (checksweep(krow,kcol,"B",color,1,1)){return true;}
	if (checksweep(krow,kcol,"B",color,1,-1)){return true;}
	if (checksweep(krow,kcol,"B",color,-1,1)){return true;}
	if (checksweep(krow,kcol,"B",color,-1,-1)){return true;}
	if (checksweep(krow,kcol,"Q",color,1,1)){return true;}
	if (checksweep(krow,kcol,"Q",color,1,-1)){return true;}
	if (checksweep(krow,kcol,"Q",color,-1,1)){return true;}
	if (checksweep(krow,kcol,"Q",color,-1,-1)){return true;}
	return false;
    }

    public boolean horizontalCheck(int krow,int kcol,String color){
	if (checksweep(krow,kcol,"R",color,1,0)){System.out.println("1"); return true;}
	if (checksweep(krow,kcol,"R",color,-1,0)){System.out.println("2"); return true;}
	if (checksweep(krow,kcol,"R",color,0,1)){System.out.println("3"); return true;}
	if (checksweep(krow,kcol,"R",color,0,-1)){System.out.println("4"); return true;}
	if (checksweep(krow,kcol,"Q",color,1,0)){System.out.println("5"); return true;}
	if (checksweep(krow,kcol,"Q",color,-1,0)){System.out.println("6"); return true;}
	if (checksweep(krow,kcol,"Q",color,0,1)){System.out.println("7"); return true;}
	if (checksweep(krow,kcol,"Q",color,0,-1)){System.out.println("8"); return true;}
	return false;
    }

    public boolean knightCheck2(int row,int col,String color){
	if (getPiece(row,col).equals("K") && !getPieceColor(row,col).equals(color)){
	    return true;
	}
	return false;
    }

    public boolean knightCheck(int krow,int kcol,String color){
	if (knightCheck2(krow+2,kcol+1,color)){return true;}
	if (knightCheck2(krow+2,kcol-1,color)){return true;}
	if (knightCheck2(krow-2,kcol+1,color)){return true;}
	if (knightCheck2(krow-2,kcol-1,color)){return true;}
	if (knightCheck2(krow+1,kcol+2,color)){return true;}
	if (knightCheck2(krow-1,kcol+2,color)){return true;}
	if (knightCheck2(krow+1,kcol-2,color)){return true;}
	if (knightCheck2(krow-1,kcol-2,color)){return true;}
	return false;
    }

    //checks for check
    public boolean check(String color,boolean p1){
	int krow=0;
	int kcol=0;
	int pawnRow = 0;
	for (int row=0;row<8;row++){
	    for (int col=0;col<8;col++){
		if (getPiece(row,col).equals("K") && getPieceColor(row,col).equals(color)){
		    krow=row;
		    kcol=col;
		}
	    }
	}
        if (pawnCheck(krow,kcol,color,p1)){System.out.println("1"); return true;}
	if (diagonalCheck(krow,kcol,color)){System.out.println("2"); return true;}
	if (horizontalCheck(krow,kcol,color)){System.out.println("3"); return true;}
	if (knightCheck(krow,kcol,color)){System.out.println("4"); return true;}
	return false;
    }

    //This executes the move and checks if it results in check for the player initiating the move. 
    //If it resulted in check it is an illegal move and the board is reset to its prior state and a false boolean is returned.
    //Otherwise true is returned and the move is executed.
    //This method is only called after the move has been autheticated.
    public boolean moveExecutor(int startRow,int startCol,int endRow,int endCol,String color,boolean p1){
	piece startPiece = board[startRow][startCol];
	piece endPiece=board[endRow][endCol];
	System.out.println("@");
	if (getPiece(startRow,startCol).equals("R")){
	    if (!(rookMove(startRow,startCol,endRow,endCol))){
	        System.out.println("This rook is blocked!");
		return false;
	    }
	}
	if (getPiece(startRow,startCol).equals("N")){
	    System.out.println("!");
	    if (!(knightMove(startRow,startCol,endRow,endCol))){
		System.out.println("That is an illegal move for this knight!");
		return false;
	    }
	}
	if (getPiece(startRow,startCol).equals("B")){
	    if (!(bishopMove(startRow,startCol,endRow,endCol))){
		System.out.println("This bishop is blocked!");
		return false;
	    }
	}
	if (getPiece(startRow,startCol).equals("Q")){
	    if (!(bishopMove(startRow,startCol,endRow,endCol))){
		System.out.println("This Queen is blocked!");
		return false;
	    }
	}
	if (getPiece(startRow,startCol).equals("p")){
	    if (!(pawnMove(startRow,startCol,endRow,endCol,color,p1))){
		return false;
	    }
	}
	if (getPiece(startRow,startCol).equals("K")){
	    if (!(kingMove(startRow,startCol,endRow,endCol))){
		return false;
	    }
	}
	System.out.println("@");
	move(startRow,startCol,endRow,endCol);
	if (check(color,p1)){
	    board[startRow][startCol] = startPiece;
	    board[endRow][endCol] = endPiece;
	    System.out.println("This move is illegal as it would result in check");
	    board[startRow][startCol].setmoved(false);
	    return false;
	}
	return true;
    }

    //the end coordinates have already been checked that they are not a players own pieces or outside the board
    public boolean pawnMove(int startRow,int startCol,int endRow,int endCol,String color,boolean p1){
	int doub=0;
	int diff=0;
	if (p1){
	    doub = 6;
	    diff=startRow-endRow;
	}
	if (!p1){
	    doub = 1;
	    diff=endRow-startRow;
	}
	if (startCol==endCol){
	    if (getPiece(endRow,endCol).equals(" ")){
		if (diff==1){
		    return true;
		}
		if (diff==2 && startRow==doub){
		    if (p1){
			board[5][startCol].setname("pas");
			board[5][startCol].setcolor(color);
		    }
		    if (!p1){
			board[2][startCol].setname("pas");
			board[2][startCol].setcolor(color);
		    }
		    return true;
		}
		else {
		    System.out.println("This pawn is blocked!");
		    return false;}
	    }
	}
	if (Math.abs(startCol-endCol)==1){
	    if (diff==1){
		if (!(getPiece(endRow,endCol).equals(" "))){
		    return true;
		}
		else {
		    System.out.println("A pawn can only move diagonally to capture");
		    return false;}
	    }
	    else {
		 System.out.println("That is an illegal move for this pawn!");
		 return false;}
	}
	else {
	    System.out.println("That is an illegal move for this pawn!");
	    return false;}
    }

    //return true if valid move
    public boolean knightMove(int startRow,int startCol,int endRow,int endCol){
	if (Math.abs(startRow-endRow)==2 && Math.abs(startCol-endCol)==1){
	    return true;
	}
	if (Math.abs(startCol-endCol)==2 && Math.abs(startRow-endRow)==1){
	    return true;
	}
	return false;
    }
    //returns true if there is no piece blocking movement in that direction
    public boolean sweep(int startRow,int startCol,int endRow,int endCol){
	int x = (endRow-startRow);
	if (x<0){x=(-1);}
	else if (x>0){x=1;}
	int y = (endCol-startCol);
	if (y<0){y=(-1);}
	else if (y>0){y=1;}
	while (startRow!=endRow && startCol!=endCol){
	    startRow+=x;
	    startCol+=y;
	    if (!(getPiece(startRow,startCol).equals(" "))){
	        return false;
	    }
	}
	return true;
    }

    public boolean diagonalMove(int startRow,int startCol,int endRow,int endCol){
	if (Math.abs(startRow-endRow)==Math.abs(startCol-endCol)){
	    if(sweep(startRow,startCol,endRow,endCol)){return true;}
	    return false;
	}
	return false;
    }

    public boolean horizontalMove(int startRow,int startCol,int endRow,int endCol){
	int x = (startRow-endRow);
	int y = (startCol-endCol);
	if ( ( x==0 && y!=0 ) || ( x!=0 && y==0)){
	    if(sweep(startRow,startCol,endRow,endCol)){return true;}
	    return false;
	}
	return false;
    }

    public boolean rookMove(int startRow,int startCol,int endRow,int endCol){
	if (horizontalMove(startRow,startCol,endRow,endCol)){return true;}
	return false;
    }

    public boolean bishopMove(int startRow,int startCol,int endRow,int endCol){
	if (diagonalMove(startRow,startCol,endRow,endCol)){return true;}
	return false;
    }
    
    public boolean queenMove(int startRow,int startCol,int endRow,int endCol){
	if (diagonalMove(startRow,startCol,endRow,endCol) || diagonalMove(startRow,startCol,endRow,endCol)){return true;}
	return false;
    }

    public boolean kingMove(int startRow,int startCol,int endRow,int endCol){
	if (Math.abs(startRow-endRow)<=1 && Math.abs(startCol-endCol)<=1){
	    return true;
	}
	return false;
    }
    
    public boolean shortCastling(String color,boolean p1){
	boolean legal = false;
	int row = 0;
	if (!p1){
	    row = 7;
	}
	if (getPiece(row,4).equals("K") && getPieceColor(row,4).equals(color) && getPiece(row,7).equals("R") && getPieceColor(row,7).equals(color)){
	    if (!board[row][4].getmoved() && !board[row][7].getmoved()){
		if (getPiece(row,5).equals(" ") && getPiece(row,6).equals(" ")){
		    legal = true;
		}
		else{System.out.println("There are pieces between your king and rook. They cannot castle!");}
	    }
	    else{System.out.println("Your rook or king has moved and cannot castle");}
	}
	else{System.out.println("Your King or rook isn't even in there original locations! They cannot castle.");}
	if (legal){
	    move(row,4,row,6);
	    move(row,7,row,5);
	    if (check(color,p1)){
		move(row,6,row,4);
		move(row,5,row,7);
		board[row][4].setmoved(false);
		board[row][7].setmoved(false);
		System.out.println("This move would put you in check!");
		return false;
	    }
	    else {return true;}
	}
	else{return false;}
    }

    public boolean longCastling(String color,boolean p1){
	boolean legal = false;
	int row = 7;
	if (!p1){
	    row = 0;
	}
	if (getPiece(row,4).equals("K") && getPieceColor(row,4).equals(color) && getPiece(row,0).equals("R") && getPieceColor(row,0).equals(color)){
	    if (!board[row][4].getmoved() && !board[row][0].getmoved()){
		if (getPiece(row,5).equals(" ") && getPiece(row,6).equals(" ")){
		    legal = true;
		}
		else{System.out.println("There are pieces between your king and rook. They cannot castle!");}
	    }
	    if (board[row][4].getmoved() || board[row][0].getmoved()){
		System.out.println("Your rook or king has moved and cannot castle");}
	}
	else{System.out.println("Your King or rook isn't even in there original locations! They cannot castle.");}
	if (legal){
	    move(row,4,row,2);
	    move(row,0,row,3);
	    if (check(color,p1)){
		move(row,2,row,4);
		move(row,3,row,0);
		board[row][4].setmoved(false);
	        board[row][0].setmoved(false);
		System.out.println("This move would put you in check!");
		return false;
	    }
	else{return true;}
	}
	else{return false;}
    }
    
    
        // checks if En Passant move can be made 

    /*
    public boolean checkEnPassant(int startRow, int startCol, int endRow, int endCol){
	boolean legal = true;
	if (!getPiece(startRow,startCol).equals("p")) {
	    legal = false;
	} 
	else if (startRow!=2 && startRow!=6) {
	    legal = false;
	}
	else if (Math.abs(startRow-endRow)!=2) {
	    legal = false;
	}
	else {
	    pCapture = board[startRow][startCol];
	    int checkRow;
	    if (startRow==2) {
		checkRow=5;
		forward=true;
	    } else {
		checkRow=4;
		forward=false;
	    }
	    for (int i=1;i<9;i++) {
		if (getPiece(!(checkRow,i).equals("p") && Math.abs(startCol-i)==1)) {
		    legal = false;
		}
	    }
	}
	return legal;
    }
*/		    
	
			
    
}










