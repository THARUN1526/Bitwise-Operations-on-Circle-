
import java.util.*;
import java.io.*;

// cf : add public, cc : remove public
class Fast_IO_1{
	static FastReader in; static FastWriter out;
    public static void main(String[] args) throws IOException {
    	in = new FastReader(); out = new FastWriter();
    	preCal();
//        solve();
    	int tc = in.nextInt(); while(tc-- > 0){ solve();} 
    	out.close();}
//		 ####################
    
    static int[][] pre;
    
    
    
    public static void preCal() {
    	
    }
    public static void solve() throws IOException  {
    	int n = in.nextInt();
    	int[] arr = new int[n];
    	pre = new int[31][n];
    	List<Integer> listA = new ArrayList<>();
    	List<Integer> listO = new ArrayList<>();
    	
    	for(int i=0;i<n;i++) {
    		int x = in.nextInt();
    		arr[i] = x;
    		
    		for(int j=0;j<=30;j++) {
    			if((x & (1 << j)) != 0) pre[j][i] = 1;
    			if(i-1 >= 0) pre[j][i] += pre[j][i-1];
    		}
    	}
    	
    	listA.add(0);
    	listO.add(0);
    	
    	int and = arr[0], or = arr[0];
    	
    	for(int i=1;i<n;i++) {
    		int a = and, o = or;
    		int nextA = a & arr[i];
    		int nextO = o | arr[i];
    		
    		if(nextA != and) listA.add(i);
    		if(nextO != or) listO.add(i);
    		
    		and = nextA;
    		or = nextO;
    	}
    	
    	int res = 0;
    	
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<listA.size() && listA.get(j) <= i-1;j++) {
    			 int ind = listA.get(j);
    			 and = rangeAnd(0, ind);
    			 if(i+1 <= n-1) and = and & rangeAnd(i+1, n-1);
    			 or = rangeOr(ind+1, i);
    			 
    			 int diff = Math.abs(or - and);
    			 res = Math.max(res, diff);
    		}
    	}
    	
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<listO.size() && listO.get(j) <= i-1;j++) {
    			int ind = listO.get(j);
    			or = rangeOr(0, ind) | rangeOr(i+1, n-1);
    			and = rangeAnd(ind+1, i);
    			
    			int diff = Math.abs(or - and);
    			res = Math.max(res, diff);
    		}
    	}
    	
    	out.println(res);
    	
    	
    	
    }
    
    static int rangeOr(int lo, int hi) {
    	if(lo > hi) return 0;
    	
    	int res = 0;
    	
    	for(int j=0;j<=30;j++) {
    		int one = pre[j][hi] - (lo - 1 >= 0? pre[j][lo - 1] : 0);
    		if(one > 0) res = res | (1 << j);
    	}
    	
    	return res;
    }
    
    static int rangeAnd(int lo, int hi) {
    	if(lo > hi) return 0;
    	
    	int res = 0;
    	
    	for(int j=0;j<=30;j++) {
    		int one = pre[j][hi] - (lo - 1 >= 0? pre[j][lo - 1] : 0);
    		if(one == (hi - lo + 1)) res = res | (1 << j);
    	}
    	
    	return res;
    }
  
  
    
//		####################  
    
    static final Random rand =new Random();
    
    static void rsort(int[] a) {
		int n=a.length;//shuffle, then sort 
		for (int i=0; i<n; i++) {
			int oi=rand.nextInt(n), temp=a[oi];
			a[oi]=a[i]; a[i]=temp;
		}
		Arrays.sort(a);
	} 
    
    static void rsort(long[] a) {
		int n=a.length;//shuffle, then sort 
		for (int i=0; i<n; i++) {
			int oi=rand.nextInt(n);
			long temp=a[oi];
			a[oi]=a[i]; a[i]=temp;
		}
		Arrays.sort(a);
	} 
    
    static boolean[] getPrime(int n) {
    	boolean[] _prime = new boolean[n+1];
    	Arrays.fill(_prime, true);
    	_prime[0] = false; _prime[1] = false;
    	for(int i=2;i*i<=n;i++) 
    		if(_prime[i]) for(int j=i*i;j<=n;j+=i) _prime[j] = false;
    	
    	return _prime;
    }
    
    static int[] getSpf(int n) {
    	int[] _sp = new int[n+1];
    	for(int i=0;i<=n;i++) _sp[i] = i;
    	for(int i=2;i*i <= n;i++) 
    		if(_sp[i] == i) for(int j=i*i;j<=n;j += i) if(_sp[j] == j) _sp[j] = i;
    	
    	return _sp;
    }
    
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        public FastReader(){
            br=new BufferedReader(new InputStreamReader(System.in));
        }
        String next(){
            while(st==null || !st.hasMoreTokens()){
                try {
                    st=new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
        long nextLong(){
            return Long.parseLong(next());
        }
        double nextDouble(){
            return Double.parseDouble(next());
        }
        String nextLine(){
            String str="";
            try {
                str=br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    static class FastWriter {
		private final BufferedWriter bw;

		public FastWriter() {
			this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
		}

		public void print(Object object) throws IOException {
			bw.append("" + object);
		}

		public void println(Object object) throws IOException {
			print(object);
			bw.append("\n");
		}
		
		public void println() throws IOException {
			bw.append("\n");
		}

		public void close() throws IOException {
			bw.close();
		}
	}
    
    
}
