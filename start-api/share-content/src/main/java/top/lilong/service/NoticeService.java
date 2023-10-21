package top.lilong.service;

import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @Author 李龙
 * @Date 2023/10/21 16:36
 * @注释
 */
@Service
public class NoticeService {
    @Resource
    private NoticeMapper noticeMapper;

    public Notice getLatest(){
        LambdaQueryWrapper<Notice> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Notice::getShowFlag,1);
        wrapper.orderByDesc(Notice::getId);
        List<Notice> noticeList = noticeMapper.selectList(wrapper);
        return noticeList.get(0);
    }
}
