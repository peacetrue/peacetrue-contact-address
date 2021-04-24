package com.github.peacetrue.contactaddress;

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
public class ContactAddressGet extends OperatorImpl<Long> {

    private static final long serialVersionUID = 0L;

    @NotNull
    private Long id;

}
