package com.nb.manager.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户轨迹实体类
 * zhangqiang
 */
@Data
@Accessors(chain = true)
@TableName("user_trajectory")
public class UserTrajectory {
    //用户id
    private String userId;
    //操作类型
    private String operationModule;
    //操作内容
    private String operationContent;
    //操作时间
    private Timestamp operationDate;

    public String userId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOperationModule() {
        return operationModule;
    }

    public void setOperationModule(String operationModule) {
        this.operationModule = operationModule;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }
}
