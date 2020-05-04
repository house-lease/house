package cn.bdqn.domain;

import org.springframework.stereotype.Component;

/**
 * 起租时间对象
 */
@Component
public class Start {

    //起租时间id
    private Integer id;

    //起租月份 字符串型
    private String startName;

    //起租月份 可计算类型
    private Integer startValue;

    //起租时间的状态 0正常 1删除'
    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName == null ? null : startName.trim();
    }

    public Integer getStartValue() {
        return startValue;
    }

    public void setStartValue(Integer startValue) {
        this.startValue = startValue;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}