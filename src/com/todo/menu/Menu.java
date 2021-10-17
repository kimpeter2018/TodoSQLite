package com.todo.menu;

public class Menu {

	public static void displaymenu() {
		System.out.println("모든 선택지는 명령어 또는 숫자로 실행 가능합니다.");
		System.out.println("1. 항목 추가 ( add )");
		System.out.println("2. 항목 삭제 ( del || 다수 삭제 : DEL)");
		System.out.println("3. 항목 수정  ( edit )");
		System.out.println("4. 전체 목록 ( ls )");
		System.out.println("5. 제목순 정렬 ( ls_name_asc or asc)");
		System.out.println("6. 제목역순 정렬 ( ls_name_desc or desc)");
		System.out.println("7. 날짜순 정렬 ( ls_date or date)");
		System.out.println("8. 날짜역순 정렬 ( ls_date_desc or d_desc)");
		System.out.println("9. 카테고리 목록 ( ls_cate or cate )");
		System.out.println("10. 완료 표시 ( done or d )");
		System.out.println("11. 종료 (exit or q)\n");
		System.out.println("마감 날짜 지난 할일 지우기(X)");
		System.out.println("완료한 할일 지우기(O)\n");
		System.out.println("키워드로 항목 검색 (find 키워드)");
		System.out.println("키워드로 카테고리 검색 (find_cate 카테고리)\n");
		System.out.println("중요도 4이상만 표시하기 ( I )");
	}

	public static void prompt() {
		System.out.print("\nEnter Command (메뉴보기: help | 0) > ");
	}
}
