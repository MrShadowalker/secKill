package cn.monitor4all.miaoshadao.dao;

import lombok.Data;

/**
 * 用户实体类
 *
 * @author Shadowalker
 */
@Data
public class User {

    private Long id;

    // 用户姓名
    private String userName;

    public User(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public User() {
        super();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append("]");
        return sb.toString();
    }
}