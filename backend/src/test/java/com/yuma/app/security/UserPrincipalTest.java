package com.yuma.app.security;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import com.yuma.app.document.Admin;
import com.yuma.app.document.Role;

public class UserPrincipalTest {
	
	@Test
	public void testCreateUserPrincipal() {
		
		Admin consumer = prepareUser();
		
		UserPrincipal userPrincipal = UserPrincipal.create(consumer);

		Assert.assertEquals(userPrincipal.getUserId(), consumer.getUserId());
		Assert.assertEquals(userPrincipal.getEmail(), consumer.getEmail());
		Assert.assertEquals(userPrincipal.getPassword(), consumer.getPassword());
		Assert.assertEquals(userPrincipal.getFirstName(), consumer.getFirstName());
		Assert.assertEquals(userPrincipal.getLastName(), consumer.getLastName());
		
	}
	
	private Admin prepareUser() {
		Role role = new Role();
		role.setRole("ADMIN");
		
		Admin admin = new Admin();
		admin.setUserId("123");
		admin.setEmail("123");
		admin.setPassword("123");
		admin.setFirstName("123");
		admin.setLastName("123");
		admin.setRoles(new HashSet<Role>(){
			{
				add(role);
			}
		});
		return admin;
	}

}
