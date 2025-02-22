import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.TreeMap;
//@Author Klaudia Obieglo, discussed with the demonstrator Kamil Przepiorski,
// Looked at  aglorithm book ,stack overflow, youtube, github
/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestantsí
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
		map=new TreeMap<>();
		slowest=Math.min(sA,sB);
		slowest=Math.min(slowest, sC);

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
						int startVertex=Integer.parseInt(lineOutput[0]);
						int endVertex= Integer.parseInt(lineOutput[1]);
						double distance=Double.parseDouble(lineOutput[2]) *1000; //make distance more measurable
						Node node1,node2;

						if(map.get(startVertex)==null)
						{
							node1=new Node(startVertex);
							map.put(startVertex, node1);
						}
						else
						{
							node1=map.get(startVertex);
						}

						if(map.get(endVertex)==null)
						{
							node2=new Node(endVertex);
							map.put(endVertex, node2);
						}
						else
						{
							node2=map.get(endVertex);
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
			
		}
	}
	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public int timeRequiredforCompetition(){

		//if the speed is out or range return -1
		if((sA<50 ||sA >100) || (sB >100 || sB<50) || (sC <50 || sC>100)||file==false || map.size()==0)
			return -1;

		double maxDistance = -1;
		//find the max distance 
		for (Node node : map.values()) {
			double distance = getMaxCost(node.id);
			if (distance == Double.MAX_VALUE) 
			{
				return -1;
			}

			maxDistance = Math.max(maxDistance, distance);
		}
		//System.out.println(maxDistance + "hereee");
		int maximumDistance=(int) Math.ceil(maxDistance / slowest);
		//System.out.println("maximumDistance" + maximumDistance);
		return  maximumDistance;
	}

	private class Node {
		int id;
		double cost = Double.MAX_VALUE;
		ArrayList<Path> pathways = new ArrayList<>();

		Node(int id) {
			this.id = id;
		}
		//adding nodes that are beside current node
		void addAdjacentNode(Node node, double cost) {
			pathways.add(new Path(node, cost));
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
		//get the max cost
		LinkedList<Node> nodes = new LinkedList<>();
		//set nodes to be double max unless they are the starting node
		for (Node node : map.values()) 
		{
			if (node.id == start)  //if the node is the start node its cost is 0 as theres no distance from that node to itself.
			{
				node.cost = 0;
			}
			else
			{
				node.cost = Double.MAX_VALUE;
			}
			nodes.add(node);
		}
		//get the destination cost for each node
		for (int i = 0; i < map.values().size(); i++)
		{
			for (int j=0; j<nodes.size(); j++)
			{  
				for (Path path : nodes.get(j).pathways) 
				{
					double newCost = nodes.get(j).cost + path.cost;
					if (newCost < path.destination.cost) 
					{
						path.destination.cost = newCost;
					}
				}
			}
		}
		
		double max =Double.MIN_VALUE;
		for (int i=0 ; i<nodes.size(); i++)
		{
			
			if (nodes.get(i).cost == Double.MAX_VALUE) 
			{
				return nodes.get(i).cost;
			}
			else if (nodes.get(i).cost > max)
			{
				max = nodes.get(i).cost;
			}
				
		}
		return max;
	}
}