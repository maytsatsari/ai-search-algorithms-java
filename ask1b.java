import java.util.*;

class Cube{
    public int x,y;
    Cube()
    {
        x=-1;
        y=-1;
    }
    
    Cube(int x1, int y1)
    {
        x=x1;
        y=y1;
    }
    
    @Override
    public String toString()
    {
        return "("+(x+1)+","+(y+1)+")";
    }
    
    boolean isEqual(Cube A)
    {
        return (x==A.x && y==A.y);
    }
    
    boolean isOn(Cube A)
    {
    	return (x==A.x && y==A.y+1);
    	
    }
    
}
// i kathe katastasi

class State
{
	Cube A[];
	int N;
	 int K;
	   
	State(int k){
		K=k;
		N=3*K;
		A=new Cube[N];
	    for (int i=0;i<N;i++) A[i]=new Cube();
	}
	
	void InitState()
    {
        int y;
        int x;
        Cube nc;
        for (int i=0;i<N;i++)
        {
            while(true)
            {
                y=random(0,3);
                
                if(y==0)
                {
                    x=random(0,4*K);
                }
                else
                {
                    x=random(0,K);
                }
                nc=new Cube(x,y);
                
                if(!exists(nc) && isValid(nc)) break; 
            }
            A[i]=nc;
            
        }
       
    }
	
	void createFinal()
	{
		int j=0;
		for(int i=0;i<K;i++)
		{
			
			A[j++]=new Cube(i,0);
			A[j++]=new Cube(i,1);
			A[j++]=new Cube(i,2);
			
		}
		
		
	}
	
	 boolean isFinal()
	 {
		 int n1=0,n2=0,n3=0;
		 for (int i=0;i<N;i++)
		 {
			 if(A[i].y==0) n1++;
			 if(A[i].y==1) n2++;
			 if(A[i].y==2) n3++;
			 
		 }
		 return (n1==K && n2==K && n3==K);
	 }
	 
	
	//mia tixaia timi
	
	int random(int a, int b)
    {
        return a+(int) Math.floor(Math.random()*(b-a));
    }
	
	
	// taxinomisi twn komvon
	void ShowSort()
    {
        int i,j;
        Cube tmp;
        Cube B[]=new Cube[N];
        for (i=0;i<N;i++) B[i]=A[i];
        for (j=1;j<N;j++)
        for (i=N-1;i>=j;i--)
        {
            if(B[i].x<B[i-1].x)
            {
                tmp=B[i];
                B[i]=B[i-1];
                B[i-1]=tmp;
            }
            
            
        }
        
        for (i=0;i<N;i++)
        {
            System.out.print(B[i]);
        }
        System.out.println();
    }
	
	
	 void ShowState()
	    {
	        
	        for (int i=0;i<N;i++)
	        {
	            System.out.print(A[i]);
	        }
	        System.out.println();
	    }
	 
	 boolean isFree(Cube c)
	    {
	    	for (int j=0;j<N;j++)
	        {
	          
	        	   
	        	   if(A[j].y==c.y+1 && A[j].x==c.x) return false;
	         
	        }
	    	
	    	return true;
	    }
	 
	 
	 boolean isEqual(State S)
	 {
		 if(S.N!=N) return false;
		 for (int j=0;j<N;j++)
	        {
	           if(! S.A[j].isEqual(A[j])) {return false; }
	        }
		 return true;
	 }
	 
	 
	 boolean exists(Cube c)
	    {

	        for (int j=0;j<N;j++)
	        {
	           if(c.isEqual(A[j])) {return true; }
	        }
	        return false;
	    }
	 
	 boolean ValidState()
	    {
	        for (int i=0;i<N;i++)
	        {
	            if(!isValid(A[i])) return false; 
	        }
	        return true;
	        
	    }
	 
	 boolean isValid(Cube c)
	    {
		 int count=0;
		
		 for (int j=0;j<N;j++)
		 {
		           	
			if(c.x==A[j].x && c.y>0) {
				if(Math.abs(A[j].y-c.y)>1) return false;
			}
			if(c.isEqual(A[j])) count++;
	 
		 }
		 if(count>1) return false;
		 if(c.y==1) {
			 int cnt0=0;
			 for (int j=0;j<N;j++)
			 {
			           	
				if(c.x==A[j].x && A[j].y==0) {
					cnt0++;
				}
			 }
			 if(cnt0==0) {
				 return false;
			 }
		 }
		 if(c.y==2) {
			 int cnt0=0;
			 for (int j=0;j<N;j++)
			 {
			           	
				if(c.x==A[j].x && A[j].y==1) {
					cnt0++;
				}
			 }
			 if(cnt0==0) {
				 return false;
			 }
		 }
		 
		 return true;
	    }
	 
