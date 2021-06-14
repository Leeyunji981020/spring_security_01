package edu.bit.ex.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.bit.ex.mapper.EmpMapper;
import edu.bit.ex.mapper.MemberMapper;
import edu.bit.ex.vo.EmpUser;
import edu.bit.ex.vo.EmpVO;
import edu.bit.ex.vo.MemberUser;
import edu.bit.ex.vo.MemberVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

//principal.emp.sal
//이렇게 달고 싶으면 (확장하고 싶으면)
//UserDetailsService 에 대해서 자손이 구현해야 할게 있는데
//loadUserByUsername 이거 하나만 구현하면 된다.(폴리몰피즘)
//->리턴값이 UserDetails에 맞게끔 리턴값을 돌리게 되면 principal.emp.sal처럼
//확장 할 수 있게 된다.

@Log4j
@Service
public class EmpDetailsService implements UserDetailsService {

   @Setter(onMethod_ = @Autowired)
   private EmpMapper empMapper;

   @Override
   public UserDetails loadUserByUsername(String ename) throws UsernameNotFoundException {
      log.warn("Load User By EmpVO number: " + ename);
      
      //MemberVO에서 username객체를 가져온다.
      EmpVO vo = empMapper.getEmp(ename);

      log.warn("queried by EmpVO mapper: " + vo);

      return vo == null ? null : new EmpUser(vo);

   }
}