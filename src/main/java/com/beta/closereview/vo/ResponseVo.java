package com.beta.closereview.vo;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseVo<T> {
    private Integer status = 0;
    private String msg = "success";
    private T data = null;

    public ResponseVo() {
    }

    public ResponseVo(T data) {
        this.data = data;
    }

    public ResponseVo(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }


}
