package com.example.dllo.yoho.GreenDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by dllo on 16/12/9.
 */
@Entity
public class CollectBean {

    @Id
    private Long id;
    String img;
    String boty;

    @Generated(hash = 1476132729)
    public CollectBean(Long id, String img, String boty) {
        this.id = id;
        this.img = img;
        this.boty = boty;
    }

    @Generated(hash = 420494524)
    public CollectBean() {
    }

    public Long getId() {
        return id;
    }

    public CollectBean setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImg() {
        return img;
    }

    public CollectBean setImg(String img) {
        this.img = img;
        return this;
    }

    public String getBoty() {
        return boty;
    }

    public CollectBean setBoty(String boty) {
        this.boty = boty;
        return this;
    }
}
