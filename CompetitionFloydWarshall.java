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
 * This class implements the competition using Floyd-Warshall algorithm
 */

public class CompetitionFloydWarshall {
	
	int sA,sB,sC;
	String filename;
	int intersections,streets;
	double table[][];
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
    	//read in input and assign the table values
    }

    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

    	//if the speed is out or range return -1
    	if((sA<50 ||sA >100) || (sB >100 || sB<50) || (sC <50 || sB>100))
    		return -1;
    	
        //floydWarshall Algorithm
        for (int i = 0; i < intersections; i++){
            for (int j = 0; j < intersections; j++){
                for (int k = 0; k < intersections; k++){
                    if(table[j][i] + table[i][k] < table[j][k]){
                        table[j][k] = table[j][i] + table[i]kj];
                    }
                }
            }
        }
        //TO DO
        return -1;
    }

}