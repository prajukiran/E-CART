package com.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.ResultSet;

public class Test {
	
	Scanner s=new Scanner(System.in);
	
	public void sell(Connection con) throws Exception {
		
		System.out.println("Enter id here:  ");
		int id=s.nextInt();
		System.out.println("Enter product name:  ");
		String nm=s.next();
		System.out.println("Enter price:  ");
		int price=s.nextInt();
		System.out.println("Enter Category");
		String cat=s.next();
		
		String insertData="insert into product values(?,?,?,?)";
		
		PreparedStatement psi=con.prepareStatement(insertData);
		
		psi.setInt(1, id);
		psi.setString(2, nm);
		psi.setInt(3, price);
		psi.setString(4, cat);
		
		psi.execute();
		psi.close();
		System.out.println("data inserted to the database");
	}
	
	public void buy (Connection con) throws Exception {
		String retrive="select * from product";
		
		PreparedStatement ps=con.prepareStatement(retrive);
		
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("product id: "+rs.getInt(1));
			System.out.println("product name: "+rs.getString(2));
			System.out.println("product price: "+rs.getInt(3));
			System.out.println("product category: "+rs.getString(4));
			System.out.println("____________________________________");
		}
		
		System.out.println("Enter id of product you want to buy");
		int i=s.nextInt();
		
		String retriveSingle="select id from product";
		
		PreparedStatement psi=con.prepareStatement(retriveSingle);
		
		rs=psi.executeQuery();
		
		boolean flag=false;
		
		while(rs.next()) {
			
			if(i==rs.getInt(1)) {
				String deleteData="delete from product where id='"+i+"'";
				Statement smt=con.createStatement();
				smt.execute(deleteData);
				flag=true;
				break;
			}
		}
		
