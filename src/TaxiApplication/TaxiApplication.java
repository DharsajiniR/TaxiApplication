package TaxiApplication;
import java.util.*;  

public class TaxiApplication{
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the No. of inputs\n");
		int inputs = scan.nextInt();
		int[] custId = new int[inputs];
		int[] pickup = new int[inputs];
		int[] drop = new int[inputs];
		int[] time = new int[inputs];
		System.out.println("Enter the No. of taxi\n");
		int noOfTaxi= scan.nextInt();
		int[] taxi = new int[noOfTaxi];
		int[] price = new int[noOfTaxi];
		int[] distance= new int[noOfTaxi];
		int[] freeTime= new int[noOfTaxi];
		
		for(int i=0;i<noOfTaxi;i++) {
		  taxi[i]=1;
		}
		for(int i=0;i<inputs;i++) {
			System.out.println("Enter the details for input no: "+ (i+1) +"\n");
			System.out.println("Enter the customer Id");
			custId[i]=scan.nextInt();
			System.out.println("Enter the Pickup Point");
		    pickup[i]=scan.nextInt();
		    System.out.println("Enter the Drop Point");
			drop[i]=scan.nextInt();
			System.out.println("Enter the Pickup Time");
		    time[i]=scan.nextInt();
		}
		for(int i=0;i<inputs;i++)
		{
				int nearTaxiCount=0;
				int position=0;
				int nearestPoint=nearestPointTaxi(taxi,pickup[i],freeTime,time[i]);
				String positionArray = ",";
				if(i!=0) {
					for(int j=0;j<noOfTaxi;j++){
					if(taxi[j]==nearestPoint) {
							positionArray=positionArray.concat(String.valueOf(j).concat(","));
							nearTaxiCount++;
						}
				}
				if(nearTaxiCount==1) {
					position=Integer.valueOf(positionArray.split(",",-1)[1]);
				}
				else {
					position=getLowestIncomeTaxi(price,positionArray);
				}
				}
				distance[position]=Math.abs(drop[i]-pickup[i]);
				freeTime[position]=distance[position]+time[i];
				distance[position]=distance[position]*15;
				taxi[position]=drop[i];
				
				
				
				
				price[position]=price[position]+(((distance[position]-5)*10)+100);
				System.out.println("Taxi "+(position+1)+" is alloted for cust "+(i+1));
				System.out.println("Price for Taxi "+(position+1)+" => "+price[position]);	
		}
	}
	public static int nearestPointTaxi (int[] array,int num,int[] freeTime, int time) {
		  int i = 0;
		  int minDiff = 1000;
		  int ans=0;
		  for (i=0;i<array.length;i++) {
		    int m = Math.abs(num - array[i]);
		    if (m < minDiff && freeTime[i]<=time) {
		      minDiff = m;
		      ans = array[i];
		    }
		  }
		  return ans;
	}
	public static int getLowestIncomeTaxi(int[] price,String positionArray) {
		int position=0;
		 int minValue = price[0]; 
		 for(int i=1;i<price.length;i++){ 
		      if(price[i] < minValue && isPositionAvailable(i,positionArray)){ 
		        minValue = price[i]; 
		        position=i;
		      } 
		    } 
		return position;
	}
	public static boolean isPositionAvailable(int index,String positionArray) {
		char[] arr=positionArray.toCharArray();
		for(int i=0;i<arr.length;i++) {
			if(index == arr[i]-'0') {
				return true;
			}
		}
		return false;
		
	}
	

	
		  
}
