package com.todo.menu;

public class Menu {

	public static void displaymenu() {
		System.out.println("��� �������� ��ɾ� �Ǵ� ���ڷ� ���� �����մϴ�.");
		System.out.println("1. �׸� �߰� ( add )");
		System.out.println("2. �׸� ���� ( del || �ټ� ���� : DEL)");
		System.out.println("3. �׸� ����  ( edit )");
		System.out.println("4. ��ü ��� ( ls )");
		System.out.println("5. ����� ���� ( ls_name_asc or asc)");
		System.out.println("6. ���񿪼� ���� ( ls_name_desc or desc)");
		System.out.println("7. ��¥�� ���� ( ls_date or date)");
		System.out.println("8. ��¥���� ���� ( ls_date_desc or d_desc)");
		System.out.println("9. ī�װ� ��� ( ls_cate or cate )");
		System.out.println("10. �Ϸ� ǥ�� ( done or d )");
		System.out.println("11. ���� (exit or q)\n");
		System.out.println("Ű����� �׸� �˻� (find Ű����)");
		System.out.println("Ű����� ī�װ� �˻� (find_cate ī�װ�)");
	}

	public static void prompt() {
		System.out.print("\nEnter Command (�޴�����: help | 0) > ");
	}
}
