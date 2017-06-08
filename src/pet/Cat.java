package pet;

import java.io.Serializable;

import petInterface.Pet;

public class Cat implements Pet,Serializable{	// 猫是宠物，实现接口，对象序列化
	private String name ;	// 宠物名字
	private String color ;	// 宠物颜色
	private String age ;		// 宠物年龄
	public Cat(){}
	public Cat(String name,String color,String age){
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
};