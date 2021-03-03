import java.util.ArrayList; 
public class City
{
    //General attributes:
    private String name;  //name of a city, like "Albany"
    private ArrayList<Road> roads;  //list of roads that are connected to the particular city 
      
    //Attributes set during Dijkstra's Algorithm:
    private int distFromInitial;  //keeps track of total distance to initial city
    private boolean visited;    //keeps track of whether or not city has been visited in Dijkstra's algorithm
    
    
    public City(String name){
        roads = new ArrayList<Road>(); 
        this.name = name; 
        distFromInitial =Integer.MAX_VALUE;  
        visited = false; 
    }
    
    public String toString(){
        String out = name; 
        out += "\n";
        out += "Roads connecting " + name + ":\n";
        for(Road r: roads){
            out +=  r.toString() + "\n"; 
        }
        out += "\n";
        return out; 
    }
    
    public String getName(){
        return name; 
    }
    
    public boolean equals(City other){
        return this.name.equals(other.name); 
    }
    
    public void addRoad(Road r){
        roads.add(r);
    }
    
    public ArrayList<Road> getRoadList(){
        return roads;
    }
    
    public void setDistanceFromInitial(int newDist){
        distFromInitial = newDist;
    }
    
    public int getDistanceFromInitial(){
        return distFromInitial;
    }
    
    public boolean hasBeenVisited(){
        return visited;
    }
    
    public void markAsVisited(){
        visited = true;
    }
}
