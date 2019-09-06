package amazon;

/*
 * 
 * https://leetcode.com/discuss/interview-question/318918/Amazon-or-Online-Assessment-2019-or-Optimal-Aircraft-Utilization
 * 
 * https://www.youtube.com/watch?v=pyghN9TpO2I&feature=youtu.be
 * 
 * 
 * Your task is to write an algorithm to optimize the sets of forward/return shipping route pairs that allow the aircraft to be optimally utilized, given a list a of forward routes and a list of return shipping routes.

INPUT
The input to the function/method consisits of three arguments:
maxTravelDist, an integer representing the maximum operating travel distance of the given aircraft;
forwardRouteList, a list of pairs of integers where the first integer represents the unique identifier of a forward shipping
route and the second integer represents the amount of travel distance required bu this shipping route;
returnRouteList, a list of pairs of integers where the first integer represents the unique identifer of a return shipping route
and the second integer represents the amount of travel distance required by this shipping route.

OUTPUT
Return a list of pairs of integers representing the pairs of IDs of forward and return shipping routes that optimally utilize the given aircraft. If no route is possible, return a list with empty pair.

Example 1:
Input:
maxTravelDist = 7000
forwardRouteList = [[1,2000],[2,4000],[3,6000]]
returnRouteList = [[1,2000]]

Output:
[[2,1]]
 * 
 * 
 * */

import java.util.*;
public class AppOptimization {


	List<List<Integer>> optimalUtilization(int deviceCapacity,
			List<List<Integer>> foregroundAppList,
			List<List<Integer>> backgroundAppList) {

		int sum = 0;
		int max = -1;

		Object[] fgArr = foregroundAppList.toArray();
		Object[] bgArr = backgroundAppList.toArray();

		HashMap<Integer,Integer> fgHm = new HashMap();
		HashMap<Integer,Integer> bgHm = new HashMap();

		for(int i=0;i<fgArr.length;i++) {

			fgHm.put(((List<Integer>)fgArr[i]).get(1),((List<Integer>)fgArr[i]).get(0));

		}
		for(int i=0;i<bgArr.length;i++) {

			bgHm.put(((List<Integer>)bgArr[i]).get(1),((List<Integer>)bgArr[i]).get(0));

		}

		Integer[] fgArr2 = fgHm.keySet().toArray(new Integer[0]);
		Integer[] bgArr2 = bgHm.keySet().toArray(new Integer[0]);
		ArrayList<List<Integer>> pairs = new ArrayList<List<Integer>>();

		for(int i=0;i<fgArr2.length;i++) {
			for(int j=0;j<bgArr2.length;j++) {
				sum = fgArr2[i]+bgArr2[j];

				if(sum<=deviceCapacity){
					if(max<sum)
						max = sum;
				}
			}
		}

		System.out.println("max >> "+max);
		System.out.println("max >> "+fgHm.toString());
		System.out.println("max >> "+bgHm.toString());


		for(int i=0;i<fgArr2.length;i++) {
			for(int j=0;j<bgArr2.length;j++) {
				sum = fgArr2[i]+bgArr2[j];

				if(max == sum){
					ArrayList<Integer> al = new ArrayList<>();
					al.add(fgHm.get(fgArr2[i]));
					al.add(bgHm.get(bgArr2[j]));
					pairs.add(al);
				}
			}
		}
		return pairs;
	}



	public static void main (String args[]) {

	}

}
