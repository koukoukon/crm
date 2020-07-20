package me.hibiki.crm.util;

import java.util.List;

/**
 * @author 高弘昆
 * @date 2020/7/17 22:08
 */

public class ResultEntity<T> {
    private Integer code;
    private String message;
    private List<T> rows;
    private Long total;

    public Integer getCode() {
        return code;
    }

    public ResultEntity<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultEntity<T> message(String message) {
        this.message = message;
        return this;
    }

    public List<T> getRows() {
        return rows;
    }

    public ResultEntity<T> rows(List<T> rows) {
        this.rows = rows;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public ResultEntity<T> total(Long total) {
        this.total = total;
        return this;
    }
}
