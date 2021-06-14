package edu.bit.ex.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//principal.member.username 이런식으로 붙음
@Setter
@Getter
@ToString
public class EmpUser extends User {

   private EmpVO emp; //userHome.jsp에서 
   //principal.emp 의 emp임
      
      //기본적으로 부모의 생성자를 호출해야만 정상적으로 작동
   
   public EmpUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
       //super의 밑에 3개는 무조건 필수!
       super(username, password, authorities);
   }

   
   //생성자
   public EmpUser(EmpVO empVO) {

       //super의 밑에 3개는 무조건 필수!
      super(empVO.getEname(), empVO.getEmpno(), getAuth(empVO));

      this.emp = empVO;
   }

   // 유저가 갖고 있는 권한 목록
   // 생성자에서 함수를 불러오려면 static을 불러와야한다.
   // 왜??(알아야함 static)
   public static Collection<? extends GrantedAuthority> getAuth(EmpVO empVO) {

      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
      
         authorities.add(new SimpleGrantedAuthority(empVO.getAuthority()));
      
      return authorities;
   }
}