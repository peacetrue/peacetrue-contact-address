package com.github.peacetrue.contactaddress;

import com.github.peacetrue.core.OperatorImpl;
import com.github.peacetrue.validation.constraints.multinotnull.MultiNotNull;
import lombok.*;

import javax.validation.constraints.PositiveOrZero;


/**
 * @author xiayx
 */
@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@MultiNotNull(propertyNames = {"id", "defaults"})
public class ContactAddressGet extends OperatorImpl<Long> {

    private static final long serialVersionUID = 0L;

    @PositiveOrZero
    private Long id;
    private Boolean defaults;

    public ContactAddressGet(Long id) {
        this.id = id;
    }
}
