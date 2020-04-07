import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;
//@Author Klaudia Obieglo, discussed with Kamil Przepiorski,
// Looked at github repos and the Alogrithms Book
/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestants’
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *     Each contestant walks at a given estimated speed.
 *     The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {
	private static final double INFINITY = Integer.MAX_VALUE; 
	int sA,sB,sC;
	String filename;
	int intersections,streets,slowest;
	private TreeMap<Integer,Node> map;
	boolean file=true;
	/**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     * 
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC){
    	//setting up the constructor
    	this.sA=sA;
    	this.sB=sB;
    	this.sC=sC;
    	this.filename=filename;
    	this.initialise();
    }
    

	public void initialise()
	{
		slowest=Math.min(sA,sB);
		slowest=Math.min(slowest, sC);
		
		//read in input and assign the table values
		
		map=new TreeMap<>();
		//FileReader file;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String input =reader.readLine();
			intersections = Integer.parseInt(input);
			input=reader.readLine();
			streets=Integer.parseInt(input);
			if(streets==0 || intersections==0)
			{
				file=false;;
				slowest=-1;
			}
			else {
				
				boolean endOfFile=false;
				while(!endOfFile )
				{
					input=reader.readLine();
					if(input!=null)
					{
						//split the line which has startVertex->EndVertex, Distance
						String[] lineOutput=input.trim().split(" ");
						int vertexA=Integer.parseInt(lineOutput[0]);
						int vertexB= Integer.parseInt(lineOutput[1]);
						double distance=Double.parseDouble(lineOutput[2]) *1000;
						Node node1,node2;
						
						if(map.get(vertexA)==null)
						{
							node1=new Node(vertexA);
							map.put(vertexA, node1);
						}
						else
						{
							node1=map.get(vertexA);
						}
						
						if(map.get(vertexB)==null)
						{
							node2=new Node(vertexB);
							map.put(vertexB, node2);
						}
						else
						{
							node2=map.get(vertexB);
						}
						
							node1.addAdjacentNode(node2, distance);
					}
					
					else 
					{
						//filename=null;
						endOfFile=true;
					}
					
				}
				reader.close();
			}
		} catch (Exception e) {
			//filename=null;
			//System.out.println("excep");
			slowest=-1;
			file=false;
			e.printStackTrace();
		}
	}
    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

    	//if the speed is out or range return -1
    	if((sA<50 ||sA >100) || (sB >100 || sB<50) || (sC <50 || sB>100)||file==false || map.size()==0)
    		return -1;
    	
        double maxDistance = -1;
        //find the max distance 
        for (Node node : map.values()) {
            double distance = getMaxCost(node.id);
            if (distance == Double.MAX_VALUE) return -1;
            
            maxDistance = Math.max(maxDistance, distance);
        }
        System.out.println(maxDistance + "hereee");
        int maximumDistance=(int) Math.ceil(maxDistance / slowest);
        System.out.println("maximumDistance" + maximumDistance);
        return  maximumDistance;
    }

    private class Node {
        int id;
        double cost = Double.MAX_VALUE; //tentative cost
        ArrayList<Path> paths = new ArrayList<>();
        
        Node(int id) {
            this.id = id;
        }

        void addAdjacentNode(Node node, double cost) {
            paths.add(new Path(node, cost));
        }
    }

    private class Path {
        Node destination;
        double cost;

        Path(Node destination, double cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }

    private double getMaxCost(int start) {

        LinkedList<Node> nodes = new LinkedList<>();
        for (Node node : map.values()) {
            if (node.id == start) node.cost = 0;
            else node.cost = Double.MAX_VALUE;
            nodes.add(node);
        }

        for (int i = 0; i < map.values().size(); i++) {
            for (Node node : nodes) {  
                for (Path path : node.paths) {
                    double newCost = node.cost + path.cost;
                    if (newCost < path.destination.cost) {
                        path.destination.cost = newCost;
                    }
                }
            }
        }

        double max = Double.MIN_VALUE;
        for (Node node : map.values()) {
            if (node.cost == Double.MAX_VALUE) return node.cost;
            else if (node.cost > max)
                max = node.cost;
        }
        return max;
    }
}