package pet;

import java.io.Serializable;

import petInterface.Pet;

public class Dog implements Pet,Serializable{	// ���ǳ��ʵ�ֽӿ�
	private String name ;	// ��������
	private String color ;	// ������ɫ
	private String age ;		// ��������
	public Dog(){}
	public Dog(String name,String color,String age){
		this.setName(name) ;
		this.setColor(color) ;
		this.setAge(age) ;
	}
	public void setName(String name){
		this.name = name ;
	}
	public void setColor(String color){
		this.color = color;
	}
	public void setAge(String age){
		this.age = age ;
	}
	public String getName(){
		return this.name ;
	}
	public String getColor(){
		return this.color ;
	}
	public String getAge(){
		return this.age ;
	}
}