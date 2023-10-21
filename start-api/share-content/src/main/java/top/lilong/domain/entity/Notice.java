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
 * @Date 2023/10/21 16:33
 * @注释
 */
 @AllArgsConstructor
 @NoArgsConstructor
 @Builder
 @Data
 public class Notice {
  private Long id;

  private String content;

  private Boolean showFlag;

  @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

 }

