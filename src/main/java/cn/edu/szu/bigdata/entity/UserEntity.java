package cn.edu.szu.bigdata.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by longhao on 2017/8/31.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserEntity {
    private String username;
    private String password;//md5加密后的密码
    private String email;
    private String phone;
    private String avatar;//个人头像
}
