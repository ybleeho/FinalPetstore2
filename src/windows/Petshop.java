package windows;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import pet.Cat;
import pet.Dog;
import petInterface.Pet;
public class Petshop extends JFrame{
    JLabel lb=new JLabel("录入请先输入！记录，查询、删除请先输入名字！修改是对查询内容改后的保存！");
    JTextField jtname,jtcolor,jtage;
    JRadioButton jrdog,jrcat;
    ButtonGroup group=null;
    JButton 录入,查询,删除,修改,显示;
    JPanel p1,p2,p3,p4,pv,ph;
    Dog dog=null;
    Hashtable<String, Pet>宠物散列表=null;
    File file=null;
    FileInputStream inOne=null;
    ObjectInputStream inTwo=null;
    FileOutputStream outOne=null;
    ObjectOutputStream outTwo=null;
    public Petshop(){
      super("宠物管理系统");
      jtname=new JTextField(10);
      jtcolor=new JTextField(10);
      jtage=new JTextField(10);
      group=new ButtonGroup();
      jrdog=new JRadioButton("狗",true);
      jrcat=new JRadioButton("猫",false);
      group.add(jrdog);
      group.add(jrcat);
      录入=new JButton("录入");
      查询=new JButton("查询");
      删除=new JButton("删除");
      修改=new JButton("修改");
      显示=new JButton("显示");
      录入.addActionListener(new InputAct());
      查询.addActionListener(new InquestAct());
      修改.addActionListener(new ModifyAct());
      删除.addActionListener(new DeleteAct());
      显示.addActionListener(new ShowAct());
      修改.setEnabled(false);
      p1=new JPanel();
      p1.add(new JLabel("宠物名:",JLabel.CENTER));
      p1.add(jtname);
      p2=new JPanel();
      p2.add(new JLabel("颜色:",JLabel.CENTER));
      p2.add(jtcolor);
      p3=new JPanel();
      p3.add(new JLabel("年龄:",JLabel.CENTER));
      p3.add(jtage);
      p4=new JPanel();
      p4.add(jrdog);
      p4.add(jrcat);
      pv=new JPanel();
      pv.setLayout(new GridLayout(4,1));
      pv.add(p1);
      pv.add(p2);
      pv.add(p3);
      pv.add(p4);
      ph=new JPanel();
      ph.add(录入);
      ph.add(查询);
      ph.add(修改);
      ph.add(删除);
      ph.add(显示);
      file=new File("f:\\宠物信息.txt");
      宠物散列表=new Hashtable<String, Pet>();
        if(!file.exists()){
            try{
                FileOutputStream out=new FileOutputStream(file);
                ObjectOutputStream objectOut=new ObjectOutputStream(out);
                objectOut.writeObject(宠物散列表);
                objectOut.close();
                out.close();
            }
            catch(IOException e){}
        }
        Container con=getContentPane();
        con.setLayout(new BorderLayout());
        con.add(lb, BorderLayout.NORTH);
        con.add(pv, BorderLayout.CENTER);
        con.add(ph, BorderLayout.SOUTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(100,100,600,300);
        setVisible(true);
        
    }
    public static void main(String[] args) {new Petshop();}
    class InputAct implements ActionListener{
        @SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e){
           修改.setEnabled(false);
           String name="";
           name=jtname.getText();
          if(name.length()>0){
              try{
                  
                  inOne=new FileInputStream(file);
                  inTwo=new ObjectInputStream(inOne);
                  宠物散列表=(Hashtable<String, Pet>)inTwo.readObject();
                  inOne.close();
                  inTwo.close();
              }
              catch(Exception ee){System.out.println("创建散列表出现问题啊！");}
              if(宠物散列表.containsKey(name)){
                  String warning="该宠物信息已存在，请到修改页面修改！";
                  JOptionPane.showMessageDialog(null,warning,"警告",
                          JOptionPane.WARNING_MESSAGE);
              }//end if1
              else{
                  String m="该宠物信息将被录入！";
                  int ok=JOptionPane.showConfirmDialog(null,m,"确认",
                          JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
                  if(ok==JOptionPane.YES_OPTION){
                      String color=jtcolor.getText();
                      String age=jtage.getText();
                    	  dog=new Dog();
                          dog.setName(name);
                          dog.setColor(color);
                          dog.setAge(age);
                       
                    	  
                      
                      try{
                          outOne=new FileOutputStream(file);
                          outTwo=new ObjectOutputStream(outOne);
                          宠物散列表.put(name,dog);
                          outTwo.writeObject(宠物散列表);
                          outTwo.close();
                          outOne.close();
                      }
                      catch(Exception ee){System.out.println("输出散列表出现问题！");}
                      jtname.setText(null);
                      jtcolor.setText(null);
                      jtage.setText(null);
              
                  }
              }//end else1
          }//end if0
          else{
              String warning="必须输入宠物名字！";
              JOptionPane.showMessageDialog(null,warning,
                      "警告",JOptionPane.WARNING_MESSAGE);
          }//end else0
      }//end actionPerformed
  }//end class
    class InquestAct implements ActionListener{
        @SuppressWarnings("unchecked")
		public void actionPerformed(ActionEvent e){
           String name="";
           name=jtname.getText();
          if(name.length()>0){
              try{
                  inOne=new FileInputStream(file);
                  inTwo=new ObjectInputStream(inOne);
                  宠物散列表=(Hashtable<String, Pet>)inTwo.readObject();
                  inOne.close();
                  inTwo.close();
              }
              catch(Exception ee){System.out.println("散列表有问题！");}
              if(宠物散列表.containsKey(name)){
                修改.setEnabled(true);
                Pet pet=(Pet)宠物散列表.get(name);
                jtcolor.setText(pet.getColor());
                jtage.setText(pet.getAge());
                
              }
              else{
                  修改.setEnabled(false);
                  String warning="该宠物不存在！";
              JOptionPane.showMessageDialog(null,warning,
                      "警告",JOptionPane.WARNING_MESSAGE);
              }
          }
          else{
          修改.setEnabled(false);
          String warning="必须输入宠物！";
              JOptionPane.showMessageDialog(null,warning,
                      "警告",JOptionPane.WARNING_MESSAGE);
          }
        }
    }
    class ModifyAct implements ActionListener{
       public void actionPerformed(ActionEvent e){
           String name=jtname.getText();
           String color=jtcolor.getText();
           String age=jtage.getText();
           String dc=null;
           if(jrdog.isSelected()){dc=jrdog.getText();}
           else{dc=jrcat.getText();}
           Dog dog=new Dog();
           dog.setName(name);
           dog.setColor(color);
           dog.setAge(age);
           
           try{
               outOne=new FileOutputStream(file);
               outTwo=new ObjectOutputStream(outOne);
               宠物散列表.put(name, dog);
               outTwo.writeObject(宠物散列表);
               outTwo.close();
               outOne.close();
               jtname.setText(null);
               jtcolor.setText(null);
               jtage.setText(null);
               
           }
           catch(Exception ee){
               System.out.println("录入修改出现异常!");
               修改.setEnabled(false);
           }
       }
   }
    class DeleteAct implements ActionListener{
       @SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e){
           修改.setEnabled(false);
           String name=jtname.getText();
          if(name.length()>0){
              try{
                  inOne=new FileInputStream(file);
                  inTwo=new ObjectInputStream(inOne);
                  宠物散列表=(Hashtable<String, Pet>)inTwo.readObject();
                  inOne.close();
                  inTwo.close();
              }
              catch(Exception ee){}
              if(宠物散列表.containsKey(name)){
                Dog stu=(Dog)宠物散列表.get(name);
                jtcolor.setText(stu.getColor());
                jtage.setText(stu.getAge());
              }
              String m="确定要删除该宠物的记录吗？";
              int ok=JOptionPane.showConfirmDialog(null,m,"确认",
                 JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
              if(ok==JOptionPane.YES_OPTION){
                  宠物散列表.remove(name);
                  try{
                      outOne=new FileOutputStream(file);
                      outTwo=new ObjectOutputStream(outOne);
                      outTwo.writeObject(宠物散列表);
                      outTwo.close();
                      outOne.close();
                      jtname.setText(null);
                      jtcolor.setText(null);
                      jtage.setText(null);
                   
                  }
                  catch(Exception ee){System.out.println(ee);}
              }
              else if(ok==JOptionPane.NO_OPTION){
                  jtname.setText(null);
                  jtcolor.setText(null);
                  jtage.setText(null);
                  
              }
              else{
                  String warning="该宠物不存在！";
                  JOptionPane.showMessageDialog(null,warning,
                          "警告",JOptionPane.WARNING_MESSAGE);
              }
          }
          else{
               String warning="必须输入宠物！";
               JOptionPane.showMessageDialog(null,warning,
                      "警告",JOptionPane.WARNING_MESSAGE);
          }
       }
   }
    class ShowAct implements ActionListener{
       public void actionPerformed(ActionEvent e){
           new PetShow(file);
       }
   }
    class PetShow extends JDialog{
       Hashtable<String, Pet> 宠物散列表= null;
       JTextArea 显示=null;
       FileInputStream inOne=null;
       ObjectInputStream inTwo=null;
       File file=null;
       @SuppressWarnings("unchecked")
	public PetShow(File file){
           super(new JFrame(),"显示对话框");
           this.file=file;
           显示=new JTextArea(16,30);
           try{
               inOne=new FileInputStream(file);
               inTwo=new ObjectInputStream(inOne);
               宠物散列表=(Hashtable<String, Pet>)inTwo.readObject();
               inOne.close();
               inTwo.close();
           }
           catch(Exception ee){}
           if(宠物散列表.isEmpty())显示.append("目前还没有宠物记录！\n");
           else{
               显示.setText("名字 颜色 年龄 \n");
               for(Enumeration<Pet> enm=宠物散列表.elements();enm.hasMoreElements();){
                   Dog dog=(Dog)enm.nextElement();
                   
                   String str=dog.getName()+","+dog.getColor()+","+dog.getAge()+"\n";
                   显示.append(str);
               }
           }
           JScrollPane scroll=new JScrollPane(显示);
           Container con=getContentPane();
           con.add("Center",scroll);
           con.validate();
           setVisible(true);
           setBounds(200,200,400,300);
           addWindowListener(new WindowAdapter(){
               public void windowClosing(WindowEvent e){setVisible(false);}
           }
           );
       }
   }
}
