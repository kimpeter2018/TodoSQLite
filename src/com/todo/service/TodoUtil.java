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

		System.out.print("�������ڸ� �Է��ϼ��� (����6�ڸ�)> ");
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
		System.out.print("�߿䵵�� �Է��ϼ��� > ");
		int importance = sc.nextInt();
		sc.nextLine();
		System.out.print("��ġ�� �Է��ϼ��� > ");
		String location = sc.nextLine().trim();
//		System.out.print("�Ϸ� �Ͽ����ϱ�?(0 : no | 1 : yes) > ");
//		int done = sc.nextInt();
		TodoItem t = new TodoItem(title, desc, category, due_date, importance, location);
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
		while (count < choice) {
			selects.add(sc.nextInt());
			count++;
		}
		for (int i : selects) {
			l.deleteItem(i);
		}
		System.out.println("���������� �����Ǿ����ϴ�!");
	}

	public static void deleteLate(TodoList l) {

		Scanner sc = new Scanner(System.in);
		System.out.print("��¥�� ���� ���ϵ��� ��� �����Ͻðڽ��ϱ�? (Press any key for yes, press n or N for no) > ");
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
			System.out.println("���������� �����Ǿ����ϴ�!");
		}
	}

	public static void deleteDone(TodoList l) {

		Scanner sc = new Scanner(System.in);
		System.out.print("�Ϸ��� ���ϵ��� ��� �����Ͻðڽ��ϱ�? (Press any key for yes, press n or N for no) > ");
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
			System.out.println("���������� �����Ǿ����ϴ�!");
		}
	}

	public static void markDone(TodoList l) {

		Scanner sc = new Scanner(System.in);
		int count = 0;
		int done = 0;
		System.out.print("[���� �Ϸ�]\n" + "��� �׸��� �Ϸ� ó�� �ϰڽ��ϱ�? > ");
		int choice = sc.nextInt();

		System.out.print("�Ϸ��� �׸���� �Է��ϼ��� > ");
		ArrayList<Integer> selects = new ArrayList<Integer>();
		while (count < choice) {
			selects.add(sc.nextInt());
			count++;
		}
		for (int i : selects) {
			done += l.markAsDone(i);
		}
		if (done == count)
			System.out.println("�Ϸ� ó���Ǿ����ϴ�!");
	}

	public static void updateItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸� ����]\n" + "������ �׸��� ��ȣ�� �Է��ϼ��� > ");
		int select = sc.nextInt();

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

		System.out.print("�� �������� (����6�ڸ�)> ");
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

		System.out.print("�� �߿䵵�� �Է��ϼ��� > ");
		int new_importance = sc.nextInt();
		sc.nextLine();
		System.out.print("�� ��ġ�� �Է��ϼ��� > ");
		String new_location = sc.nextLine().trim();
//		System.out.print("�Ϸ� �Ͽ����ϱ�?(0 : no | 1 : yes) > ");
//		int new_done = sc.nextInt();
		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date, new_importance, new_location);
		t.setId(select);
		if (l.updateItem(t) > 0)
			System.out.println("�׸��� ���������� �����Ǿ����ϴ�!");
	}

	public static void listAll(TodoList l, String orderby, int ordering, boolean showAll) {
		System.out.println("[��ü ���, �� " + l.getCount() + "��]");
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			if (!showAll) {
				if (item.getImportance() > 3)
					System.out.println(item.toString());
			} else
				System.out.println(item.toString());
		}
	}

	public static void listAll(TodoList l, boolean showAll) {
		System.out.println("[��ü ���, �� " + l.getCount() + "��]");
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
