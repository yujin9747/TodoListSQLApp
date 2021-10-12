package com.todo.service;
 
import java.util.*;
import java.io.* ;
import java.sql.Statement;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;
 
public class TodoUtil {
	public static void createItem(TodoList list) { 
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		  
		System.out.println("\nadd(항목 추가)를 선택하셨습니다!");
		System.out.print("항목 카테고리를 입력해주세요 > ");
		category = sc.next();
		System.out.print("항목 이름을 입력해주세요 > ");
		title = sc.next();
		if(list.isDuplicate(title)) {
			System.out.println("제목이 중복 됩니다.") ;
			return ;
		}
		sc.nextLine();
		System.out.print("내용을 입력해주세요 > ");
		desc = sc.nextLine();
		
		System.out.print("마감기한을 입력해주세요 > ");
		due_date = sc.nextLine();
		TodoItem t = new TodoItem(title, desc, category, due_date);
		if(list.addItem(t) > 0 ) {
			System.out.println(list.indexOf(t)+1 + ". [" + t.getCategory() + "] " 
		+ t.getTitle() + " - " + t.getDesc() + " - " + t.getDue_date() + " - " + t.getCurrent_date());
			System.out.println("**항목 추가 완료**\n");
		}
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\ndel(항목 삭제)를 선택하셨습니다!");
		System.out.print("삭제할 항목 번호를 입력해주세요 > ");
		int number = sc.nextInt() ;

		int count = 1 ;
		for (TodoItem item : l.getList()) {
			if (count == number) {
				System.out.println(number + ". [" + item.getCategory() + "] " 
			+ item.getTitle() + " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
				break ;
			}
			count++ ;
		}
		System.out.print("위 항목을 삭제하시겠습니까? (y/n) > ") ;
		String yesOrno = sc.next() ; 
		if(yesOrno.equals("y")) {
			if(l.deleteItem(number)>0)
				System.out.println("**항목 삭제 완료**\n");
		}

	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("\nedit(항목 수정)를 선택하셨습니다!");
		System.out.print("수정할 항목 번호를 입력해주세요 > ");
		int number = sc.nextInt();
		int count = 1 ;
		for (TodoItem item : l.getList()) {
			if (count == number) {
				System.out.println(number + ". [" + item.getCategory() + "] " + item.getTitle() 
				+ " - " + item.getDesc() + " - " + item.getDue_date() + " - " + item.getCurrent_date());
			}	
			count++ ;
		}
		
		System.out.print("새로운 항목 카테고리를 입력해주세요 > ");
		String new_category = sc.next().trim();
		sc.nextLine() ;
		System.out.print("새로운 항목 이름을 입력해주세요 > ");
		String new_title = sc.next().trim();
		sc.nextLine() ;
		System.out.print("새 항목의 내용을 입력해주세요 > ");
		String new_description = sc.nextLine().trim();
		System.out.print("새 항목의 마감기한을 입력해주세요 > ");
		String new_due_date = sc.nextLine().trim();

		TodoItem t = new TodoItem(new_title, new_description, new_category, new_due_date);
		t.setId(number) ;
		if(l.updateItem(t) > 0) 
			System.out.println("**수정되었습니다**\n");

	}

	

	public static void listAll(TodoList l) { //수정 완료
		System.out.println("\n\n<< 전체 목록 , 총 " + l.getCount()+"개 >>");
		for (TodoItem item : l.getList()) {
			System.out.print(item.toString());
		}
	}
	
	public static void listAll(TodoList l, int comp) {
		int count = 0 ;
		for (TodoItem item : l.getList(comp)) {
			System.out.print(item.toString());
			count++ ;
		}
		System.out.printf("총 %d개의 항목이 완료 되었습니다.\n", count) ;
	}
	
	public static void listAll(TodoList l, String field, int version) { //수정 완료
		//리스트 정렬하는 함수 호출 해서 정렬된 리스트 받기
		//정렬된 리스트 하나하나 출력하기
		System.out.printf("전체 목록 %d개 \n", l.getCount()) ;
		for (TodoItem item : l.getOrderedList(field, version)) {
			System.out.print(item.toString());
		}
	}


	public static void find(TodoList l, String keyword) {
		int count = 0 ;
		for (TodoItem item : l.getList(keyword)) {
			System.out.print(item.toString());
			count++ ;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n\n", count) ;
	}

	public static void find_cat(TodoList l, String cat) {
		int count = 0 ; //키워드로 찾아진 항목의 갯수 카운팅
		for (TodoItem item : l.getListCategory(cat)) {
			System.out.print(item.toString());
			count++ ;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n\n", count) ;
		
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0 ; //키워드로 찾아진 항목의 갯수 카운팅
		for (String item : l.getCategories()) {
			System.out.print(item + " ") ;
			count++ ;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n\n", count) ;
	}
	
}