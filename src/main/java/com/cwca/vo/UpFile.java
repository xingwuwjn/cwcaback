package com.cwca.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: liforever
 * @Date: 2019/4/29 16:01
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpFile {
    /* 对应资料审核主键*/
    private String fileInfoId;
    /* 资料字典名称*/
    private String dictName;
    /* 资料字典code  暂时不用*/
    private String dictCode;
    /* 上传资料url*/
    private String fileUrl;
    /* 是否通过*/
    private Boolean isPass;
    /* 文件类型*/
    private String type;
    /* 备注*/
    private String note;

    public void setFileUrl(String pre, String fileUrl, HttpServletRequest request) {
        this.fileUrl = pre + fileUrl.replaceAll("\\\\", "/");
    }

    public static void main(String[] args) {
        String dd = "\\20190530\\31\\afc20424214f4dd894a3711c4fce30d7.jpg";
        System.out.println(dd.replaceAll("\\\\", "/"));
        String s = "/api/file/**".substring(0, "/api/file/**".lastIndexOf("/"));
        System.out.println(s);
    }
}
