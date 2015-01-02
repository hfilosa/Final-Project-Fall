public class board{
    private piece[][] board = new piece[8][8];
    private String p1header = "   a   b   c   d   e   f   g    h\n";
    private String p2header = "   h   g   f   e   d   c   b    a\n";
    private String divider = "  ---------------------------------\n";

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

    //this prints the board facing player 1
    public String print1(){
	String ans = p1header;
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
	return ans;
    }

    public String print2(){
	String ans = p2header;
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
	return ans;
    }

    public piece getPiece(int row, int col){
	if (row<8 && row>=0 && col<8 && col>=0){
	    return board[row][col];
	}
	piece x = new piece();
	return x;
    }
    public String getPieceColor(int row, int col){
	return board[row][col].getcolor();
    }
    //This is used to move the piece AFTER the move has been authenticated
    public void move(int startRow,int startCol,int endRow,int endCol){
	board[endRow][endCol] = board[startRow][startCol];
	board[startRow][startCol] = new piece();
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
	    if (getPiece(krow,kcol).equals(piece) && getPieceColor(krow,kcol).equals(color)){
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
	if (checksweep(krow,kcol,"R",color,1,0)){return true;}
	if (checksweep(krow,kcol,"R",color,-1,0)){return true;}
	if (checksweep(krow,kcol,"R",color,0,1)){return true;}
	if (checksweep(krow,kcol,"R",color,0,-1)){return true;}
	if (checksweep(krow,kcol,"Q",color,1,0)){return true;}
	if (checksweep(krow,kcol,"Q",color,-1,0)){return true;}
	if (checksweep(krow,kcol,"Q",color,0,1)){return true;}
	if (checksweep(krow,kcol,"Q",color,0,-1)){return true;}
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
        if (pawnCheck(krow,kcol,color,p1)){return true;}
	if (diagonalCheck(krow,kcol,color)){return true;}
	if (horizontalCheck(krow,kcol,color)){return true;}
	if (knightCheck(krow,kcol,color)){return true;}
	return false;
    }

    //the end coordinates have already been checked that they are not a players own pieces or outside the board
    public boolean pawnMove(int startRow,int startCol,int endRow,int endCol,boolean p1){
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
		    return true;
		}
		else {return false;}
	    }
	}
	if (Math.abs(startCol-endCol)==1){
	    if (diff==1){
		if (!(getPiece(endRow,endCol).equals(" "))){
		    return true;
		}
		else {return false;}
	    }
	    else {return false;}
	}
	else {return false;}
    }
}










