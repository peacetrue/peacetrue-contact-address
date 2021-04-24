package com.github.peacetrue.contactaddress;

import com.github.peacetrue.core.IdCapable;
import com.github.peacetrue.core.OperatorImpl;
import lombok.*;

import javax.validation.constraints.NotNull;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContactAddressSetDefaults extends OperatorImpl<Long> implements IdCapable<Long> {

    private static final long serialVersionUID = 0L;

    /** 主键 */
    @NotNull
    private Long id;

}
