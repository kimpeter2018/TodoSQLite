package com.todo.service;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
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

		System.out.print("마감일자를 입력하세요 (숫자6자리)> ");
		String received_date = sc.nextLine().trim();
		String due_date;
		if (received_date.contains("/")) {
			due_date = received_date;
		} else {
			int parsed_date = Integer.parseInt(received_date);
			String array[] = new String[3];
			int i = 0;
			while (parsed_date > 1) {
				int temp = parsed_date % 100;
				if (temp < 10)
					array[i] = "0" + Integer.toString(temp);
				else
					array[i] = Integer.toString(temp);
				parsed_date /= 100;
				i++;
			}
			due_date = "20" + array[2] + "/" + array[1] + "/" + array[0];
		}
		System.out.print("중요도를 입력하세요 > ");
		int importance = sc.nextInt();
		sc.nextLine();
		System.out.print("위치를 입력하세요 > ");
		String location = sc.nextLine().trim();
//		System.out.print("완료 하였습니까?(0 : no | 1 : yes) > ");
//		int done = sc.nextInt();
		TodoItem t = new TodoItem(title, desc, category, due_date, importance, location);
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

	public static void deleteItems(TodoList l) {

		Scanner sc = new Scanner(System.in);
		int count = 0;
		System.out.print("[항목 삭제]\n" + "몇개의 항목을 삭제하겠습니까? > ");
		int choice = sc.nextInt();

		System.out.print("삭제 할 항목들을 입력하세요 > ");
		ArrayList<Integer> selects = new ArrayList<Integer>();
		while (count < choice) {
			selects.add(sc.nextInt());
			count++;
		}
		for (int i : selects) {
			l.deleteItem(i);
		}
		System.out.println("성공적으로 삭제되었습니다!");
	}

	public static void deleteLate(TodoList l) {

		Scanner sc = new Scanner(System.in);
		System.out.print("날짜가 지난 할일들을 모두 삭제하시겠습니까? (Press any key for yes, press n or N for no) > ");
		String choice = sc.next();
		sc.nextLine();
		if (choice.equals("N") || choice.equals("n"))
			return;
		else {
			SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
			String current_date = f.format(new Date());
			ArrayList<TodoItem> list = l.getList();
			for (TodoItem item : list) {
				if (item.getDue_date().compareTo(current_date) < 0) {
					l.deleteItem(item.getId());
				}
			}
			System.out.println("성공적으로 삭제되었습니다!");
		}
	}

	public static void deleteDone(TodoList l) {

		Scanner sc = new Scanner(System.in);
		System.out.print("완료한 할일들을 모두 삭제하시겠습니까? (Press any key for yes, press n or N for no) > ");
		String choice = sc.next();
		sc.nextLine();
		if (choice.equals("N") || choice.equals("n"))
			return;
		else {
			ArrayList<TodoItem> list = l.getList();
			for (TodoItem item : list) {
				if (item.isDone() == 1) {
					l.deleteItem(item.getId());
				}
			}
			System.out.println("성공적으로 삭제되었습니다!");
		}
	}

	public static void markDone(TodoList l) {

		Scanner sc = new Scanner(System.in);
		int count = 0;
		int done = 0;
		System.out.print("[할일 완료]\n" + "몇개의 항목을 완료 처리 하겠습니까? > ");
		int choice = sc.nextInt();

		System.out.print("완료한 항목들을 입력하세요 > ");
		ArrayList<Integer> selects = new ArrayList<Integer>();
		while (count < choice) {
			selects.add(sc.nextInt());
			count++;
		}
		for (int i : selects) {
			done += l.markAsDone(i);
		}
		if (done == count)
			System.out.println("완료 처리되었습니다!");
	}

	public static void updateItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("[항목 수정]\n" + "수정할 항목의 번호를 입력하세요 > ");
		int select = sc.nextInt();

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

		System.out.print("새 마감일자 (숫자6자리)> ");
		String received_date = sc.nextLine().trim();
		String new_due_date;
		if (received_date.contains("/")) {
			new_due_date = received_date;
		} else {
			int parsed_date = Integer.parseInt(received_date);
			String array[] = new String[3];
			int i = 0;
			while (parsed_date > 1) {
				int temp = parsed_date % 100;
				if (temp < 10)
					array[i] = "0" + Integer.toString(temp);
				else
					array[i] = Integer.toString(temp);
				parsed_date /= 100;
				i++;
			}
			new_due_date = "20" + array[2] + "/" + array[1] + "/" + array[0];
		}

		System.out.print("새 중요도를 입력하세요 > ");
		int new_importance = sc.nextInt();
		sc.nextLine();
		System.out.print("새 위치를 입력하세요 > ");
		String new_location = sc.nextLine().trim();
//		System.out.print("완료 하였습니까?(0 : no | 1 : yes) > ");
//		int new_done = sc.nextInt();
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date, new_importance, new_location);
		t.setId(select);
		if (l.updateItem(t) > 0)
			System.out.println("항목이 성공적으로 수정되었습니다!");
	}

	public static void listAll(TodoList l, String orderby, int ordering, boolean showAll) {
		System.out.println("[전체 목록, 총 " + l.getCount() + "개]");
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			if (!showAll) {
				if (item.getImportance() > 3)
					System.out.println(item.toString());
			} else
				System.out.println(item.toString());
		}
	}

	public static void listAll(TodoList l, boolean showAll) {
		System.out.println("[전체 목록, 총 " + l.getCount() + "개]");
		for (TodoItem item : l.getList()) {
			if (!showAll) {
				if (item.getImportance() > 3)
					System.out.println(item.toString());
			} else {

				System.out.println(item.toString());
				}
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
