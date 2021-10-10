package com.todo.service;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {

	public static void createItem(TodoList list) {
		String title, desc;
		Scanner sc = new Scanner(System.in);

		System.out.print("[항목 추가]\n" + "제목을 입력하세요 > ");
		title = sc.nextLine();
//		if (list.isDuplicate(title)) {
//			System.out.print(title + "(이)가 이미 존재합니다!");
//			return;
//		}

		System.out.print("카테고리를 입력하세요 > ");
		String category = sc.nextLine().trim();
		System.out.print("내용을 입력하세요 > ");
		desc = sc.nextLine().trim();
		System.out.print("마감일자를 입력하세요 > ");
		String due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(title, desc, category, due_date);
		if (list.addItem(t) > 0)
			System.out.println("새 항목이 성공적으로 추가 되었습니다!");

	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("[항목 삭제]\n" + "제거할 항목의 번호를 입력하세요 > ");
		int select = sc.nextInt();

		if (l.deleteItem(select) > 0)
			System.out.println("성공적으로 삭제되었습니다!");
	}

	public static void updateItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("[항목 수정]\n" + "수정할 항목의 번호를 입력하세요 > ");
		int select = sc.nextInt();

		if (select > l.getList().size()) {
			System.out.println("해당 번호는 존재하지 않습니다.");
			return;
		}
		sc.nextLine();

		System.out.print("새 제목 > ");
		String new_title = sc.nextLine();
//		if (l.isDuplicate(new_title)) {
//			System.out.print(new_title + "(이)가 이미 존재합니다!");
//			return;
//		}
		System.out.print("새 카테고리 > ");
		String new_category = sc.nextLine().trim();
		System.out.print("새 내용 > ");
		String new_description = sc.nextLine().trim();
		System.out.print("새 마감일자 > ");
		String new_due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		t.setId(select);
		if (l.updateItem(t) > 0)
			System.out.println("항목이 성공적으로 수정되었습니다!");
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("[전체 목록, 총 " + l.getCount() + "개]");
		for (TodoItem item : l.getOrderedList(orderby,ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l) {
		System.out.println("[전체 목록, 총 " + l.getCount() + "개]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}

	public static void listCategory(TodoList l) {
		int count = 0;
		for (String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.println("\n총 " + count + "개의 카테고리가 등록되어 있습니다");
	}

	public static void find(TodoList l, String key) {
		boolean found = false;
		int count = 0;
		for (TodoItem item : l.getList(key)) {
				System.out.println(item.toString());
				found = true;
				count++;
			}
		if (!found) {
			System.out.println("해당 키워드가 존재 하지 않습니다!");
		} else {
			System.out.printf("\n총 %d개의 항목을 찾았습니다\n", count);
		}
	}

	public static void find_cate(TodoList l, String key) {
		boolean found = false;
		for (TodoItem item : l.getList()) {
			if (item.getCategory().contentEquals(key)) {
				int count = l.getList().indexOf(item) + 1;
				System.out.println(count + ". " + item.toString());
				found = true;
			}
		}
		if (!found) {
			System.out.println("해당 카테고리가 존재 하지 않습니다!");
		}
	}

	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();

			System.out.println("정보 저장 성공 !!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public static void loadList(TodoList l, String filename) {
//		try {
//			BufferedReader br = new BufferedReader(new FileReader(filename));
//			String oneline;
//			while ((oneline = br.readLine()) != null) {
//				StringTokenizer st = new StringTokenizer(oneline, "##");
//				String title = st.nextToken();
//				String desc = st.nextToken();
//				String date = st.nextToken();
//				String category = st.nextToken();
//				String due_date = st.nextToken();
//				TodoItem t = new TodoItem(title, desc, category, due_date);
//				l.addItem(t);
//			}
//			br.close();
//
//			System.out.println("정보 로딩 완료 !!! ");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
