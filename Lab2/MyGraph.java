import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Collections;
import java.util.HashSet;

public class MyGraph 
{
    ArrayList<Vertex> vertices = new ArrayList<Vertex>();
    boolean dir;
    int nverts, n, comps;

    void readfile_graph(String filename) throws FileNotFoundException  
    {
        int x, y, i, temp, nFiles;
        FileInputStream inFile = new FileInputStream(new File(filename));
        Scanner scan = new Scanner(inFile);
        temp = scan.nextInt(); 
        dir = (temp == 1);
        nverts = scan.nextInt();

        for (i = 0; i <= nverts-1; i++)
        {
            Vertex v = new Vertex(i+1);
            vertices.add(v);
        }

        n = scan.nextInt(); 
        nFiles = n;

        for (i = 1; i <= nFiles; i++)	
        {
            x = scan.nextInt();
            y = scan.nextInt();
            insert_edge(x,y,dir);
        }

        for(i = 0; i <= nverts-1; i++)	
        {
            Collections.sort(vertices.get(i).edges);
        }
    }

    static void process_vertex_early(Vertex v)	
    {
        t++;
        v.entry_time = t;
    }

    static void process_vertex_late(Vertex v)	
    {
        t++;
        v.exit_time = t;
    }

    static void process_edge(Vertex x, Vertex y) 	
    {
        int c = edge_classification(x,y);
        if (c == BACK) System.out.printf("back edge (%d,%d)\n",x.key,y.key);
        else if (c == TREE) System.out.printf("tree edge (%d,%d)\n",x.key,y.key);
        else if (c == FORWARD) System.out.printf("forward edge (%d,%d)\n",x.key,y.key);
        else if (c == CROSS) System.out.printf("cross edge (%d,%d)\n",x.key,y.key);
        else System.out.printf("edge (%d,%d)\n not in valid class=%d",x.key,y.key,c);
    }

    static void initialize_search(MyGraph gr)	
    {
        for(Vertex v : gr.vertices)		
        {
            v.processed = v.discovered = false;
            v.parent = null;
        }
    }

    static final int TREE = 1, BACK = 2, FORWARD = 3, CROSS = 4;
    static int t = 0;

    static int edge_classification(Vertex x, Vertex y)	
    {
        if (y.parent == x) return(TREE);
        if (y.discovered && !y.processed) return(BACK);
        if (y.processed && (y.entry_time > x.entry_time)) return(FORWARD);
        if (y.processed && (y.entry_time < x.entry_time)) return(CROSS);
        System.out.printf("Warning: self loop (%d,%d)\n",x,y);
        return -1;
    }

    void insert_edge(int x, int y, boolean dir) 	
    {
        Vertex v1 = vertices.get(x-1);
        Vertex v2 = vertices.get(y-1);
        v1.edges.add(v2);
        vertices.get(x-1).deg++;
        if(!dir)
            insert_edge(y,x,true);
        else
            n++;
    }

    void remove_edge(Vertex x, Vertex y)  
    {
        if(x.deg<0)
            System.out.println("Warning: no edge --" + x + ", " + y);
        x.edges.remove(y);
        x.deg--;
    }

    void print_graph()	
    {
        for(Vertex v : vertices)	
        {
            System.out.println("vertex: "  + v.key);
            for(Vertex vert : v.edges)
                System.out.print("  adjacency list: " + vert.key);
            System.out.println();
        }
    }

    class Vertex implements Comparable<Vertex> 
    {
        int key, deg, co;
        int color = -1;
        boolean discovered = false;
        boolean processed = false;
        int entry_time = 0;
        int exit_time = 0;
        Vertex parent = null;
        ArrayList<Vertex> edges = new ArrayList<Vertex>();

        public Vertex(int tkey)
        {
            key = tkey;
        }
 
        public int compareTo(Vertex other)
        {
            if (this.key > other.key)
            {
                return 1;
            }         
            else if (this.key < other.key) 
            {
                return -1;
            }
            else
            {
                return 0;
            }
        }
    }

    Vertex unProcessedV()
    {
        for(Vertex v:  vertices)  
        {
            if (! v.processed ) 
            {
               return v;
            }
        }
        return null;
    }

    public ArrayList<HashSet<Integer>> connectCheck(){
        ArrayList<HashSet<Integer>> components = new ArrayList<>();

        HashSet<Integer> vertexComps = new HashSet<>();

        HashSet<Integer> vertexComps2 = new HashSet<>();
        HashSet<Integer> connectedCompVal = new HashSet<>();
        GraphStart3 graph = new GraphStart3();
        int connectedComp = 0;

        for (Vertex v : this.vertices){

            if (!vertexComps.contains(v.key)) 
            {
                connectedComp++;

                if (connectedComp > 1)
                {
                    components.add(vertexComps2);
                    vertexComps2 = new HashSet<>();
                }

                vertexComps.add(v.key);
                vertexComps2.add(v.key);

            }

            for (Vertex edge : v.edges)
            {
                if(!vertexComps.contains(edge))
                {
                    vertexComps.add(edge.key);
                    vertexComps2.add(edge.key);
                }
            }
        }

        components.add(vertexComps2);
        connectedCompVal.add(connectedComp);
        components.add(0, connectedCompVal);
        return components;
    }

    public boolean bipartiteCheck(){

        HashSet<Integer> red = new HashSet<>();
        HashSet<Integer> blue = new HashSet<>();

        for (Vertex v : this.vertices){

            if (!blue.contains(v.key)){
                red.add(v.key);
                for (Vertex edgeBlue: v.edges){

                    if (red.contains(edgeBlue.key)){
                        return false;
                    }
                    else{
                        blue.add(edgeBlue.key);
                    }

                }
            }

            else if (blue.contains(v.key)) {
                for (Vertex edgeRed : v.edges){

                    if (blue.contains(edgeRed.key)){
                        return false;
                    }
                    else{
                        red.add(edgeRed.key);
                    }

                }

            }
        }

        return true;
    }


}
