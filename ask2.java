
import java.util.*;


//mia katastasi tou paixnidou
class State {
	
	// ta vasika stoixeia mias katastasi
	int O[];
	int B[];
	int K;
	int M;
	int cost;
	int minmax; // 1 max , 0 min
	State Father;  // pateras tis katastasis
	ArrayList<State> C; //paidia tis katastasis
	
	//kataskevastis
	State(int o[],int b[],int k,int c, State F, int m, int M)
	{
		O=new int[k];
		B=new int[k];
		K=k;
		minmax=m;
		
		for (int i=0;i<K;i++)
		{
			O[i]=o[i];
			B[i]=b[i];
			
		}
		C=new ArrayList<State>();
		cost=c;
		
		
		
	}
	
	// pairnoume ta paidia tis katastasis
	void generateChds()
	{
		if(! isFinal() )
		{
			for (int i=0;i<K;i++)
			{
				for (int j=1;j<=B[i];j++)
				{
					int o[]=new int[K];
					int s=0;
					for(int l=0;l<K;l++) o[l]=O[l];
					
					if(j<=O[i]) {
						o[i]=O[i]-j;
						if(minmax==1) {
							C.add(new State(o,B,K,0,this,0,M));
						}
						else
						{
							C.add(new State(o,B,K,0,this,1,M));
						}
					}
				}
			}
		}
		else
		{
			if(minmax==1) cost=1;
			else cost=0;
		}
	}
	
	
	// elegxos an einai final i katastasi
	boolean isFinal()
	{
		
		for (int i=0;i<K;i++)
		{
			if(O[i]!=0) return false;
		}
		return true;
	}
	
	
	// an exei paidia  (einai filo an den exei)
	boolean hasChds()
	{
		
		if(C.size()==0) return false;
		return true;
	}
	
	
    void getCost()
    {
    	
    	if(C.size()!=0) {
    		int max=-1000;
    		int min=1000;
    		for (int i=0;i<C.size();i++)
    		{
    			if(C.get(i).cost>max) max=C.get(i).cost;
    			if(C.get(i).cost<min) min=C.get(i).cost;
    			
    		}
    		if(minmax==1) cost=max;
    		else cost=min;
    	}
    	else
    	{
    		if(minmax==1) {
    			cost=1;
    		}
    		else
    		{
    			cost=-1;
    		}
    	}
    }
	
	// prokeipti to dendro me riza tin sygkerimeni katastasi
	// kai ta kosti se kathe filo (1 niki , -1 mi niki)
	void setTree()
	{
		if(!isFinal())
		{
			this.generateChds();
			for (int i=0;i<C.size();i++)
			{
				C.get(i).setTree();
			}
		}
		else
		{
			if(minmax==1) {
				cost=1;
				
			}
			else
			{
				cost=-1;
			}
		}
	}
	
	
	// ypologismos tou kostous
	void setCosts()
	{
		
		for (State c: C) {
			c.setCosts();
		}
		getCost();
	}
	
	
	void Play()
	{
		

    	if(C.size()!=0) {
    		int max=-1000;
    		int min=1000;
    		int maxi=-1;
    		int mini=-1;
    		System.out.println(this);
    		for (int i=0;i<C.size();i++)
    		{
    			if(C.get(i).cost>max) {max=C.get(i).cost; maxi=i;}
    			if(C.get(i).cost<min) {min=C.get(i).cost; mini=i;}
    			
    		}
    		if(minmax==1) { C.get(maxi).Play();}
    		else { C.get(mini).Play();}
    	}
	}
	
	public String toString()
	{
			String s="";
		if (minmax==1) s+="Max Play: ";
		else s+="Min Play: ";
		
		for (int i=0;i<K;i++)
		{
			s+=O[i]+"  ";
		}
		return s;
	}
}

public class ask2_1 {
	public static void main(String arg[])
	{
		// dimoume ta dedomena
		Scanner in=new Scanner(System.in);
		System.out.println("Give M:");
		int M=in.nextInt();
		System.out.println("Give K:");
		int K=in.nextInt();
		
		int A[]=new int[K];
		int O[]=new int[K];
		int B[]=new int[K];
		for (int i=0;i<K;i++)
		{
			while(true)
			{
			System.out.println("Give A["+i+"]:");
			A[i]=in.nextInt();
			O[i]=A[i];
			if(A[i]<2) System.out.println("Must be more than 1 card");
			else break;
			}
		}
		
		
		System.out.println("Max number of cards to remove from each set (B[i]):");
		for (int i=0;i<K;i++)
		{
			while(true)
			{
			System.out.println("Give B["+i+"]:");
			B[i]=in.nextInt();
			
			if(B[i]>A[i]) System.out.println("Must be more than "+A[i]+" card");
			else break;
			}
		}
		
		int sum=0;
		for (int i=0;i<K;i++)
		{
			sum+=A[i];
		}
		if(sum!=M) {
			System.out.println("Error M -- Try to run again the program");
			System.exit(1);
		}
		
		
		State R=new State(O,B,K,0,null,1,M);
		R.setTree();
		
		R.setCosts();
		
		
		R.Play();
		
		in.close();
		
	}
}