	 Cube move(int i, int type)
	    {
	    	
	    	Cube a=new Cube();
	    	Cube b=new Cube(A[i].x,A[i].y);
	    	if(isFree(b)) {
	    		if(type==1)
	    		{
	    			
		    		if(A[i].y>0) {
		    			for (int x=0;x<4*K;x++)
		    			{
			    			a.x=x;
			    			a.y=0;
			    			if(isValid(a) && !exists(a))
			    			{
			    				b.x=a.x;
			    				b.y=a.y;
			    				
			    			 break;	
			    			}
		    			}
		    			
		    			
		    		}
	    		}
	    		
	    		
	    		if(type==2)
	    		{
	    			
	    			for (int j=0;j<N;j++)
	    			{
	    				
	    				if(A[j].y==0 && isFree(A[j]) && !A[j].isEqual(b))
	    				{
	    					b.y=A[j].y+1;
	    					b.x=A[j].x;
	    					
	    					break;
	    				}
	    			}
		    		
	    		}
	    		
	    		if(type==3)
	    		{
	    			
	    			for (int j=0;j<N;j++)
	    			{
	    				
	    				if(A[j].y==1 && isFree(A[j]) && !A[j].isEqual(b))
	    				{
	    					b.y=A[j].y+1;
	    					b.x=A[j].x;
	    					
	    					break;
	    				}
	    			}
		    		
	    		}
	    	}
	    	
	    	
	    	
	    	
	    	return b;
	    }
	 
	 State StateToState()
	 {
		 State tmp=new State(K);
		 
		 for (int i=0;i<N;i++)
		 {
			 tmp.A[i]=A[i];
		 }
		 
		 return tmp;
	 }
	 
	 
	
	 ArrayList<State> getChilds()
	 {
		 ArrayList<State> C=new ArrayList<State>();
		 
		 for (int i=0;i<N;i++)
		 {
			 
			 Cube b1=move(i,1);
			 if(!b1.isEqual(A[i]))
			 {
				 State S=this.StateToState();
				 S.A[i]=b1;
				 C.add(S);
			 }
			 Cube b2=move(i,2);
			 if(!b2.isEqual(A[i]))
			 {
				 State S=this.StateToState();
				 S.A[i]=b2;
				 C.add(S);
			 }
			 Cube b3=move(i,3);
			 if(!b3.isEqual(A[i]))
			 {
				 State S=this.StateToState();
				 S.A[i]=b3;
				 C.add(S);
			 }
			 
		 }
		 
		 
		 return C;
		 
	 }
	 
	 
	
	 
	 double cost(State S)
	 {
		 
		 
		 double cost=0;
		 double g=0;
		 for (int i=0;i<N;i++)
		 {
			 if(S.A[i].y>A[i].y) g+=S.A[i].y-A[i].y;
			 if(S.A[i].y<A[i].y) g+=0.5*(A[i].y-S.A[i].y);
			 if(S.A[i].y==A[i].y && S.A[i].x!=A[i].x) g+=0.75;
			 
		 }
		 
		 State FF=new State(K);
		 FF.createFinal();
		 double f=0;
		 for (int i=0;i<N;i++)
		 {
			 if(FF.A[i].y>A[i].y) f+=FF.A[i].y-A[i].y;
			 if(FF.A[i].y<A[i].y) f+=0.5*(A[i].y-FF.A[i].y);
			 if(FF.A[i].y==A[i].y && FF.A[i].x!=A[i].x) f+=0.75;
			 
		 }
		 return f+g;
	 }
}


class Path
{
	State S;
	double pathcost;
	Path Father;
	Path(State s, Path F)
	{
		S=s;
		pathcost=0;
		Father=F;
	}
	
    boolean isEqual(State s)
    {
    	
    	if(s.isEqual(S)) return true;
    	
    	return false;
    }
	
    void addCost(double c)
    {
    	pathcost+=c;
    }
    
    ArrayList<Path> getChilds()
    {
    	ArrayList<Path> p=new ArrayList<Path>();
    	ArrayList<State> C=new ArrayList<State>();
    	C=S.getChilds();
    	
    	for (State ss: C)
    	{
    		double cost=S.cost(ss);
    		
    		Path np=new Path(ss, this);
    		np.pathcost=pathcost+cost;
    		p.add(np);
    	}
    	return p;
    } 
    
    
    boolean isFinal()
    {
    	
    		if(S.isFinal()) return true;
    	
    	return false;
    }
    
    
    boolean isEqual(Path p)
    {
    	
    		if(S.isEqual(p.S)) return true;
    	
    	return false;
    }
    
