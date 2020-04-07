import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

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
