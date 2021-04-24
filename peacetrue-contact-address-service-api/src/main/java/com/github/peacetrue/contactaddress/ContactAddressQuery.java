package com.github.peacetrue.contactaddress;

import com.github.peacetrue.core.OperatorImpl;
import com.github.peacetrue.core.Range;
import lombok.*;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContactAddressQuery extends OperatorImpl<Long> {

    public static final ContactAddressQuery DEFAULT = new ContactAddressQuery();

    private static final long serialVersionUID = 0L;

    /** 主键 */
    private Long[] id;
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
    private Range.LocalDateTime createdTime = Range.LocalDateTime.DEFAULT;
    /** 最近修改者. 主键 */
    private Long modifierId;
    /** 最近修改时间 */
    private Range.LocalDateTime modifiedTime = Range.LocalDateTime.DEFAULT;

    public ContactAddressQuery(Long[] id) {
        this.id = id;
    }

}
