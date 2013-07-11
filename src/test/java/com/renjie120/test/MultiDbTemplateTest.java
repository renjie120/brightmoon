package com.renjie120.test;

import java.sql.SQLException;

import org.junit.Test;

import brightmoon.jdbc.MultiDbTool;

public class MultiDbTemplateTest { 
	@Test
	public void dbTest() throws SQLException {
		MultiDbTool db = new MultiDbTool();
        int count = db.queryForInt("report","select count(1) from Process_Define");
        System.out.println(count);
        
        int count2 = db.queryForInt("money","select count(1) from money_detail_t");
        System.out.println(count2);
	}
}