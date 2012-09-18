class StackX
{
	private final int SIZE = 50;
	private int[] st;
	private int top;
	// ------------------------------------------------------------
	public StackX()           // constructor
	{
		st = new int[SIZE];    // make array
		top = -1;
	}
	// ------------------------------------------------------------
	public void push(int j)   // put item on stack
	{ st[++top] = j; }
	// ------------------------------------------------------------
	public int pop()          // take item off stack
	{ 


		return st[top--]; 

	}
	// ------------------------------------------------------------
	public int peek()         // peek at top of stack
	{ return st[top]; }
	// ------------------------------------------------------------
	public boolean isEmpty()  // true if nothing on stack
	{ return (top == -1); }

	public int numberOfElements()  // true if nothing on stack
	{ 
		return (top + 1); 
	}
	// ------------------------------------------------------------
}  // end class StackX

class Queue
{
	private final int SIZE = 50;
	private int[] queArray;
	private int front;
	private int rear;

	public Queue()            // constructor
	{
		queArray = new int[SIZE];
		front = 0;
		rear = -1;
	}
	// -------------------------------------------------------------
	public void insert(int j) 
	{
		if(rear == SIZE-1)
			rear = -1;
		queArray[++rear] = j;
	}
	// -------------------------------------------------------------
	public int remove()       
	{
		int temp = queArray[front++];
		if(front == SIZE)
			front = 0;
		return temp;
	}
	// -------------------------------------------------------------
	public boolean isEmpty()  
	{
		return ( rear+1==front || (front+SIZE-1==rear) );
	}
	// -------------------------------------------------------------
}  // end class Queue

class Vertex
{
	public String label;        
	public boolean wasVisited;
	// -------------------------------------------------------------
	public Vertex(String lab)   
	{
		label = lab;
		wasVisited = false;
	}
	// -------------------------------------------------------------
} 
class Graph
{
	private final int MAX_VERTS = 100;
	private Vertex vertexList[]; // list of vertices
	private int adjMat[][];      // adjacency matrix
	private int nVerts;          // current number of vertices
	private Queue theQueue;
	private StackX theStack;
	boolean solutionfoundids=false;
	private int adjMatRuler[][];
	int M;
	int L;
	int checkpoint[];
	int RulerwithMRVArray[][];
	boolean continueMRV=true;
	int MAX;
	private int[][] arrayAss;

	public Graph()               // constructor
	{
		vertexList = new Vertex[MAX_VERTS];
		// adjacency matrix
		adjMat = new int[MAX_VERTS][MAX_VERTS];
		nVerts = 0;
		for(int j=0; j<MAX_VERTS; j++)      // set adjacency
			for(int k=0; k<MAX_VERTS; k++)   //    matrix to 0
				adjMat[j][k] = 0;
		theQueue = new Queue();
		theStack = new StackX();
		MAX=1000000;
	}  // end constructor
	// -------------------------------------------------------------
	public void addVertex(String lab)
	{
		vertexList[nVerts++] = new Vertex(lab);
	}
	// -------------------------------------------------------------
	public void addEdge(int start, int end)
	{
		adjMat[start][end] = 1;
		adjMat[end][start] = 1;
	}
	public int checkEdgeExists(int start, int end)
	{
		if(adjMat[start][end] == 1 || adjMat[end][start] == 1)
			return 1;
		else
			return 0;

	}

	// -------------------------------------------------------------
	public void displayVertex(int v)
	{
		//System.out.println(vertexList[v].label);
	}
	// -------------------------------------------------------------
	public void bfs()                   // breadth-first search
	{                                // begin at vertex 0
		vertexList[0].wasVisited = true; // mark it
		displayVertex(0);                // display it
		theQueue.insert(0);              // insert at tail
		int v2; 

		while( !theQueue.isEmpty() )     // until queue empty,
		{
			int v1 = theQueue.remove();   // remove vertex at head
			while( (v2=getAdjUnvisitedVertex(v1)) != -1 )
			{                                  // get one,
				vertexList[v2].wasVisited = true;  // mark it
				displayVertex(v2);                 // display it
				theQueue.insert(v2);               // insert it
			}   // end while
		}  // end while(queue not empty)

		// queue is empty, so we're done
		for(int j=0; j<nVerts; j++)             // reset flags
			vertexList[j].wasVisited = false;
	}  // end bfs()


