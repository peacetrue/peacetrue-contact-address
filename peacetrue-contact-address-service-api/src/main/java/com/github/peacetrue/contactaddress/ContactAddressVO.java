package com.github.peacetrue.contactaddress;

import com.github.peacetrue.core.IdCapable;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author xiayx
 */
@Data
public class ContactAddressVO implements Serializable, IdCapable<Long> {

    private static final long serialVersionUID = 0L;

    /** 主键 */
    private Long id;
    /** 联系人姓名 */
    private String contactName;
    /** 联系人手机号 */
    private String contactPhoneCode;
    /** 地区. 主键 */
    private Long addressId;
    /** 详细地址 */
    private String addressDetail;
    /** 上个联系地址. 0 表示新建，后续修改关联之前的联系地址主键 */
    private Long sourceId;
    /** 创建者. 主键 */
    private Long creatorId;
    /** 创建时间 */
    private LocalDateTime createdTime;
    /** 最近修改者. 主键 */
    private Long modifierId;
    /** 最近修改时间 */
    private LocalDateTime modifiedTime;
}
