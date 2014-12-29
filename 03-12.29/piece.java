public class piece{
    private String color;
    private String name;
    public static final String reset = "\u001B[0m";
    public static final String black = "\u001B[30m";
    public static final String red = "\u001B[31m";
    public static final String green = "\u001B[32m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    public static final String white = "\u001B[37m";

    public piece (){
	setcolor("black");
	setname(" ");
    }
    public void setcolor(String Color){
	color = Color;
    }
    public String getcolor(){
	return color;
    }
    public void setname(String Name){
	name = Name;
    }
    public String getname(){
	return name;
    }
    public String toString(){
	if (color.equals("black")){return black+name+reset;}
	else if(color.equals("red")){return red+name+reset;}
	else if(color.equals("green")){return green+name+reset;}
	else if(color.equals("yellow")){return yellow+name+reset;}
	else if(color.equals("blue")){return blue+name+reset;}
	else if(color.equals("purple")){return purple+name+reset;}
	else if(color.equals("cyan")){return cyan+name+reset;}
	else if(color.equals("white")){return white+name+reset;}
	return "Error";      
    }
}
   

