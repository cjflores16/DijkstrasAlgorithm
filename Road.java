public class Road
{
    //a road connects two cities
    private City city1;   //one of the cities connected   
    private City city2;   //one of the cities connected
    private int distance; //distance between the two cities along the roadway
    private String name;  //name of roadway like "I87"
    
    
    public Road(City city1, City city2, int distance, String name){
        this.city1 = city1; 
        this.city2 = city2; 
        this.distance = distance; 
        this.name = name; 
    }
    
    public String toString(){
        return name + " for " + distance + " miles between " + city1.getName() + " and " + city2.getName(); 
    }
    
    public int getDistance(){
        return distance;
    }

    public City getNeighborCity(City c){
        if(c.equals(city1)){
            return city2;
        } else {
            return city1;
        }
    }
}