    void Print()
    {
    	System.out.print("Cost: "+pathcost+" State:");
    	S.ShowState();
    }
}


// i kiria class
public class ask1b
{
    State S;
    int K;
   
    public static void main(String args[])
    {
       
       ask1b t=new ask1b();
      
    }
    
   // edo ekteleitai o algorithmos
    
    public ask1b()
    {
    	ArrayList<Path> E=new ArrayList<Path>();  //metopo anazitisis
        ArrayList<Path> F=new ArrayList<Path>();  //kleisto synolo
        
        
       System.out.println("Give K:");
       Scanner in = new Scanner(System.in);
       
       
       
       K=in.nextInt();
       S=new State(K);
       
       
       // dimimourgoume mia arxiki katastasi
       S.InitState();
       System.out.println("Initial State");
       S.ShowState();
       
       // vazoume tin katastasi sto synolo anazitisis
       Path P=new Path(S,null);
       E.add(P);
       
       while(true)
       {
    	   
    	   // an to synolo anazitisis einai keno tote 
    	   if(E.isEmpty()) break;
    	   
    	   // vres sto synolo anazitisis tin katastasi me to mikrotero kostos
    	   Path k=findBest(E);
    	   //k.Print();
    	   // an einai teliki tote
    	   if(k.isFinal()) {
    		   F.add(k);
    		   System.out.println("Final State");
    		   k.Print();
    		   System.out.println("Path");
    		   ShowPath(F,k);
    		   break;
    	   }
    	   
    	   // vazoume tin katastasi sto klisto synolo
    	   F.add(k);
    	   
    	   // vgazoume tin katastasi apo to sinolo anazitisis
    	   int i1=0;
    	   for (int i=0;i<E.size();i++)
    	   {
    		   if(E.get(i).isEqual(k))
    		   {
    			 i1=i;  
    		   }
    	   }
    	   E.remove(i1);
    	   
    	   
    	   // pairnoume ta nea paidia apo tin katastasi pou vriskomaste
    	   ArrayList<Path> ch=k.getChilds();
    	   
    	   // gia kathe paidi elegxoume an yparxei sto synolo anazitisis i to kleisto synolo
    	   for (Path x: ch)
    	   {
    		   
    		   // elegxos sto kleisto synolo
    		   int i3=-1;
        	   for (int i=0;i<F.size();i++)
        	   {
        		   if(F.get(i).isEqual(x))
        		   {
        			 i3=i;  
        		   }
        	   }
        	   if(i3!=-1)
        	   {
        		   if(x.pathcost<F.get(i3).pathcost) {
        			   F.remove(i3);
        		   }
        	   }
        	   
        	   
    		   
    		   if(i3==-1)
    		   {	   
	    		   // an den yparxei sto kleisto synolo elegxos sto synolo anazitisis
	    		   int i2=-1;
	        	   for (int i=0;i<E.size();i++)
	        	   {
	        		   if(E.get(i).isEqual(x))
	        		   {
	        			 i2=i;  
	        		   }
	        	   }
	        	   if(i2!=-1)
	        	   {
	        		   if(x.pathcost<E.get(i2).pathcost) {
	        			   E.remove(i2);
	        			   E.add(x);
	        		   }
	        	   }
	        	   else
	        	   {
	        		   E.add(x);
	        	   }
    		   }
        	   
        	   
        	  
    		   
    	   }
    	   
       }
      
       
    }
    
    Path findBest(ArrayList<Path> d)
    {
    	int mink=-1;
    	double min=1000000;
    	
    	for(int i=0;i<d.size();i++)
    	{
    		double cost=d.get(i).pathcost;
    		if(cost<min)
    		{
    			mink=i;
    			min=cost;
    				
    		}
    	}
    	
    	return d.get(mink);
    }
    
    void ShowPath(ArrayList<Path> P, Path F)
    {
    	Path init=null;
    	for (Path p: P)
    	{
    		if(p.isEqual(F))
    		{
    			init=p;
    			break;
    		}
    	}
    	
    	ArrayList<Path> pth=new ArrayList<Path>();
    	Path st=init;
    	while (st!=null)
    	{
    		pth.add(st);
    		st=st.Father;
    	}
    	
    	for (int i=pth.size()-1;i>=0;i--)
    	{
    		pth.get(i).Print();
    	}
    }
  
}
