package top.lilong.controller;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import top.lilong.domain.entity.Notice;
import top.lilong.domain.entity.Share;
import top.lilong.resp.CommonResp;
import top.lilong.service.NoticeService;
import top.lilong.service.ShareService;
import top.lilong.util.JwtUtil;

import java.util.List;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/21 16:39
 * @注释
 */
@RestController
@RequestMapping("/share")
@Slf4j
@Tag(name = "ShareController",description = "内容接口")
public class ShareController {

 @Resource
 private NoticeService noticeService;

 @Resource
 private ShareService shareService;
 //定义每页最多的数据量，以防前蝠定义传递超大参数，造成页面数据量过大
 private final int MAX = 100;


 @GetMapping("/notice")
 public CommonResp<Notice> getLatestNotice() {
  CommonResp<Notice> commonResp = new CommonResp<>();
  commonResp.setData(noticeService.getLatest());
  return commonResp;
 }

 @GetMapping("/list")
 public CommonResp<List<Share>> getShareList(
         @RequestParam(required = false) String title,
         @RequestParam(required = false, defaultValue = "1") Integer pageNo,
         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
         @RequestParam(value = "token", required = false) String token
 ) {
  if (pageSize > MAX) {
   pageSize = MAX;
  }
  long userId = 2;
  if (token != null) {
   userId = getUserFromToken(token);
  }
  CommonResp<List<Share>> commonResp = new CommonResp<>();
//  List<Share> list = shareService.getList(title, pageNo, pageSize, userId);
//  commonResp.setData(list);
  return commonResp;

 }

 private long getUserFromToken(String token) {
  log.info(">>>>>>>>> token" + token);
  long userId = 0;
  String noToken = "no-token";
  if (!noToken.equals(token)) {
   JSONObject jsonObject = JwtUtil.getJSONObject(token);
   log.info("解析到 token 的 json数据为：{}", jsonObject);
   userId = Long.parseLong(jsonObject.get("id").toString());
  } else {
   log.info("没有token");
  }
  return userId;
 }

// @GetMapping("/{id}")
// public CommonResp<ShareResp> getShareById(@PathVariable long id) {
//  ShareResp shareResp = shareService.findById(id);
//  CommonResp<ShareResp> commonResp = new CommonResp<>();
//  commonResp.setData(shareResp);
//  return commonResp;
// }
//
// @PostMapping("/exchange")
// public CommonResp<Share> exchange(@RequestBody ExchangeDTO exchangeDTO) throws IllegalAccessException {
//  System.out.println(exchangeDTO);
//  CommonResp<Share> commonResp = new CommonResp<>();
//  commonResp.setData(shareService.exchange(exchangeDTO));
//  return commonResp;
// }
//
// @PostMapping("/contribute")
// public int contributeShare(@RequestBody ShareRequestDTO shareRequestDTO,
//                            @RequestHeader(value = "token", required = false) String token) {
//  long userId = getUserFromToken(token);
//  shareRequestDTO.setUserId(userId);
//  System.out.println(shareRequestDTO);
//  return shareService.contribute(shareRequestDTO);
// }
//
// @GetMapping("/my-contribute")
// public CommonResp<List<Share>> myContributeShare(
//         @RequestParam(required = false, defaultValue = "1") Integer pageNo,
//         @RequestParam(required = false, defaultValue = "3") Integer pageSize,
//         @RequestHeader(value = "token", required = false) String token) {
//  if (pageSize > MAX) {
//   pageSize = MAX;
//  }
//  long userId = getUserFromToken(token);
//  CommonResp<List<Share>> commonResp = new CommonResp<>();
//  commonResp.setData(shareService.myContribute(pageNo, pageSize, userId));
//  return commonResp;
// }
//
// @GetMapping("/myexchange")
// public CommonResp<List<Share>> exchangeListByUserId(@RequestHeader(value = "token", required = false) String token){
//  long userId = getUserFromToken(token);
//  List<Share> shares = shareService.exchangeListByUserId(userId);
//  CommonResp<List<Share>> commonResp = new CommonResp<>();
//  commonResp.setData(shares);
//  return commonResp;
// }
}
