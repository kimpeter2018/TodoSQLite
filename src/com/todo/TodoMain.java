package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {

	public static void start() {
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
//		l.importData("todolist.txt");
		boolean isList = false;
		boolean quit = false;
		Menu.displaymenu();
		do {
			Menu.prompt();
			isList = false;
			String choice = sc.next();
			switch (choice) {

			case "add", "1":
				TodoUtil.createItem(l);
				break;

			case "del", "2":
				TodoUtil.listAll(l);
				TodoUtil.deleteItem(l);
				break;
				
			case "DEL":
				TodoUtil.listAll(l);
				TodoUtil.deleteItems(l);
				break;

			case "edit", "3":
				TodoUtil.listAll(l);
				TodoUtil.updateItem(l);
				break;

			case "ls", "4":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc", "asc", "5":
				System.out.println("����Ʈ�� ��������� ���ĵǾ����ϴ�.");
				TodoUtil.listAll(l, "title", 1);
				isList = true;
				break;

			case "ls_name_desc", "desc", "6":
				System.out.println("����Ʈ�� ���񿪼����� ���ĵǾ����ϴ�.");
				TodoUtil.listAll(l, "title", 0);
				isList = true;
				break;

			case "ls_date", "date", "7":
				System.out.println("����Ʈ�� ��¥������ ���ĵǾ����ϴ�.");
				TodoUtil.listAll(l, "due_date", 1);
				isList = true;
				break;
			case "ls_date_desc", "d_desc", "8":
				System.out.println("����Ʈ�� ��¥�������� ���ĵǾ����ϴ�.");
				TodoUtil.listAll(l, "due_date", 0);
				isList = true;
				break;
			case "ls_cate", "cate", "9":
				TodoUtil.listCategory(l);
				break;
			case "find":
				String key = sc.nextLine().trim();
				TodoUtil.find(l, key);
				break;
			case "find_cate":
				String category = sc.next().trim();
				TodoUtil.find_cate(l, category);
				break;
			case "done", "d", "10":
				TodoUtil.markDone(l);
				break;

			case "help", "0":
				Menu.displaymenu();
				break;

			case "exit", "11", "q":
				quit = true;
				break;

			default:
				System.out.println("�������� �°� ������ �ֽʽÿ�");
				break;
			}

		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
