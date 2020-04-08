import java.io.BufferedReader;
import java.io.FileReader;
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
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {

	int sA,sB,sC;
	String filename;
	int intersections,streets,slowest,fastest;
	double table[][];
	boolean validFile=true;
	private static final double INFINITY = Integer.MAX_VALUE; 
	/**
	 * @param filename: A filename containing the details of the city road network
	 * @param sA, sB, sC: speeds for 3 contestants
	 
	 */
	CompetitionFloydWarshall (String filename, int sA, int sB, int sC){
		//setting up the constructor
		this.filename=filename;
		this.sA=sA;
		this.sB=sB;
		this.sC=sC;
		this.initalise();

	}

	public void initalise()
	{
		fastest=Math.max(sA, sB);
		fastest=Math.max(fastest,sC);
		if(fastest >100)
		{
			slowest=-1;
		}
		else
		{
			slowest=Math.min(sA,sB);
			slowest=Math.min(slowest, sC);
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			String input =reader.readLine();
			System.out.println("hello");
			intersections = Integer.parseInt(input);
			System.out.println(intersections);
			input =reader.readLine();
			streets=Integer.parseInt(input);
			System.out.println(streets);
			if(streets==0 || intersections==0)
			{
				validFile=false;
				slowest=-1;
			}
			else {
				table=new double[intersections][intersections];

				for(int i=0; i<intersections; i++)
				{
					for(int j=0; j<intersections; j++)
					{
						//if i=j put in 0, everything else is infinity for the moment
						if(i==j)
						{
							table[i][j]=0;
						}
						else {
							 
							table[i][j]=INFINITY;
						}
						
					}
				}
				boolean endOfFile=false;
				input=reader.readLine();
				while(!endOfFile )
				{
					if(input!=null)
					{
						//put in the distance into the grids
						String[] lineOutput=input.trim().split(" ");
						table[Integer.parseInt(lineOutput[0])][Integer.parseInt(lineOutput[1])]=Double.parseDouble(lineOutput[2]);
						input=reader.readLine();
					}
					else 
					{
						
						endOfFile=true;
					}
					
				}
				reader.close();
			}
		} catch (Exception e) {
			validFile=false;
			slowest=-1;
			
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}  
		

	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants can meet
	 */
	public int timeRequiredforCompetition(){

		//if the speed is out or range  or file is null return -1
		if((sA<50 ||sA >100) || (sB >100 || sB<50) || (sC <50 || sB>100) || validFile==false)
			return -1;

		//floydWarshall Algorithm
		for (int i = 0; i < intersections; i++){
			for (int j = 0; j < intersections; j++){ 
				for (int k = 0; k < intersections; k++){
					if(table[j][i] + table[i][k] < table[j][k]){
						table[j][k] = table[j][i] + table[i][k];
					}
				}
			}
		}
		double maximumDistance=getMax();
	//	System.out.println("slowrst"+slowest + " maxdist" + maximumDistance);
		//System.out.println("max distnace"+(maximumDistance*1000)/slowest);
		if(maximumDistance == INFINITY)
		{
	//		System.out.println("shouldnt be hee");
			return -1;
		}
		maximumDistance=maximumDistance*1000;
	//	System.out.println("maximumDistanceAttheEnd");
		return (int)Math.ceil(maximumDistance/slowest);
	}
	public double getMax()
	{
		double maximumDistance=-1;
		//go through the table to find max distance
		for(int i=0; i<intersections; i++)
		{
			for(int j=0; j<intersections; j++)
			{
				if(table[i][j]>maximumDistance && i!=j)
				{
					maximumDistance=table[i][j];
				}
			}
		}
		return maximumDistance;
	}

}