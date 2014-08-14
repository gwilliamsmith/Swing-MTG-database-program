package databaseproject;

public class MTGDBResult {
    public String cardName;
    public double cardPrice;
    
    public MTGDBResult(String name, double price){
        cardName = name;
        cardPrice = price;
    }//end constructor
    
    @Override
    public String toString(){
        return ("Card Name: " + cardName + " Card Price: " + cardPrice);
    }//end toString
}//end class declaration