		if(flag) {
			System.out.println("Congrats! you added one product");
		}else {
			System.out.println("you entered wrong id here");
		}
	}
	
	public void update (Connection con) throws Exception {
		String retrive="select * from product";
		PreparedStatement ps=con.prepareStatement(retrive);
		
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("product id: "+rs.getInt(1));
			System.out.println("product name: "+rs.getString(2));
			System.out.println("product price: "+rs.getInt(3));
			System.out.println("product category:"+rs.getString(4));
			System.out.println("______________________________________");
		}
			System.out.println("Enter id of product you want to update");
			int i=s.nextInt();
			
			String retriveSingle="select id from product";
			PreparedStatement psi=con.prepareStatement(retriveSingle);
			
			rs=psi.executeQuery();
			
			boolean flag=false;
			
			while(rs.next()) {
				if(i==rs.getInt(1)) {
					
					System.out.println("Enter what you want to update");
					System.out.println("1.product name\n2.product price\n3.product Category");
					System.out.println("enter your choice");
					int ch=s.nextInt();
					
					switch(ch) {
					
					case 1 :
						System.out.println("enter new product name you want to update ");
						String nam=s.next();
						
						String updateName="update product set pname='"+nam+"' where id='"+i+"'";
						Statement smt=con.createStatement();
						smt.execute(updateName);
						flag=true;
						break;
						
					case 2 : 
						System.out.println("enter new price of product you want to update ");
						int pric=s.nextInt();
						
						String updatePrice="update product set price='"+pric+"' where id='"+i+"'";
						Statement smt1=con.createStatement();
						smt1.execute(updatePrice);
						flag=true;
						break;
						
					case 3 : 
						System.out.println("enter new product category you want to update");
						String cat=s.next();
						
						String updateCategory="update product set category='"+cat+"' where id='"+i+"'";
						Statement smt2=con.createStatement();
						smt2.execute(updateCategory);
						flag=true;
						break;
						
						default: System.out.println("exit");
					}
							
					
				}
				
			}

			if(flag) {
				System.out.println("you updated one product");
			}else {
				System.out.println("you enterd wrong id here");
			}
			
	}
	
	public void retrive (Connection con) throws Exception {
		System.out.println("what you want to retrive from database??");
		
		System.out.println("1.product id\n2.product name.\n3.product price\n4.product category\n5.whole products information");
		
		System.out.println("enter your choice");
		int ch=s.nextInt();
		switch(ch) {
		case 1:
		    System.out.println("enter product id you want to see details");
		    int idn=s.nextInt();
			String retriveId="select * from product where id='"+idn+"'";
			Statement smt1=con.createStatement();
			ResultSet rs1=smt1.executeQuery(retriveId);
			boolean flag1=false;
			while(rs1.next()) {
			System.out.println(rs1.getInt(1));
			System.out.println(rs1.getString(2));
			System.out.println(rs1.getInt(3));
			System.out.println(rs1.getString(4));
			System.out.println("_____________________________________");
			flag1=true;
			}
			if(flag1) {
			   }
				else {
					System.out.println("unable to find id you eneterd");
				}
			break;
			
		case 2:
			System.out.println("enter product name you want to see details");
		    String nm=s.next();
			String retriveName="select * from product where pname='"+nm+"'";
			Statement smt2=con.createStatement();
			ResultSet rs2=smt2.executeQuery(retriveName);
			boolean flag2=false;
			while(rs2.next()) {
			System.out.println(rs2.getInt(1));
			System.out.println(rs2.getString(2));
			System.out.println(rs2.getInt(3));
			System.out.println(rs2.getString(4));
			System.out.println("_____________________________");
			flag2=true;
			}
			if(flag2) {
			   }
				else {
					System.out.println("unable to find name you eneterd");
				}
			break;
		
		case 3:
			System.out.println("enter product price you want to see details");
		    int pricenew=s.nextInt();
			String retrivePrice="select * from product where price='"+pricenew+"'";
			Statement smt3=con.createStatement();
			ResultSet rs3=smt3.executeQuery(retrivePrice);
			boolean flag3=false;
				while(rs3.next()) {
			System.out.println(rs3.getInt(1));
			System.out.println(rs3.getString(2));
			System.out.println(rs3.getInt(3));
			System.out.println(rs3.getString(4));
			System.out.println("_____________________________");
			flag3=true;
			     }
				   if(flag3) {
				   }
					else {
						System.out.println("unable to find amount you eneterd");
					}
			 break;
		
		case 4:
			System.out.println("enter product Category you want to see details");
		    String cat=s.next();
			String retrivecat="select * from product where category='"+cat+"'";
			Statement smt4=con.createStatement();
			ResultSet rs4=smt4.executeQuery(retrivecat);
			boolean flag4=false;
			while(rs4.next()) {
			System.out.println(rs4.getInt(1));
			System.out.println(rs4.getString(2));
			System.out.println(rs4.getInt(3));
			System.out.println(rs4.getString(4));
			System.out.println("_____________________________");
			flag4=true;
			}
			if(flag4) {
			   }
				else {
					System.out.println("unable to find category you eneterd");
				}
			break;
			
		case 5:
			
		String retriveWhole="select * from product";
		PreparedStatement ps5=con.prepareStatement(retriveWhole);
		
		ResultSet rs5=ps5.executeQuery();
		
		while(rs5.next()) {
			System.out.println("product id: "+rs5.getInt(1));
			System.out.println("product name: "+rs5.getString(2));
			System.out.println("product price: "+rs5.getInt(3));
			System.out.println("product category:"+rs5.getString(4));
			System.out.println("______________________________________");
		}
		break;
	}
}
	
	
	public static void main(String args[]) throws Exception {
		Scanner s=new Scanner(System.in);
		
		Test t=new Test();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cashify","root","Praju@123");
		
		while(true) {
			System.out.println("Menu.......................");
			
			System.out.println("1.Sell your product\n2.Buy your product\n3.update your product\n4.Retrive");
			
			System.out.println("enter your choice");
			int ch=s.nextInt();
			
			switch(ch) {
			
			case 1: t.sell(con);
			break;
			
			case 2: t.buy(con);
			break;
			
			case 3: t.update(con);
			break;
			
			case 4: t.retrive(con);
			break;
			
			default :System.out.println("exit");
			}
			
		}
	}
}
