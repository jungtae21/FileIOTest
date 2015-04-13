import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class Main {

	// 자바 1.5로 complie 바꿔야 사용이 가능.
	static HashMap<String, List<String>> map = new HashMap<String, List<String>>();
	static Multimap<String, List<String>> multiMap = ArrayListMultimap.create();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		while (true) {
			Scanner sc = new Scanner(System.in);
			System.out.println("==================");
			System.out.println("원하는 메뉴를 입력해주세요");
			System.out.println("1.주문서 목록 읽어오기");
			System.out.println("2.주문번호입력, 주문가격출력");
			System.out.println("3.사용자ID입력, ID에 해당하는 주문 출력, 합계출력");
			System.out.println("==================");
			int num = sc.nextInt();
			System.out.println(num + "번이 입력되었습니다.");

			switch (num) {
			case 1:
				fileRead();
				break;
			case 2:
				orderNum();
				break;
			case 3:
				idInfo();
				break;
			default:
				System.out.println("잘못된 입력입니다");
				break;
			}
		}
	}

	private static void fileRead() {
		// TODO Auto-generated method stub
		System.out.println("주문서 목록 읽기");

		File file = new File("sample.txt");
		FileReader fileReader = null;

		try {
			fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			// System.out.println(reader);
			String line = null;

			while ((line = reader.readLine()) != null) {
				// System.out.println(line);
				String[] arr = line.split(",");
				int arrLength = arr.length - 2;

				ArrayList<String> list = new ArrayList<String>();
				for (int i = 0; i < arrLength; i++) {
					list.add(arr[i + 2]);
					// System.out.println(list.get(i));

				}
				// 1. 주문번호(key)에 따른 주문정보들(values)
				map.put(arr[0], list);
				
				// 2. 사용자ID(key)에 따른 주문정보들(values)
				// 사용자ID 하나에 대해서 여러개 주문번호가 가능
				multiMap.put(arr[1], list);
			}
			reader.close();
			System.out.println("입력이 완료되었습니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private static void orderNum() {
		// TODO Auto-generated method stub
		System.out.println("주문번호입력, 주문가격출력");
		System.out.println("주문번호 입력 : ");
		
		Scanner sc = new Scanner(System.in);
		String num = sc.next();
		
		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if(key.equals(num)){
			System.out.println(key+"번호에 대한 주문 내역");
			System.out.println(map.get(key));	
			}
		}
	}
	
	private static void idInfo() {
		// TODO Auto-generated method stub
		System.out.println("사용자 ID입력, 주문,합계 출력");
		System.out.println("사용자 ID입력 : ");
		
		Scanner sc = new Scanner(System.in);
		String id = sc.next();
		
		Iterator<String> iterator = multiMap.keySet().iterator();
		//Collection<List<String>> iterator2 = multiMap.values();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if(key.equals(id)){
			System.out.println(key+"님의 주문 내역");
			System.out.println(multiMap.get(key));
			//multiMap.containsEntry(key, "H");
			
			//Collection<List<String>> a = multiMap.get(key);
			
			//System.out.println(a.toArray().equals("H"));
			
			
			//System.out.println(a.get(0));
			}
		}
	}
}
