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

		System.out.print("[�׸� �߰�]\n" + "������ �Է��ϼ��� > ");
		title = sc.nextLine();
//		if (list.isDuplicate(title)) {
//			System.out.print(title + "(��)�� �̹� �����մϴ�!");
//			return;
//		}

		System.out.print("ī�װ��� �Է��ϼ��� > ");
		String category = sc.nextLine().trim();
		System.out.print("������ �Է��ϼ��� > ");
		desc = sc.nextLine().trim();
		System.out.print("�������ڸ� �Է��ϼ��� > ");
		String due_date = sc.nextLine().trim();
		System.out.print("�߿䵵�� �Է��ϼ��� > ");
		int importance = sc.nextInt();
		sc.nextLine();
		System.out.print("��ġ�� �Է��ϼ��� > ");
		String location = sc.nextLine().trim();
		System.out.print("�Ϸ� �Ͽ����ϱ�?(0 : no | 1 : yes) > ");
		int done = sc.nextInt();
		TodoItem t = new TodoItem(title, desc, category, due_date, importance, location, done);
		if (list.addItem(t) > 0)
			System.out.println("�� �׸��� ���������� �߰� �Ǿ����ϴ�!");

	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸� ����]\n" + "������ �׸��� ��ȣ�� �Է��ϼ��� > ");
		int select = sc.nextInt();

		if (l.deleteItem(select) > 0)
			System.out.println("���������� �����Ǿ����ϴ�!");
	}
	
	public static void deleteItems(TodoList l) {

		Scanner sc = new Scanner(System.in);
		int count = 0;
		System.out.print("[�׸� ����]\n" + "��� �׸��� �����ϰڽ��ϱ�? > ");
		int choice = sc.nextInt();
		
		System.out.print("���� �� �׸���� �Է��ϼ��� > ");
		ArrayList<Integer> selects = new ArrayList<Integer>();
		while(count < choice) {
			selects.add(sc.nextInt());
			count++;
		}
		for(int i : selects) {
			l.deleteItem(i);
		}
		System.out.println("���������� �����Ǿ����ϴ�!");
	}
	
	public static void markDone(TodoList l) {

		Scanner sc = new Scanner(System.in);
		int count = 0;
		int done = 0;
		System.out.print("[���� �Ϸ�]\n" + "��� �׸��� �Ϸ� ó�� �ϰڽ��ϱ�? > ");
		int choice = sc.nextInt();
		
		System.out.print("�Ϸ��� �׸���� �Է��ϼ��� > ");
		ArrayList<Integer> selects = new ArrayList<Integer>();
		while(count < choice) {
			selects.add(sc.nextInt());
			count++;
		}
		for(int i : selects) {
			done += l.markAsDone(i);
		}
		if(done == count)
			System.out.println("�Ϸ� ó���Ǿ����ϴ�!");
	}

	public static void updateItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸� ����]\n" + "������ �׸��� ��ȣ�� �Է��ϼ��� > ");
		int select = sc.nextInt();

		if (select > l.getList().size()) {
			System.out.println("�ش� ��ȣ�� �������� �ʽ��ϴ�.");
			return;
		}
		sc.nextLine();

		System.out.print("�� ���� > ");
		String new_title = sc.nextLine();
//		if (l.isDuplicate(new_title)) {
//			System.out.print(new_title + "(��)�� �̹� �����մϴ�!");
//			return;
//		}
		System.out.print("�� ī�װ� > ");
		String new_category = sc.nextLine().trim();
		System.out.print("�� ���� > ");
		String new_description = sc.nextLine().trim();
		System.out.print("�� �������� > ");
		String new_due_date = sc.nextLine().trim();
		System.out.print("�� �߿䵵�� �Է��ϼ��� > ");
		int new_importance = sc.nextInt();
		System.out.print("�� ��ġ�� �Է��ϼ��� > ");
		String new_location = sc.nextLine().trim();
		System.out.print("�Ϸ� �Ͽ����ϱ�?(0 : no | 1 : yes) > ");
		int new_done = sc.nextInt();
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date, new_importance, new_location, new_done);
		t.setId(select);
		if (l.updateItem(t) > 0)
			System.out.println("�׸��� ���������� �����Ǿ����ϴ�!");
	}

	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.println("[��ü ���, �� " + l.getCount() + "��]");
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���, �� " + l.getCount() + "��]");
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
		System.out.println("\n�� " + count + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�");
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
			System.out.println("�ش� Ű���尡 ���� ���� �ʽ��ϴ�!");
		} else {
			System.out.printf("\n�� %d���� �׸��� ã�ҽ��ϴ�\n", count);
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
			System.out.println("�ش� ī�װ��� ���� ���� �ʽ��ϴ�!");
		}
	}

	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();

			System.out.println("���� ���� ���� !!");
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
//			System.out.println("���� �ε� �Ϸ� !!! ");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
