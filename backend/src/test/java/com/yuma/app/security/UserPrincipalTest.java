package com.yuma.app.security;

import com.yuma.app.document.Role;
import com.yuma.app.document.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class UserPrincipalTest {
	
	@Test
	public void testCreateUserPrincipal() {
		
		User user = prepareUser();
		
		UserPrincipal userPrincipal = UserPrincipal.create(user);

		Assert.assertEquals(userPrincipal.getUserId(), user.getUserId());
		Assert.assertEquals(userPrincipal.getEmail(), user.getEmail());
		Assert.assertEquals(userPrincipal.getPassword(), user.getPassword());
		Assert.assertEquals(userPrincipal.getFirstName(), user.getFirstName());
		Assert.assertEquals(userPrincipal.getLastName(), user.getLastName());
		
	}
	
	User prepareUser() {
		
		Role role = new Role();
		role.setRole("ADMIN");
		
		User user = new User();
		user.setUserId("123");
		user.setEmail("123");
		user.setPassword("123");
		user.setFirstName("123");
		user.setLastName("123");
		user.setRoles(new HashSet<Role>(){
			{
				add(role);
			}
		});
		return user;
	}

}
