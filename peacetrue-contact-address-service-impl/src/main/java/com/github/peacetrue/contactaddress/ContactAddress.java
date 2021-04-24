package com.github.peacetrue.contactaddress;

import com.github.peacetrue.core.CreateModify;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 联系地址实体类
 *
 * @author xiayx
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContactAddress implements Serializable, com.github.peacetrue.core.Id<Long>, CreateModify<Long> {

    private static final long serialVersionUID = 0L;

    /** 主键 */
    @Id
    private Long id;
    /** 联系人姓名 */
    private String contactName;
    /** 联系人手机号 */
    private String contactPhoneCode;
    /** 地区. 主键 */
    private Long addressId;
    /** 详细地址 */
    private String addressDetail;
    /** 默认地址 */
    private Boolean defaults;
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
