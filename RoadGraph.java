/**
 * NOTE: For HW 16R, NO coding is required in this class. 
 * 
 */
import java.util.*;
import java.io.*;  
public class RoadGraph
{

    //HashMap containing all cities.  The key is a String like "Albany" and the value is the corresponding City object.
    private HashMap<String, City> allCities;

    //this is the initial city where the Dijkstra's algorithm will start
    private City initialCity; 

    public RoadGraph(String fileName){
        allCities = new HashMap<String, City>();  //Initialize an empty HashMap
        this.readCityFile(fileName);    //Read in the city file
        initialCity = null;  //initialCity for Dijkstra's Algorithm.  Set to null initially; this will get set within the dijkstra() method.
    }

    private void readCityFile(String fileName){
        //Initializing the Scanner object
        //try...catch is for handling errors and is required in case Java can't find file 
        Scanner reader;
        try{
            reader = new Scanner(new File(fileName)); 
        }catch(FileNotFoundException e){
            System.out.println("Error while reading file."); 
            return; 
        } 

        //Going through the input file one road at a time
        while(reader.hasNext()){
            String s = reader.nextLine(); 
            String[] parts = s.trim().split("\\s+"); 

            //skip over any bad lines by continuing the while loop (going back to the top)
            if(parts.length != 4){continue;}

            String city1Name = parts[0]; 
            String city2Name = parts[1]; 
            int d = Integer.parseInt(parts[2]); 
            String roadName = parts[3];

            //The starter code reads in the input file for you.
            //But you still have to do the following:
            //(1) Make sure all cities specified get into the hash map - remember that you'll often see the same city more than once becasue the input file specifies ROADS
            if(!(allCities.containsKey(city1Name))){
                City c1 = new City(city1Name);
                allCities.put(city1Name, c1);
            }
            if(!(allCities.containsKey(city2Name))){
                City c2 = new City(city2Name);
                allCities.put(city2Name, c2);
            }

            //(2) Create the road. 
            Road r = new Road(allCities.get(city1Name), allCities.get(city2Name), d, roadName);
            //(3) Add the road to each city's road list
            allCities.get(city1Name).addRoad(r);
            allCities.get(city2Name).addRoad(r);
        }

    }

    public String toString(){
        String out = ""; 
        for(String cityName: allCities.keySet()){
            out+= allCities.get(cityName).toString(); 
        }
        return out;  
    }

    public City getAndRemoveClosestCity(ArrayList<City> cityList){
        City closest = cityList.get(0);
        for(int i = 1; i < cityList.size(); i++){
            int a = cityList.get(i).getDistanceFromInitial();
            if(a < closest.getDistanceFromInitial()){
                closest = cityList.get(i);
            }
        }
        cityList.remove(cityList.indexOf(closest));
        return closest;
    }

    public void dijkstra(String initialCityName){
        //Step 1: Get the initial city from hash map and set its distance to 0 (because it is 0 away from itself)
        initialCity = allCities.get(initialCityName);
        initialCity.setDistanceFromInitial(0);
        //Step 2: Fill the unvisited set with all of the cities (which will include initial)
        ArrayList<City> unvisitedSet = new ArrayList<City>();
        for(String c: allCities.keySet()){
            unvisitedSet.add(allCities.get(c));
        }
        //Step 3: Run the algorithm as long as there are still cities in the univisted set 
        while(unvisitedSet.size() > 0){       
            //Important ideas here:
            //Set the current city to the unvisited city which is closest to the initial.
            //Loop through all the roads of the city to update distances for neighboring cities
            //Mark the current city as visited. 
            City currentCity = getAndRemoveClosestCity(unvisitedSet);
            currentCity.markAsVisited();
            for(Road r: currentCity.getRoadList()){
                if(!(r.getNeighborCity(currentCity).hasBeenVisited())){
                    City neighbor = r.getNeighborCity(currentCity);
                    int a = currentCity.getDistanceFromInitial() + r.getDistance();
                    if(a < neighbor.getDistanceFromInitial()){
                        neighbor.setDistanceFromInitial(a);
                    }
                }          
            }
        }   

    }

    public void printDijkstraResults(){
        for(String a: allCities.keySet()){
            System.out.println(allCities.get(a).getName() + ": " + allCities.get(a).getDistanceFromInitial() + " miles from " + initialCity.getName());
        }
    }

    public void printBestRouteTo(String destName){
        System.out.println("The best route from " + initialCity.getName() + " to " + destName + ":");
     
        System.out.println("Total mileage: " + allCities.get(destName).getDistanceFromInitial() + " miles");
    }

}
