package me.glinde.blog.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 用户
 * 
 * @author glinde
 * @email 2512719869@qq.com
 * @date 2022-01-28 22:26:41
 */
@Data
@TableName("user")
public class UserEntity implements Serializable, UserDetails {
	private static final long serialVersionUID = 1L;

	@TableId
	private Integer id;

	private String username;

	private String password;

	private Integer state;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return state != 0;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return state == 1;
	}
}
