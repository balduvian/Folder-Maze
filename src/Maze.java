import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;

public class Maze {
	
	boolean pc = true;
	String mname = "maze";
	int limit = 10;
	String root = System.getProperty("user.home") + "\\Desktop\\"+mname;
	ExecutorService exe = Executors.newCachedThreadPool();
	String[] names = {"mtg","magic","kyle","koala","sec","france","nova","weed","school","atraxa","cat","planeswalker","forest","island","mounain","swamp","plains","henry","gay","vincent","bot","madi","kira","high","way too deep","fuck","god","emmett","m","soren","sam","ravnica","theros","majority","trump","bernie","hillary","republican","libertarian","smoke","au5","dp","kiora","wheel","succ","dank","maractus","henrybot","kirabot","madibot","vincentbot","scrib","toby","virus","frank","ramen","we are number one","bee","just a prank","xc","shit","fun","not porn","mom's spaghetti","vomit","bislin","mazeception","lose yourself","crosman","spurlock","terra stomper","robbie","black hole","100","boi","dat boi","white","blue","green","red","black","creature","commander","cyclops","hard","owl","karl","paul","aman","president","vip","important","folder","et",genhex(5),genhex(1),genhex(3),"zombie","gorilla","america","do not enter","skeleton","foegod","turbofog","7","grand dad","911","8=D","update","system32","do not delete","sjw","minecraft"};
	
	public void convert(){
		String get = Sump(System.getProperty("user.home") + "\\Desktop\\help.txt");
		String out = "";
		String valid = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0;i<get.length();i++){
			for(int g=0;g<valid.length();g++){
				if(get.charAt(i) == valid.charAt(g)){
					out += valid.charAt(g);
					break;
				}else if(g==valid.length()-1){
					out += ' ';
				}
			}
		}
		System.out.println(out);
		System.out.println(get);
		System.out.println("done");
	}
	
	public String Sump(String f){
		String s = "";
		byte[] tc = new byte[1];
		System.out.println("hi");
		try(InputStream input = getClass().getResourceAsStream(f)) {
			while(true){
				System.out.println("lol");
				tc[0] = (byte)input.read();
				if(tc[0]==-1){
					System.out.println("emd re");
					break;
				}else{
					s += new String(tc, StandardCharsets.UTF_8);
					System.out.println("add");
				}
			}
			System.out.println("hit");
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return s;
	}
	
	public String genhex(int len){
		String po = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String s = "";
		for(int i=0;i<len;i++){
			s+=po.charAt((int)(Math.random()*po.length()));
		}
		return s;
	}
	
	public class Shoot implements Runnable{
		String base;
		int generation;
		boolean chosen;
		public Shoot(String b, int g, boolean c){
			base = b;
			generation = g;
			chosen = c;
		}
		public void run() {
			if(generation<limit){
				int times = (int)(Math.random()*3)+1;
				for(int u=0;u<times;u++){
					while(true){
						File ad = new File("");
						if(pc){
							ad = new File(base+"//"+genhex((int)(Math.random()*3+2)));
						}else{
							ad = new File(base+"//"+names[(int)(Math.random()*names.length)]);
						}
						if(!ad.exists()){
							ad.mkdir();
							if(u==0){
								exe.execute(new Shoot(ad.getPath(),generation+1,chosen));
							}else{
								exe.execute(new Shoot(ad.getPath(),generation+1,false));
							}
							break;
						}
					}
				}
			}else if(generation==limit && chosen){
				try{
				    PrintWriter writer = new PrintWriter(base+"//end", "UTF-8");
				    if(pc){
				    	writer.println("You win!");
				    }else{
				    	writer.println("PORN");
				    }
				    writer.close();
				}catch(IOException ex){}
			}
			System.out.println("done");
		}
	}
	
	public Maze(){
		//convert();
		File set = new File(root);
		if(!set.exists()){
			set.mkdir();
		}
		exe.execute(new Shoot(set.getPath(),0,true));//here we go!
	}
	
	public static void main(String[] args) {
		new Maze();
	}

}
