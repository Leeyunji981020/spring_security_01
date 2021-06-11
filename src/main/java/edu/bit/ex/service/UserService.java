package edu.bit.ex.service;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Insert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.bit.ex.mapper.UserMapper;
import edu.bit.ex.vo.UserVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Log4j
@NoArgsConstructor
@Service

public class UserService {
	
	   @Autowired
	   private BCryptPasswordEncoder passEncoder;

	   @Inject
	   private UserMapper userMapper;
	   
	   @Transactional(rollbackFor = Exception.class)
	   public void addUser(UserVO userVO) {
		   
	      String password = userVO.getPassword();
	      String encode = passEncoder.encode(password);

	      userVO.setPassword(encode);

	      userMapper.insertUser(userVO);
	      userMapper.insertAuthorities(userVO);
	   }
}