	// returns an unvisited vertex adj to v
	public int getAdjUnvisitedVertex(int v)
	{
		for(int j=0; j<nVerts; j++)
			if(adjMat[v][j]==1 && vertexList[j].wasVisited==false)
				return j;
		return -1;
	}  // end getAdjUnvisitedVertex()
	// -------------------------------------------------------------

	//golombRuler BT
	public void golombRulerBT() {
		vertexList[0].wasVisited = true;  // mark it
		//displayVertex(0);                 // display it
		theStack.push(0);                 // push it
		checkpoint[0]=0;//assign 0 to first node root 3>2>1>0
		adjMatRuler[0][0]=0;

		while( !theStack.isEmpty() )      // until stack empty,
		{
			// get an unvisited vertex adjacent to stack top
			int v = getAdjUnvisitedVertex( theStack.peek() );
			////System.out.println("popped out beg" + (theStack.peek()));
			if(v == -1){                    // if no such vertex,{
				theStack.pop();

			}
			else                           // if it exists,
			{
				//int conflict= checkValididty(v,nextInt()+1);
				int i=1;
				int conflict=1;
				int COUNTER=0;
				if(checkpoint[v]!=-1){//means a BT occured, take adjMatRuler to old state.
					//COUNTER=checkpoint[v]+1;
					
						if(getNextassigned(v,checkpoint[v-1])==-2){
							COUNTER=getNextassigned(v);
							if(COUNTER==-1){
								int pop = theStack.pop();
								continue;
							}
								
						}else {
							COUNTER=checkpoint[v-1];
						}
					
					
				}
					
				//while(conflict!=0 && (nextInt(v)+i-1) < L){
				while(conflict!=0 && COUNTER <= L){
					conflict= checkValididtyBT(v,COUNTER);//CHECK VALIIDTY IF CONSTRAINTS ARE SATISFIED OR NOT

					//System.out.println("while " +  COUNTER + "," + v);
					for(int i2=0;i2<M;i2++){
						for(int j=0;j<M;j++){
							//System.out.print( " " + adjMatRuler[i2][j]);
						}
						//System.out.println();
					}
					i++;
					if(conflict==1)
						COUNTER++;
					
				}
				if(COUNTER > L || (COUNTER==L && v < M-1)){//IF > CONSTRAINTS ARE NOT SATISFIED , SO BACKTRACK AND INCREMENT THE VALUE OF THE LAST VALID VARIABLLE
					////System.out.println("sundi t2 : " + (nextInt(v)+i-1));
					////System.out.println("sundi t2 v-1: " + (v-1));

					vertexList[v-1].wasVisited = false;
					vertexList[v].wasVisited = false;
					for(int k=0;k<M;k++)
						adjMatRuler[v-1][k]=-1;
					for(int k=0;k<M;k++)
						adjMatRuler[k][v-1]=-1;
					//if(COUNTER==L && v < M-1)
						
					for(int s=v+1;s<M;s++){
						checkpoint[s]=-1;
					}
					int pop = theStack.pop();
					
					////System.out.println("popped out" + pop + (theStack.peek()));

					continue;

				}
				/* 1. IF THE VALUE ID VALID, ASSIGN THAT VALID VALUE.
				 * 2. STORE THE DIFFERNCE IN THE DIFFERENCE ARRAY WHICH 
				 *    STORES DIFFERENCE BETWEEN ALL THE VALID ASSIGNED PAIR VARIABLES, 
				 *    THIS HELPS IS CHECKING THE CONSTRAINTS, THE DIFFERENCE ARRAY IS adjMatRuler
				 * 3. THE VALID VALUES ARE ASSIGNED TO ARRAY checkpoint[].    
				 */

				checkpoint[v]=COUNTER;
				addAssigned(v,COUNTER);
				for(int p=0;p<M;p++){

					if(checkpoint[p]>=0){
						for(int m=0;m<v+1;m++){
							for(int n=0;n<v+1;n++){

								
								int val=checkpoint[m]-checkpoint[n]>0?checkpoint[m]-checkpoint[n]:checkpoint[n]-checkpoint[m];
								adjMatRuler[m][n]=val;
								////System.out.print( "value assigned " + val + m + n + checkpoint[p] + COUNTER);


							}
						}
					}
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				vertexList[v].wasVisited = true;  // mark it

				//displayVertex(v);                 // display it

				theStack.push(v);                 // push it
			}

		}  // end while
		// stack is empty, so we're done
		for(int i=0;i<M;i++){
			for(int j=0;j<M;j++){
				////System.out.print( "test " + adjMatRuler[i][j]);
			}
			////System.out.println();
		}
		////System.out.println( "Values assigned are ");
		if(checkpoint[M-1]!=L ){
			//System.out.print("Golomb Ruler is not possible");
		}
		else{
			for(int i=0;i<M;i++){
				//if(i==M-1)
					//System.out.print( "" + checkpoint[i] + ".");
				//else
					//System.out.print( "" + checkpoint[i] + ",");
			}
		}
		for(int j=0; j<nVerts; j++)          // reset flags
			vertexList[j].wasVisited = false;
	}
	private int getNextassigned(int v) {
		for(int k=0;k<L+1;k++){
			if(arrayAss[v][k]==-1){
				return k;
			}
			
		}
		return 0;
		
	}
	private int getNextassigned(int v,int value) {
		for(int k=0;k<L+1;k++){
			if(arrayAss[v][k]!=value){
				
			}
			else{
				return -2;
			}
		}
		
		return 0;
	}
	private void addAssigned(int v, int cOUNTER) {


		
		//int k;
		/*for(k=0;k<L;k++){
			if(arrayAss[v][k]==-1){
				break;
			}
		}*/
		
		arrayAss[v][cOUNTER]=cOUNTER;
		
		
		
	}
	// END OF gOLOMBrULER BT

	//golombRuler with backtrack
	public void golombRuler() {
		vertexList[0].wasVisited = true;  // mark it
		//displayVertex(0);                 // display it
		theStack.push(0);                 // push it
		checkpoint[0]=0;//assign 0 to first node root 3>2>1>0
		adjMatRuler[0][0]=0;

		while( !theStack.isEmpty() )      // until stack empty,
		{
			// get an unvisited vertex adjacent to stack top
			int v = getAdjUnvisitedVertex( theStack.peek() );
			////System.out.println("popped out beg" + (theStack.peek()));
			if(v == -1){                    // if no such vertex,{
				theStack.pop();

			}
			else                           // if it exists,
			{
				//int conflict= checkValididty(v,nextInt()+1);
				int i=1;
				int conflict=1;
				//while(conflict!=0 && (nextInt(v)+i-1) < L){
				while(conflict!=0 && (nextInt(v)+i-1) <= L){
					conflict= checkValididty(v,nextInt(v)+i);//CHECK VALIIDTY IF CONSTRAINTS ARE SATISFIED OR NOT

					//System.out.println("while " +  (nextInt(v)+i) + "," + v);
					for(int i2=0;i2<M;i2++){
						for(int j=0;j<M;j++){
							////System.out.print( "test " + adjMatRuler[i2][j]);
						}
						////System.out.println();
					}
					i++;
				}
				if((nextInt(v)+i-1) > L){//IF > CONSTRAINTS ARE NOT SATISFIED , SO BACKTRACK AND INCREMENT THE VALUE OF THE LAST VALID VARIABLLE
					////System.out.println("sundi t2 : " + (nextInt(v)+i-1));
					////System.out.println("sundi t2 v-1: " + (v-1));

					vertexList[v-1].wasVisited = false;
					vertexList[v].wasVisited = false;
					for(int k=0;k<M;k++)
						adjMatRuler[v-1][k]=0;
					for(int k=0;k<M;k++)
						adjMatRuler[k][v-1]=0;
					int pop = theStack.pop();
					////System.out.println("popped out" + pop + (theStack.peek()));

					continue;

				}
				/* 1. IF THE VALUE ID VALID, ASSIGN THAT VALID VALUE.
				 * 2. STORE THE DIFFERNCE IN THE DIFFERENCE ARRAY WHICH 
				 *    STORES DIFFERENCE BETWEEN ALL THE VALID ASSIGNED PAIR VARIABLES, 
				 *    THIS HELPS IS CHECKING THE CONSTRAINTS, THE DIFFERENCE ARRAY IS adjMatRuler
				 * 3. THE VALID VALUES ARE ASSIGNED TO ARRAY checkpoint[].    
				 */

				checkpoint[v]=nextInt(v)+i-1;
				for(int p=0;p<M;p++){
					if(checkpoint[p]!=-1){
						for(int m=0;m<M;m++){
							for(int n=0;n<M;n++){
								int val=checkpoint[m]-checkpoint[n]>0?checkpoint[m]-checkpoint[n]:checkpoint[n]-checkpoint[m];
								adjMatRuler[m][n]=val;


							}
						}
					}
				}
				vertexList[v].wasVisited = true;  // mark it

				//displayVertex(v);                 // display it

				theStack.push(v);                 // push it
			}

		}  // end while
		// stack is empty, so we're done
		for(int i=0;i<M;i++){
			for(int j=0;j<M;j++){
				////System.out.print( "test " + adjMatRuler[i][j]);
			}
			////System.out.println();
		}
		////System.out.println( "Values assigned are ");
		if(checkpoint[M-1]!=L ){
			System.out.print("Golomb Ruler is not possible");
		}
		else{
			for(int i=0;i<M;i++){
				if(i==M-1)
					System.out.print( "" + checkpoint[i] + ".");
				else
					System.out.print( "" + checkpoint[i] + ",");
			}
		}
		for(int j=0; j<nVerts; j++)          // reset flags
			vertexList[j].wasVisited = false;
	}
	// END OF gOLOMBrULER

	public int checkValididtyBT(int k, int val){
		int value=0;

		for(int s=0;s<k;s++){
			if(checkpoint[s]>=0){
				//System.out.println(" " +checkpoint[s]);
			}
		}
		for(int s=0;s<k;s++){
			if(checkpoint[s]>=0){
				
				value=val-checkpoint[s]>0?val-checkpoint[s]:checkpoint[s]-val;
				for(int i=0;i<k;i++){
					for(int j=0;j<M;j++){
						if(adjMatRuler[i][j]==value){
							////System.out.print(" " + value + " " + checkpoint[s] + " " + val + i +j);
							return 1;
						}

					}
				}
			}
		}
		return 0;

	}


	public int checkValididty(int k, int val){
		int value=0;
		for(int s=0;s<k;s++){
			if(checkpoint[s]>=0){	
				value=val-checkpoint[s];
				for(int i=0;i<k;i++){
					for(int j=0;j<M;j++){
						if(adjMatRuler[i][j]==value)
							return 1;

					}
				}
			}
		}
		return 0;

	}

	public int checkValididtyFC(int k, int val){
		int value=0;
		// extra check for forward checking.
		if(L-checkpoint[k-1]< M-k+1){
			return 2;
		}
		for(int s=0;s<k;s++){
			if(checkpoint[s]>=0){	
				value=val-checkpoint[s];
				for(int i=0;i<k;i++){
					for(int j=0;j<M;j++){
						if(adjMatRuler[i][j]==value)
							return 1;

					}
				}
			}
		}
		return 0;

	}

	
	private int nextInt(int v) {
		// TODO Auto-generated method stub
		int max=checkpoint[0];
		for(int s=0;s<v+1;s++){
			if(max<checkpoint[s])
				max=checkpoint[s];
		}
		for(int s=v+1;s<M;s++){
			checkpoint[s]=0;
		}
		return max;
	}
	public void ids() {
		// TODO Auto-generated method stub
		int i=0;

		while(solutionfoundids==false){
			dfs(i);
			i++;
		}
	}
	private void dfs(int i) {
		vertexList[0].wasVisited = true;  // mark it
		//System.out.println( "Trace " + i + " : ");
		displayVertex(0);                 // display it
		theStack.push(0);                 // push it

		while( !theStack.isEmpty() )      // until stack empty,
		{
			// get an unvisited vertex adjacent to stack top
			int v = getAdjUnvisitedVertex( theStack.peek() );
			if(v == -1 || theStack.numberOfElements()>(i+1)){                    // if no such vertex,
				theStack.pop();
			}
			else                           // if it exists,
			{
				if(theStack.numberOfElements()>(i+1)){
					//if in IDS depth is greater than i , dont do anything.
					theStack.pop();
				}else{
					vertexList[v].wasVisited = true;  // mark it
					displayVertex(v);                 // display it
					theStack.push(v);                 // push it   
				}


			}

		}  // end while

		// stack is empty, so we're done
		for(int j=0; j<nVerts; j++)          // reset flags
			vertexList[j].wasVisited = false;

	}
	public void unmarkAllVert() {
		for(int j=0; j<nVerts; j++)          // reset flags
			vertexList[j].wasVisited = false;
	}
	public void addEdge(String string, String string2) {
		// TODO Auto-generated method stub

	}

	public void addRulerConfig(int m, int l) {

		M= m;
		L=l;
		adjMatRuler = new int[m][m];
		for(int k=0;k<m;k++){
			for(int k1=0;k1<m;k1++){
				adjMatRuler[k][k1]=-1;
			}
		}
		checkpoint = new int[m];
		for(int k1=0;k1<m;k1++){
			checkpoint[k1]=-1;
		}

		arrayAss=new int[M][L+1];
		for(int k=0;k<M;k++){
			for(int j=0;j<L+1;j++){
				arrayAss[k][j]=-1;
			}
		}
	}

	public void addRulerConfigMRV(int m, int l) {

		M= m;
		L=l;
		adjMatRuler = new int[m][m];
		for(int k=0;k<m;k++){
			for(int k1=0;k1<m;k1++){
				adjMatRuler[k][k1]=-1;
			}
		}
		checkpoint = new int[m];
		for(int k1=0;k1<m;k1++){
			checkpoint[k1]=-2;
		}

	}
	public void golombRulerwithMRV() {
		vertexList[0].wasVisited = true;  // mark it
		//displayVertex(0);                 // display it
		theStack.push(0);                 // push it
		//checkpoint[0]=0;//assign 0 to first node root 3>2>1>0
		//checkpoint[M-1]=L;

		/*adjMatRuler[0][0]=0;
		adjMatRuler[0][M-1]=L;
		adjMatRuler[M-1][0]=L;
		adjMatRuler[M-1][M-1]=0;*/

		RulerwithMRVArray = new int[M][L+1];

		for(int p=0;p<M;p++){
			for(int p2=0;p2<L+1;p2++){
				RulerwithMRVArray[p][p2]=p2;
			}
		}
		//RulerwithMRVArray[0][0]=0;
		assignVariable(0,0,0);//assigns a value to a  variable and updates the RulerwithMRVArray  
		//RulerwithMRVArray[M-1][L]=L;
		assignVariable(M-1,L,L);

		while(continueMRV){
			int min=MAX;
			int k=0;
			int minindex=0;
			for(int p=1;p<M;p++){

				k= findMinMRV(p);
				if(k==-1)
					continue;
				if(min>k){
					min=k;
					minindex=p;
				}

				//System.out.println("min if for "+ k);

			}
			//System.out.println("min "+ minindex);
			assignVariable(minindex);
			for(int i=0;i<M;i++){
				//System.out.println("row "+ i);
				for(int j=0;j<L+1;j++){
					//System.out.print( " " + RulerwithMRVArray[i][j]);
				}
				//System.out.println();
			}
		}

		for(int i=0;i<M;i++){
			for(int j=0;j<M;j++){
				////System.out.print( "test " + adjMatRuler[i][j]);
			}
			////System.out.println();
		}

		if(checkpoint[M-1]!=L ){
			//System.out.print("Golomb Ruler is not possible");
		}
		else{
			for(int i=0;i<M;i++){
				//if(i==M-1)
					//System.out.print( "" + checkpoint[i] + ".");
				//else
					//System.out.print( "" + checkpoint[i] + ",");
			}
		}
		for(int j=0; j<nVerts; j++)          // reset flags
			vertexList[j].wasVisited = false;
		return ;

	}

	private boolean assignVariable(int i, int j, int k) {//row,col,value is assigned to a variable(i,j) in the resultant array

		//System.out.print( " assignVariable " + k + i +j);
		int tmpadjMatRuler[][] = new int[M][M];

		int value=0;
		int val=k;
		//System.out.println("ruler matrix ");
		for(int i3=0;i3<M;i3++){

			for(int j3=0;j3<M;j3++){
				//System.out.print( " " + adjMatRuler[i3][j3]);
				tmpadjMatRuler[i3][j3] = adjMatRuler[i3][j3];
			}
			//System.out.println();
		}

		for(int s=0;s<M;s++){
			if(checkpoint[s]>=0){	
				value=val-checkpoint[s]>0?val-checkpoint[s]:checkpoint[s]-val;
				for(int i2=0;i2<M;i2++){
					for(int j2=0;j2<M;j2++){
						if(adjMatRuler[i2][j2]==value){
							//System.out.println( " mags " +checkpoint[s] + adjMatRuler[i2][j2] + value);
							RulerwithMRVArray[i][j]=-1;

							int assV=0;
							int prev_p=0;

							if(k==L-1){
								for(prev_p=0;prev_p<L+1;prev_p++){
									if(RulerwithMRVArray[i-1][prev_p]!=-1){
										assV=RulerwithMRVArray[i-1][prev_p];
										//System.out.println("assV " + assV);
										break;
									}

								}
								RulerwithMRVArray[i-1][prev_p]=-1;
								int m5=1;
								for(int k4=prev_p+1;k4<L+1;k4++){

									RulerwithMRVArray[i-1][k]=assV+m5;
									m5++;
								}


								RulerwithMRVArray[i][j]=-1;
								checkpoint[i-1]=-2;

								checkpoint[i]=-2;


								for(int p=0;p<M;p++){
									if(checkpoint[p]!=-2){
										//System.out.println( " " + checkpoint[p]);
										for(int m=0;m<M;m++){
											for(int n=0;n<M;n++){
												if(checkpoint[m]!=-2 && checkpoint[n]!=-2){
													int diff_val=checkpoint[m]-checkpoint[n]>0?checkpoint[m]-checkpoint[n]:checkpoint[n]-checkpoint[m];
													adjMatRuler[m][n]=diff_val;

												}


											}
										}
									} 
								}


								for(int i3=0;i3<M;i3++){

									for(int j3=0;j3<M;j3++){

										//System.out.print( " " + adjMatRuler[i3][j3]);
									}
									//System.out.println();
								}

								for(int i3=0;i3<M;i3++){

									for(int j3=0;j3<M;j3++){

										//System.out.print( " " + RulerwithMRVArray[i3][j3]);
									}
									//System.out.println();
								}
								return true;
							}

							return false;
						}

					}
				}
			}
		}


		RulerwithMRVArray[i][j]=k;
		checkpoint[i]=k;
		//System.out.println("test1234 ");

		for(int p=0;p<M;p++){
			if(checkpoint[p]!=-2){
				//System.out.println( " " + checkpoint[p]);
				for(int m=0;m<M;m++){
					for(int n=0;n<M;n++){
						if(checkpoint[m]!=-2 && checkpoint[n]!=-2){
							int diff_val=checkpoint[m]-checkpoint[n]>0?checkpoint[m]-checkpoint[n]:checkpoint[n]-checkpoint[m];
							tmpadjMatRuler[m][n]=diff_val;

						}


					}
				}
			} 
		}

		int chkValidInd[]=new int[L+1];

		for(int u=0;u<L+1;u++){
			chkValidInd[u]=0;
		}
		int chkValid=0;
		for(int i3=0;i3<M;i3++){

			for(int j3=0;j3<M;j3++){

				//System.out.print( " " + tmpadjMatRuler[i3][j3]);
			}
			//System.out.println();
		}


		for(int m=0;m<M;m++){
			for(int n=0;n<M;n++){

				if(tmpadjMatRuler[m][n]>0){

					chkValidInd[tmpadjMatRuler[m][n]]=chkValidInd[tmpadjMatRuler[m][n]] + 1;
					//System.out.println( "  " + chkValidInd[tmpadjMatRuler[m][n]]);
					if(chkValidInd[tmpadjMatRuler[m][n]]>=4){
						//System.out.println( " mags ");

						int assV=0;
						int prev_p=0;

						if(k==L-1){
							for(prev_p=0;prev_p<L+1;prev_p++){
								if(RulerwithMRVArray[i-1][prev_p]!=-1){
									assV=RulerwithMRVArray[i-1][prev_p];
									//System.out.println("assV " + assV);
									break;
								}

							}
							RulerwithMRVArray[i-1][prev_p]=-1;
							int m5=1;
							for(int k4=prev_p+1;k4<L+1;k4++){

								RulerwithMRVArray[i-1][k]=assV+m5;
								m5++;
							}


							RulerwithMRVArray[i][j]=-1;
							checkpoint[i-1]=-2;
							checkpoint[i]=-2;

							for(int p=0;p<M;p++){
								if(checkpoint[p]!=-2){
									//System.out.println( " " + checkpoint[p]);
									for(int m2=0;m2<M;m2++){
										for(int n2=0;n2<M;n2++){
											if(checkpoint[m2]!=-2 && checkpoint[n2]!=-2){
												int diff_val=checkpoint[m2]-checkpoint[n2]>0?checkpoint[m2]-checkpoint[n2]:checkpoint[n2]-checkpoint[m2];
												adjMatRuler[m2][n2]=diff_val;

											}


										}
									}
								} 
							}


							return true;
						}


						RulerwithMRVArray[i][j]=-1;
						checkpoint[i]=-2;
						return false;
					}
				}
			}
		}

		//System.out.println("adjMatRuler chnaging now");
		for(int i3=0;i3<M;i3++){

			for(int j3=0;j3<M;j3++){

				adjMatRuler[i3][j3]=tmpadjMatRuler[i3][j3] ;
				//System.out.print( " " + adjMatRuler[i3][j3]);
			}
			//System.out.println();
		}




		for(int m=0;m<M;m++){
			if(i!=m)
				RulerwithMRVArray[m][j]=-1;
		}
		for(int m=0;m<L+1;m++){
			if(j!=m)
				RulerwithMRVArray[i][m]=-1;
		}
		//System.out.println("ruler");
		for(int i3=0;i3<M;i3++){

			for(int j3=0;j3<L+1;j3++){
				//System.out.print( " " + RulerwithMRVArray[i3][j3]);
			}
			//System.out.println();
		}
		return true;

	}

	/*
	 * row,col , assign the value which is available and for which 
	 * constraint is satisfied without thinking of future.
	 */
	private int assignVariable(int i) {

		int count=0;
		int m=0;
		for(m=0;m<L+1;m++){
			////System.out.println("i " + i + m);
			if(RulerwithMRVArray[i][m]!=-1){
				count++;
				if(assignVariable(i, m, RulerwithMRVArray[i][m]))
					break;
			}

		}
		if(m==L+1){

			continueMRV=false;	
			return 1;
		}


		return 0;

	}
	private int findMinMRV(int k) {

		int count=MAX;
		for(int o=0;o<M;o++)
			////System.out.println("checkpoint " + k + checkpoint[o]);
			for(int j=0;j<L+1;j++){
				if(RulerwithMRVArray[k][j]!=-1 && checkpoint[k]==-2){
					if(count==MAX)
						count=0;
					else
						count++;
				}
			}
		if(count==L || count== MAX)
			return -1;
		return count;
	}
	public void golombRulerFC() {
		
			vertexList[0].wasVisited = true;  // mark it
			//displayVertex(0);                 // display it
			theStack.push(0);                 // push it
			checkpoint[0]=0;//assign 0 to first node root 3>2>1>0
			adjMatRuler[0][0]=0;

			while( !theStack.isEmpty() )      // until stack empty,
			{
				// get an unvisited vertex adjacent to stack top
				int v = getAdjUnvisitedVertex( theStack.peek() );
				////System.out.println("popped out beg" + (theStack.peek()));
				if(v == -1){                    // if no such vertex,{
					theStack.pop();

				}
				else                           // if it exists,
				{
					//int conflict= checkValididty(v,nextInt()+1);
					int i=1;
					int conflict=1;
					//while(conflict!=0 && (nextInt(v)+i-1) < L){
					while(conflict!=0 && (nextInt(v)+i-1) <= L){
						conflict= checkValididtyFC(v,nextInt(v)+i);//CHECK VALIIDTY IF CONSTRAINTS ARE SATISFIED OR NOT

						//System.out.println("while " +  (nextInt(v)+i) + "," + v);
						for(int i2=0;i2<M;i2++){
							for(int j=0;j<M;j++){
								////System.out.print( "test " + adjMatRuler[i2][j]);
							}
							////System.out.println();
						}
						if(conflict==2)
							break;
						i++;
					}
					if(((nextInt(v)+i-1) > L) || conflict==2){//IF > CONSTRAINTS ARE NOT SATISFIED , SO BACKTRACK AND INCREMENT THE VALUE OF THE LAST VALID VARIABLLE
						////System.out.println("sundi t2 : " + (nextInt(v)+i-1));
						////System.out.println("sundi t2 v-1: " + (v-1));

						vertexList[v-1].wasVisited = false;
						vertexList[v].wasVisited = false;
						for(int k=0;k<M;k++)
							adjMatRuler[v-1][k]=0;
						for(int k=0;k<M;k++)
							adjMatRuler[k][v-1]=0;
						int pop = theStack.pop();
						////System.out.println("popped out" + pop + (theStack.peek()));

						continue;

					}
					/* 1. IF THE VALUE ID VALID, ASSIGN THAT VALID VALUE.
					 * 2. STORE THE DIFFERNCE IN THE DIFFERENCE ARRAY WHICH 
					 *    STORES DIFFERENCE BETWEEN ALL THE VALID ASSIGNED PAIR VARIABLES, 
					 *    THIS HELPS IS CHECKING THE CONSTRAINTS, THE DIFFERENCE ARRAY IS adjMatRuler
					 * 3. THE VALID VALUES ARE ASSIGNED TO ARRAY checkpoint[].    
					 */

					checkpoint[v]=nextInt(v)+i-1;
					for(int p=0;p<M;p++){
						if(checkpoint[p]!=-1){
							for(int m=0;m<M;m++){
								for(int n=0;n<M;n++){
									int val=checkpoint[m]-checkpoint[n]>0?checkpoint[m]-checkpoint[n]:checkpoint[n]-checkpoint[m];
									adjMatRuler[m][n]=val;


								}
							}
						}
					}
					vertexList[v].wasVisited = true;  // mark it

					//displayVertex(v);                 // display it

					theStack.push(v);                 // push it
				}

			}  // end while
			// stack is empty, so we're done
			for(int i=0;i<M;i++){
				for(int j=0;j<M;j++){
					////System.out.print( "test " + adjMatRuler[i][j]);
				}
				////System.out.println();
			}
			////System.out.println( "Values assigned are ");
			if(checkpoint[M-1]!=L ){
				System.out.print("Golomb Ruler is not possible");
			}
			else{
				for(int i=0;i<M;i++){
					if(i==M-1)
						System.out.print( "" + checkpoint[i] + ".");
					else
						System.out.print( "" + checkpoint[i] + ",");
				}
			}
			for(int j=0; j<nVerts; j++)          // reset flags
				vertexList[j].wasVisited = false;
		
		

	}// END OF gOLOMBrULER
}  // end class Graph