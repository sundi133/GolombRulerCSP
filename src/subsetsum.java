
public class subsetsum {

	/**
	 * @param args
	 */
	static boolean S[][]= new boolean[10][10];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int A[]= {1,2,3,4,5,6,7,8,9,11};
		//A =
		for(int i = 0; i < 10; i++){
			S[i][0] = true;
			int T=11;
			for(int j = 1; j < 10; j++)
				S[0][j] = false;
			for (int i1 = 1; i1 < 10; i1++){
				for (int j = 1; j < 10; j ++){
					int k= A[i1];
					S[i1][j] = S[i1-1][j]==true || ( A[i1] <= j && ( S[i1-1][j-k]==true ) ) ;
					if(S[i1][j] == true){
						System.out.println("System match found" + i1 + j);
						break;
					}
					else
						System.out.println("System mismatch found" + S[i1][j]);
				}
			}

		}

		

		return ;
	}
}




