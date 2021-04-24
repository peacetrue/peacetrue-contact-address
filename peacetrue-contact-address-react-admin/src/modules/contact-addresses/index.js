import React from "react";
import {Resource} from "react-admin";

import {ContactAddressList} from './list';
import {ContactAddressCreate} from './create';
import {ContactAddressEdit} from './edit';
import {ContactAddressShow} from './show';

export const ContactAddress = {
    list: ContactAddressList,
    create: ContactAddressCreate,
    edit: ContactAddressEdit,
    show: ContactAddressShow
};
const ContactAddressResource = <Resource name="contact-addresses" {...ContactAddress} />;
export default ContactAddressResource;
