import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
/*
 * Differences in Implementation 
 * 
 * Floyd Warshall
 * For floyd warshall I used a 2d array to implement the algorithm. I used it because the way we discussed it in lectures
 * looked like a table to me. However, if I have printed out the whole 2d table, there would have been a lot of Infinity symbols
 * if the files I passed through where relativaly small. This would lead to a lot of wasted space.
 * 
 * Dijkstra
 * For Dijkstra I used a different method to the one in the Algorithms book. I have found that to be very long and confusing. 
 * I used a TreeMap to implement dijsktra. I used nodes with the treemap and in the nodes i stored all the important 
 * information needed., the nodes contained the start node, and each node had its own arraylist which represented all the nodes
 * that it had paths to. In these arraylists which I called paths, we would find the end node and the distance between the two nodes.
 * This implementation seems to me to be much simpler than the one in the books but it also saves space as the only paths 
 * that are identified are the ones that exist in the document.
 * 
 * Differences in Performance
 * Floyd Warshall
 * Floyd Warshall has a performance of O(V^3). It has a this performance because of the 3 nested for loops that the algorithm
 * needs.
 * 
 * Dijkstra has a worst performance of O(V^3 log V). It has this performance as it has to find the distance from each start node
 * to each end node. However it does not waste time on creating edges that do not exist unlike Floyd Warshall. Only edges
 * that exist are defined as paths and only these are searched.
 * 
 */
public class CompetitionTests {

    @Test
    public void testDijkstraConstructor(){
        CompetitionDijkstra dijkstra = new CompetitionDijkstra("tinyEWD.txt", 70,60,50);
        assertEquals("constructor testing", dijkstra.slowest, 50);

    }
    @Test
    public void testDijkstra()
    {
    	CompetitionDijkstra dijkstraD = new CompetitionDijkstra("tinyEWD.txt", 50,80,60);
        assertEquals("Testing tinyEWD",38, dijkstraD.timeRequiredforCompetition());
       	CompetitionDijkstra dijkstraE = new CompetitionDijkstra("tinyEWD.txt", 150,80,60);
        assertEquals("Testing dijkstra with too high speed",-1, dijkstraE.timeRequiredforCompetition());
    	CompetitionDijkstra dijkstra = new CompetitionDijkstra("WrongFile", 70,54,50);
        assertEquals("Testing with incorrect File",-1, dijkstra.timeRequiredforCompetition());
    	CompetitionDijkstra dijkstraA = new CompetitionDijkstra("tinyEWD.txt", -59,54,50);
        assertEquals("Testing with negative speed",-1, dijkstraA.timeRequiredforCompetition());
    	CompetitionDijkstra dijkstraB = new CompetitionDijkstra(null, -59,54,50);
        assertEquals("Testing Dijkstra with null filename",-1, dijkstraB.timeRequiredforCompetition());
       
    }

    @Test
    public void testFWConstructor() {
    	CompetitionFloydWarshall floyWar = new CompetitionFloydWarshall("tinyEWD.txt", 70,60,84);
        assertEquals("constructor testing", floyWar.slowest, 60);

    }
    
    @Test
    public void testFloyd()
    {
    	CompetitionFloydWarshall floyWar = new CompetitionFloydWarshall("WrongFile", 70,60,84);
        assertEquals("Testing with incorrect Filename", -1, floyWar.timeRequiredforCompetition());
    	CompetitionFloydWarshall floyWarA = new CompetitionFloydWarshall("tinyEWD.txt", -59,60,84);
        assertEquals("Testing with negative speed", -1, floyWarA.timeRequiredforCompetition());
        CompetitionFloydWarshall floyWarB = new CompetitionFloydWarshall(null, 50, 80, 60);
        assertEquals("Test competition with null filename", -1, floyWarB.timeRequiredforCompetition());
     
        CompetitionFloydWarshall floyWarC = new CompetitionFloydWarshall("tinyEWD.txt", 50, 80, 60);
        assertEquals("Test Floyd with the tinyEWD file", 38, floyWarC.timeRequiredforCompetition());
        CompetitionFloydWarshall floyWarD = new CompetitionFloydWarshall("tinyEWD.txt", 200, 80, 60);
        assertEquals("Test Floyd with too high speed", -1, floyWarD.timeRequiredforCompetition());
        
        CompetitionFloydWarshall floyWarE = new CompetitionFloydWarshall("tinyEWD.txt", 40, 80, 60);
        assertEquals("Test Floyd with speed A being too low", -1, floyWarE.timeRequiredforCompetition());
    }

    
}
