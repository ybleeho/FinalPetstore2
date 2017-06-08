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
    JLabel lb=new JLabel("¼���������룡��¼����ѯ��ɾ�������������֣��޸��ǶԲ�ѯ���ݸĺ�ı��棡");
    JTextField jtname,jtcolor,jtage;
    JRadioButton jrdog,jrcat;
    ButtonGroup group=null;
    JButton ¼��,��ѯ,ɾ��,�޸�,��ʾ;
    JPanel p1,p2,p3,p4,pv,ph;
    Dog dog=null;
    Hashtable<String, Pet>����ɢ�б�=null;
    File file=null;
    FileInputStream inOne=null;
    ObjectInputStream inTwo=null;
    FileOutputStream outOne=null;
    ObjectOutputStream outTwo=null;
    public Petshop(){
      super("�������ϵͳ");
      jtname=new JTextField(10);
      jtcolor=new JTextField(10);
      jtage=new JTextField(10);
      group=new ButtonGroup();
      jrdog=new JRadioButton("��",true);
      jrcat=new JRadioButton("è",false);
      group.add(jrdog);
      group.add(jrcat);
      ¼��=new JButton("¼��");
      ��ѯ=new JButton("��ѯ");
      ɾ��=new JButton("ɾ��");
      �޸�=new JButton("�޸�");
      ��ʾ=new JButton("��ʾ");
      ¼��.addActionListener(new InputAct());
      ��ѯ.addActionListener(new InquestAct());
      �޸�.addActionListener(new ModifyAct());
      ɾ��.addActionListener(new DeleteAct());
      ��ʾ.addActionListener(new ShowAct());
      �޸�.setEnabled(false);
      p1=new JPanel();
      p1.add(new JLabel("������:",JLabel.CENTER));
      p1.add(jtname);
      p2=new JPanel();
      p2.add(new JLabel("��ɫ:",JLabel.CENTER));
      p2.add(jtcolor);
      p3=new JPanel();
      p3.add(new JLabel("����:",JLabel.CENTER));
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
      ph.add(¼��);
      ph.add(��ѯ);
      ph.add(�޸�);
      ph.add(ɾ��);
      ph.add(��ʾ);
      file=new File("f:\\������Ϣ.txt");
      ����ɢ�б�=new Hashtable<String, Pet>();
        if(!file.exists()){
            try{
                FileOutputStream out=new FileOutputStream(file);
                ObjectOutputStream objectOut=new ObjectOutputStream(out);
                objectOut.writeObject(����ɢ�б�);
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
           �޸�.setEnabled(false);
           String name="";
           name=jtname.getText();
          if(name.length()>0){
              try{
                  
                  inOne=new FileInputStream(file);
                  inTwo=new ObjectInputStream(inOne);
                  ����ɢ�б�=(Hashtable<String, Pet>)inTwo.readObject();
                  inOne.close();
                  inTwo.close();
              }
              catch(Exception ee){System.out.println("����ɢ�б�������Ⱑ��");}
              if(����ɢ�б�.containsKey(name)){
                  String warning="�ó�����Ϣ�Ѵ��ڣ��뵽�޸�ҳ���޸ģ�";
                  JOptionPane.showMessageDialog(null,warning,"����",
                          JOptionPane.WARNING_MESSAGE);
              }//end if1
              else{
                  String m="�ó�����Ϣ����¼�룡";
                  int ok=JOptionPane.showConfirmDialog(null,m,"ȷ��",
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
                          ����ɢ�б�.put(name,dog);
                          outTwo.writeObject(����ɢ�б�);
                          outTwo.close();
                          outOne.close();
                      }
                      catch(Exception ee){System.out.println("���ɢ�б�������⣡");}
                      jtname.setText(null);
                      jtcolor.setText(null);
                      jtage.setText(null);
              
                  }
              }//end else1
          }//end if0
          else{
              String warning="��������������֣�";
              JOptionPane.showMessageDialog(null,warning,
                      "����",JOptionPane.WARNING_MESSAGE);
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
                  ����ɢ�б�=(Hashtable<String, Pet>)inTwo.readObject();
                  inOne.close();
                  inTwo.close();
              }
              catch(Exception ee){System.out.println("ɢ�б������⣡");}
              if(����ɢ�б�.containsKey(name)){
                �޸�.setEnabled(true);
                Pet pet=(Pet)����ɢ�б�.get(name);
                jtcolor.setText(pet.getColor());
                jtage.setText(pet.getAge());
                
              }
              else{
                  �޸�.setEnabled(false);
                  String warning="�ó��ﲻ���ڣ�";
              JOptionPane.showMessageDialog(null,warning,
                      "����",JOptionPane.WARNING_MESSAGE);
              }
          }
          else{
          �޸�.setEnabled(false);
          String warning="����������";
              JOptionPane.showMessageDialog(null,warning,
                      "����",JOptionPane.WARNING_MESSAGE);
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
               ����ɢ�б�.put(name, dog);
               outTwo.writeObject(����ɢ�б�);
               outTwo.close();
               outOne.close();
               jtname.setText(null);
               jtcolor.setText(null);
               jtage.setText(null);
               
           }
           catch(Exception ee){
               System.out.println("¼���޸ĳ����쳣!");
               �޸�.setEnabled(false);
           }
       }
   }
    class DeleteAct implements ActionListener{
       @SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e){
           �޸�.setEnabled(false);
           String name=jtname.getText();
          if(name.length()>0){
              try{
                  inOne=new FileInputStream(file);
                  inTwo=new ObjectInputStream(inOne);
                  ����ɢ�б�=(Hashtable<String, Pet>)inTwo.readObject();
                  inOne.close();
                  inTwo.close();
              }
              catch(Exception ee){}
              if(����ɢ�б�.containsKey(name)){
                Dog stu=(Dog)����ɢ�б�.get(name);
                jtcolor.setText(stu.getColor());
                jtage.setText(stu.getAge());
              }
              String m="ȷ��Ҫɾ���ó���ļ�¼��";
              int ok=JOptionPane.showConfirmDialog(null,m,"ȷ��",
                 JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
              if(ok==JOptionPane.YES_OPTION){
                  ����ɢ�б�.remove(name);
                  try{
                      outOne=new FileOutputStream(file);
                      outTwo=new ObjectOutputStream(outOne);
                      outTwo.writeObject(����ɢ�б�);
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
                  String warning="�ó��ﲻ���ڣ�";
                  JOptionPane.showMessageDialog(null,warning,
                          "����",JOptionPane.WARNING_MESSAGE);
              }
          }
          else{
               String warning="����������";
               JOptionPane.showMessageDialog(null,warning,
                      "����",JOptionPane.WARNING_MESSAGE);
          }
       }
   }
    class ShowAct implements ActionListener{
       public void actionPerformed(ActionEvent e){
           new PetShow(file);
       }
   }
    class PetShow extends JDialog{
       Hashtable<String, Pet> ����ɢ�б�= null;
       JTextArea ��ʾ=null;
       FileInputStream inOne=null;
       ObjectInputStream inTwo=null;
       File file=null;
       @SuppressWarnings("unchecked")
	public PetShow(File file){
           super(new JFrame(),"��ʾ�Ի���");
           this.file=file;
           ��ʾ=new JTextArea(16,30);
           try{
               inOne=new FileInputStream(file);
               inTwo=new ObjectInputStream(inOne);
               ����ɢ�б�=(Hashtable<String, Pet>)inTwo.readObject();
               inOne.close();
               inTwo.close();
           }
           catch(Exception ee){}
           if(����ɢ�б�.isEmpty())��ʾ.append("Ŀǰ��û�г����¼��\n");
           else{
               ��ʾ.setText("���� ��ɫ ���� \n");
               for(Enumeration<Pet> enm=����ɢ�б�.elements();enm.hasMoreElements();){
                   Dog dog=(Dog)enm.nextElement();
                   
                   String str=dog.getName()+","+dog.getColor()+","+dog.getAge()+"\n";
                   ��ʾ.append(str);
               }
           }
           JScrollPane scroll=new JScrollPane(��ʾ);
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
