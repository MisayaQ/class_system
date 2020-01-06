package com.course.utils.feignDto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 通知实体类
 * @author David
 */
@Data
@Builder
public class MTNotificationSendReqDTO {

    private String updateCode;

    private String corpId;

    private boolean silent;
    /**
     * 通知标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 发送通道，全部通道为ALL, 这个分类应该是一个固定值,暂时写成ALL
     */
    private String channels;

    /**
     * 发送人id，非必须
     */
    private String senderId;

    /**
     * 发送人名称
     */
    private String senderName;

    /**
     * 外部id，对应各自服务中的id，需要保证同一个通知分类下的唯一性，可以用来统计和修改已读状态
     * 用来存咱自己的发送id
     */
    private String targetId;

    /**
     * 通知分类列表，这个值应该是一个固定值 TASK
     */
    private List<String> categoryCodes;

    /**
     * receiverIds
     */
    private List<String> receiverIds;

    /**
     * 自定义属性map，key和value都是字符串类型
     */
    private Map<String, String> properties;

    @Override
    public String toString() {
        return "MTNotificationSendReqDTO{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", channels='" + channels + '\'' +
                ", senderId='" + senderId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", targetId='" + targetId + '\'' +
                ", categoryCodes=" + categoryCodes +
                ", receiverIds=" + receiverIds +
                ", properties=" + properties +
                '}';
    }
}
