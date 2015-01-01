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
	return board[row][col];
    }
    public String getPieceColor(int row, int col){
	return board[row][col].getcolor();
    }
}










