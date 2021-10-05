package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {

	public static void start() {

		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt");
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

			case "edit", "3":
				TodoUtil.listAll(l);
				TodoUtil.updateItem(l);
				break;

			case "ls", "4":
				TodoUtil.listAll(l);
				break;

			case "ls_name_asc", "asc", "5":
				l.sortByName();
				System.out.println("리스트가 제목순으로 정렬되었습니다.");
				isList = true;
				break;

			case "ls_name_desc", "desc", "6":
				l.sortByName();
				l.reverseList();
				System.out.println("리스트가 제목역순으로 정렬되었습니다.");
				isList = true;
				break;

			case "ls_date", "date", "7":
				l.sortByDate();
				System.out.println("리스트가 날짜순으로 정렬되었습니다.");
				isList = true;
				break;
			case "ls_date_desc", "d_desc", "8":
				l.sortByDateDesc();
				System.out.println("리스트가 날짜역순으로 정렬되었습니다.");
				isList = true;
				break;
			case "ls_cate", "cate", "9":
				TodoUtil.listCategory(l);
				break;
			case "find":
				String key = sc.next().trim();
				TodoUtil.find(l, key);
				break;
			case "find_cate":
				String category = sc.next().trim();
				TodoUtil.find_cate(l, category);
				break;

			case "help", "0":
				Menu.displaymenu();
				break;

			case "exit", "10", "q":
				quit = true;
				break;

			default:
				System.out.println("선택지에 맞게 선택해 주십시오");
				break;
			}

			if (isList)
				TodoUtil.listAll(l);
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}
