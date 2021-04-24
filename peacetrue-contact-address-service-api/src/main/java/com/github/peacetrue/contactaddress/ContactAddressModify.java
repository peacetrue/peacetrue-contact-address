package com.github.peacetrue.contactaddress;

import com.github.peacetrue.core.IdCapable;
import com.github.peacetrue.core.OperatorImpl;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContactAddressModify extends OperatorImpl<Long> implements IdCapable<Long> {

    private static final long serialVersionUID = 0L;

    /** 主键 */
    @NotNull
    private Long id;
    /** 联系人姓名 */
    @Size(min = 1, max = 255)
    private String contactName;
    /** 联系人手机号 */
    @Size(min = 1, max = 255)
    private String contactPhoneCode;
    /** 地区. 主键 */
    private Long addressId;
    /** 详细地址 */
    @Size(min = 1, max = 255)
    private String addressDetail;
    /** 上个联系地址. 0 表示新建，后续修改关联之前的联系地址主键 */
    private Long sourceId;

}
