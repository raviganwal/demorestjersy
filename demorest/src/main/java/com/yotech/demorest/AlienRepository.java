package com.yotech.demorest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class AlienRepository {

	

	Connection con=null;
	public AlienRepository() {
		String url="jdbc:mysql://localhost:3306/restdb";
		String username="root";
		String password="1234";
//		String driver = "com.mysql.jdbc.Driver";
		String driver = "com.mysql.cj.jdbc.Driver";
		
try {
	Class.forName(driver);
	con =DriverManager.getConnection(url,username,password);
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
	public List<Alien> getAliens(){
		List<Alien> aliens= new ArrayList<>();
		String sql ="Select * from alien";
		try {
			Statement st = con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));
				aliens.add(a);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aliens;
	}
	
	
public Alien getAlien(int id){
	
	String sql ="Select * from alien where id="+id;
	Alien a = new Alien();

	try {
		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		if (rs.next()) {
			a.setId(rs.getInt(1));
			a.setName(rs.getString(2));
			a.setPoints(rs.getInt(3));
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

return a;
	}
public void create(Alien a1) {
	// TODO Auto-generated method stub
	String sql ="insert into alien values(?,?,?)";

	try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, a1.getId());
		st.setString(2, a1.getName());
		st.setInt(3, a1.getPoints());

		st.executeUpdate();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void update(Alien a1) {
	// TODO Auto-generated method stub
//	update alien set name='java',points=67 where id=1
	String sql ="update alien set name=?, points=? where id=?";

	try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, a1.getName());
		st.setInt(2, a1.getPoints());
		st.setInt(3, a1.getId());

		st.executeUpdate();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(e);
	}
}

public void delete(int id) {
	// TODO Auto-generated method stub
//	update alien set name='java',points=67 where id=1
	String sql ="delete from alien where id=?";

	try {
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		st.executeUpdate();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(e);
	}
}
}
