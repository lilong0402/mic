package top.lilong.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import top.lilong.domain.entity.MidUserShare;
import top.lilong.domain.entity.Share;
import top.lilong.mapper.MidUserShareMapper;
import top.lilong.mapper.ShareMapper;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/21 16:48
 * @注释
 */
@Service
public class ShareService {
 @Resource
 private ShareMapper shareMapper;
 @Resource
 private MidUserShareMapper midUserMapper;
//
// @Resource
// private UserService userService;
//
// @Resource
// private RocketMQTemplate rocketMQTemplate;

 /**
  * 查询某个用户首页可见的资源列表
  *
  * @param title  标题
  * @param userId 用户id
  * @return
  */
 public List<Share> getList(String title, Integer pageNo, Integer pageSize, Long userId) {
  //构造查询条件
  LambdaQueryWrapper<Share> wrapper = new LambdaQueryWrapper<>();
  //安装id降序查询所有数据
  wrapper.orderByDesc(Share::getId);
  //如果关键字不空，则加上模糊查询条件，结束结果即所有数据
  if (StrUtil.isNotEmpty(title)) {
   wrapper.like(Share::getTitle, title);
  }
  //过滤出所有已经通过审核的数据并显示数据
  wrapper.eq(Share::getAuditStatus, "PASS")
          .eq(Share::getShowFlag, true);
  //內置的分页对象
  Page<Share> page = Page.of(pageNo, pageSize);
  //执行按条件查询
  List<Share> shares = shareMapper.selectList(page, wrapper);
  //处理后的Share数据列表
  List<Share> shareDeal = null;
  //1.如果用户未登录，那么downloadUrl全部设为nuLl
  if (userId == null) {
   shares.stream()
           .peek(share -> share.setDownloadUrl(null))
           .collect(Collectors.toList());
  }
  //如果用户已经登录了，那么查询mid_user_share,如果没有share的download也设为null
  //只有自己分享的资源才能看到下载链接，否则显示未兑换
  else {
   shareDeal = shares.stream().peek(
           share -> {
            MidUserShare midUserShare = midUserMapper.selectOne(new QueryWrapper<MidUserShare>()
                    .lambda()
                    .eq(MidUserShare::getUserId, userId)
                    .eq(MidUserShare::getShareId, share.getId())
            );
            if (midUserShare == null) {
             share.setDownloadUrl(null);
            }

           }
   ).collect(Collectors.toList());
  }
  return shareDeal;
 }

// public ShareResp findById(Long shareId) {
//  Share share = shareMapper.selectById(shareId);
//  CommonResp<User> commonResp =
//          userService.getUser(share.getUserId());
//  return ShareResp.builder().share(share)
//          .nickname(commonResp.getData().getNickname())
//          .avatarUrl(commonResp.getData().getAvatarUrl())
//          .build();
// }
//
// public Share exchange(ExchangeDTO exchangeDTO) throws IllegalAccessException {
//  Long userId = exchangeDTO.getUserId();
//  Long shareId = exchangeDTO.getShareId();
//  //1.查询id 根据id 查询share,校验需要兑换的资源是否存在
//  Share share = shareMapper.selectById(shareId);
//  if (share == null) {
//   throw new IllegalAccessException("该分享不存存在");
//  }
//
//  //2.如果当前用户已经兑换该分享，则直接返回该分享(不需要扣积分)
//  MidUserShare midUserShare = midUserMapper.selectOne(
//          new QueryWrapper<MidUserShare>().lambda()
//                  .eq(MidUserShare::getUserId, userId)
//                  .eq(MidUserShare::getShareId, shareId)
//  );
//
//  if (midUserShare != null) {
//   return share;
//  }
//  //3. 看用户积分是否足够
//  CommonResp<User> commonResp = userService.getUser(userId);
//  User user = commonResp.getData();
//  //看这条资源需要的积分
//  Integer price = share.getPrice();
//  if (price > user.getBonus()) {
//   throw new IllegalAccessException("用户积分不够!");
//  }
//  //4.修改积分(+1 就是负值扣分)
//  userService.updateBonus(UserAddBonusMsgDTO.builder()
//          .userId(userId)
//          .bonus(price * -1)
//          .build());
//  //5. 向mid_user_share 表中插入一条数据，让这个用户对资源有下载权限
//  midUserMapper.insert(MidUserShare.builder().userId(userId).shareId(shareId).build());
//  return share;
// }
//
//
// /**
//  * 投稿
//  *
//  * @param shareRequestDTO
//  * @return
//  */
// public int contribute(ShareRequestDTO shareRequestDTO) {
//  Share share = Share.builder()
//          .isOriginal(shareRequestDTO.getIsOriginal())
//          .author(shareRequestDTO.getAuthor())
//          .price(shareRequestDTO.getPrice())
//          .downloadUrl(shareRequestDTO.getDownloadUrl())
//          .summary(shareRequestDTO.getSummary())
//          .buyCount(0)
//          .title(shareRequestDTO.getTitle())
//          .userId(shareRequestDTO.getUserId())
//          .cover(shareRequestDTO.getCover())
//          .createTime(new Date())
//          .updateTime(new Date())
//          .showFlag(false)
//          .auditStatus("NOT_UET")
//          .reason("未审核")
//          .build();
//  return shareMapper.insert(share);
// }
//
// /**
//  * 我的投稿
//  *
//  * @param pageNo
//  * @param pageSize
//  * @param userId
//  * @return
//  */
// public List<Share> myContribute(Integer pageNo, Integer pageSize, Long userId) {
//  LambdaQueryWrapper<Share> wrapper = new LambdaQueryWrapper<>();
//  wrapper.orderByDesc(Share::getId);
//  wrapper.eq(Share::getUserId, userId);
//  Page<Share> page = Page.of(pageNo, pageSize);
//  return shareMapper.selectList(page, wrapper);
// }
//
//
// /**
//  * 查询待审核状态的shares列表
//  *
//  * @return
//  */
// public List<Share> querySharesNotYet() {
//  LambdaQueryWrapper<Share> wrapper = new LambdaQueryWrapper<>();
//  wrapper.orderByDesc(Share::getId);
//  wrapper.eq(Share::getShowFlag, false)
//          .eq(Share::getAuditStatus, "NOT_YET");
//  return shareMapper.selectList(wrapper);
// }
//
// /**
//  * 审核
//  *
//  * @return
//  */
// public Share auditById(Long id, ShareAuditDTO shareAuditDTO) throws IllegalAccessException {
//// 1．查询 share是否存在，不存在或者当前的 audit_status != NOT_YET，那么抛异常
//  Share share = shareMapper.selectById(id);
//  if (share == null){
//   throw new IllegalAccessException("参数异常!该分享不存在");
//  }
//  //2.审核资源，将状态改为PASS 或 REJECT，更新原因和是否发布显示
//  share.setAuditStatus(shareAuditDTO.getAuditStatusEnum().toString());
//  share.setReason(shareAuditDTO.getReason());
//  share.setShowFlag(shareAuditDTO.getShowFlag());
//  LambdaQueryWrapper<Share> wrapper = new LambdaQueryWrapper<>();
//  wrapper.eq(Share::getId,id);
////3．向mid_user 插入一条数据，分享的作者通过审核后，默认拥有了下载权限
//  this.midUserMapper.insert(
//          MidUserShare.builder()
//                  .userId(share.getUserId())
//                  .shareId(id)
//                  .build()
//  );
//  //
//  if (AuditStatusEnum.PASS.equals(shareAuditDTO.getAuditStatusEnum())){
//   this.rocketMQTemplate.convertAndSend(
//           "add-bonus",
//           UserAddBonusMsgDTO.builder()
//                   .userId(share.getUserId())
//                   .bonus(50)
//                   .build()
//   );
//  }
//  return share;
// }
//
// public List<Share> exchangeListByUserId(Long userId){
//  //2.根据shareId找出兑换过的share
//  List<Share> shares = shareMapper.selectExchangeByUserId(userId);
//  return shares;
// }
}

