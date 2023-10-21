package top.lilong.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/21 16:47
 * @注释
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Share {
 private Long id;
 private Long userId;
 private String title;
 private Boolean isOriginal;
 private String author;
 private String cover;

 private String summary;

 private Integer price;

 private String downloadUrl;

 private Integer buyCount;
 private Boolean showFlag;
 private String auditStatus;
 private String reason;

 @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
 private Date createTime;
 @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
 private Date updateTime;

}

