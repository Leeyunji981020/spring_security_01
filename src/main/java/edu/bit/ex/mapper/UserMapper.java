package edu.bit.ex.mapper;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.bit.ex.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;


public interface UserMapper {
	   @Insert("insert into users(username,password,enabled) values(#{username},#{password},#{enabled})")
	   public int insertUser(UserVO userVO);

	   @Insert("insert into AUTHORITIES (username,AUTHORITY) values(#{username},'ROLE_USER')")
	   public void insertAuthorities(UserVO UserVO);
	   
	   @Delete("delete from users")
	   public void deleteUsers();
	   
	   @Delete("delete from AUTHORITIES")
	   public void deleteAuthorities();
}
