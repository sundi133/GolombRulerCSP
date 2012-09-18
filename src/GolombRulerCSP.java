import java.util.Scanner;


public class GolombRulerCSP {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub 

		System.out.println("Input M and L with a space in between ex: 4 6\n");
		Scanner in2 = new Scanner(System.in);
		int M=in2.nextInt();
		int L=in2.nextInt();
		System.out.println("Golomb Ruler configuration is ...");
		Graph g = new Graph();
		int iter=M-1;
		while(iter>=0){
			g.addVertex(Integer.toString(iter));
			iter--;
		}
		iter=M-1;
		for(int i=0;i<M-1;i++){
			g.addEdge(i, i+1);
		}
		long start = System.currentTimeMillis();
		g.addRulerConfig(M,L);
		g.golombRuler();
		long end = System.currentTimeMillis();
	    System.out.println("BT execution time " + (end-start) + " msecs");
		System.out.println();
		start = System.currentTimeMillis();
		g.addRulerConfig(M,L);
		g.golombRulerFC();
		end = System.currentTimeMillis();
	    System.out.println("FC execution time " + (end-start) + " msecs");
		
		//System.out.println("Done.");

		return;
	}

}
