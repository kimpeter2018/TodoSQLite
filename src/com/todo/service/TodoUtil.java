package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
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
		if (list.isDuplicate(title)) {
			System.out.print(title + "(��)�� �̹� �����մϴ�!");
			return;
		}

		System.out.print("������ �Է��ϼ��� > ");
		desc = sc.nextLine().trim();
		System.out.print("ī�װ��� �Է��ϼ��� > ");
		String category = sc.nextLine().trim();
		System.out.print("�������ڸ� �Է��ϼ��� > ");
		String due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(title, desc, null, category, due_date);
		list.addItem(t);
		System.out.println("�� �׸��� ���������� �߰� �Ǿ����ϴ�!");
	}

	public static void deleteItem(TodoList l) {

		Scanner sc = new Scanner(System.in);

		System.out.print("[�׸� ����]\n" + "������ �׸��� ��ȣ�� �Է��ϼ��� > ");

		int select = sc.nextInt();

		if (select > l.getList().size()) {
			System.out.println("�ش� ��ȣ�� �������� �ʽ��ϴ�.");
			return;
		}

		l.deleteItem(l.getList().get(select - 1));
		System.out.println("���������� �����Ǿ����ϴ�!");
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
		if (l.isDuplicate(new_title)) {
			System.out.print(new_title + "(��)�� �̹� �����մϴ�!");
			return;
		}
		System.out.print("�� ī�װ� > ");
		String new_category = sc.nextLine().trim();
		System.out.print("�� ���� > ");
		String new_description = sc.nextLine().trim();
		System.out.print("�� �������� > ");
		String new_due_date = sc.nextLine().trim();

		l.deleteItem(l.getList().get(select - 1));
		TodoItem t = new TodoItem(new_title, new_description, null, new_category, new_due_date);
		l.addItem(t);
		System.out.println("�׸��� ���������� �����Ǿ����ϴ�!");
	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���, �� " + l.getList().size() + "��]");
		for (TodoItem item : l.getList()) {
			int count = l.getList().indexOf(item) + 1;
			System.out.println(count + ". " + item.toString());
		}
	}

	public static void listCategory(TodoList l) {
		ArrayList<String> category = new ArrayList<String>();
		for (TodoItem item : l.getList()) {
			category.add(item.getCategory());
			if (l.getList().indexOf(item) != l.getList().size() - 1)
				System.out.print(item.getCategory() + " / ");
			else
				System.out.print(item.getCategory());
		}
		System.out.println("\n�� " + category.size() + "���� ī�װ��� ��ϵǾ� �ֽ��ϴ�");
	}

	public static void find(TodoList l, String key) {
		boolean found = false;
		int count = 0;
		for (TodoItem item : l.getList()) {
			if (item.getDesc().contains(key) || item.getTitle().contains(key)) {
				int index = l.getList().indexOf(item) + 1;
				System.out.println(index + ". " + item.toString());
				found = true;
				count++;
			}
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

	public static void loadList(TodoList l, String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String oneline;
			while ((oneline = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(oneline, "##");
				String title = st.nextToken();
				String desc = st.nextToken();
				String date = st.nextToken();
				String category = st.nextToken();
				String due_date = st.nextToken();
				TodoItem t = new TodoItem(title, desc, date, category, due_date);
				l.addItem(t);
			}
			br.close();

			System.out.println("���� �ε� �Ϸ� !!! ");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
