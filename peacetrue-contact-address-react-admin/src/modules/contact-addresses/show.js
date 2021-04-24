import React from 'react';
import {DateField, ReferenceField, Show, SimpleShowLayout, TextField} from 'react-admin';

export const ContactAddressShow = (props) => {
    console.info('ContactAddressShow:', props);
    return (
        <Show {...props}>
            <SimpleShowLayout>
                <TextField source="contactName"/>
                <TextField source="contactPhoneCode"/>
                <TextField source="addressId"/>
                <TextField source="addressDetail"/>
                <ReferenceField reference="members" source="creatorId" link="show">
                    <TextField source="username"/>
                </ReferenceField>
                <DateField source="createdTime" showTime/>
                <ReferenceField reference="members" source="modifierId" link="show">
                    <TextField source="username"/>
                </ReferenceField>
                <DateField source="modifiedTime" showTime/>
            </SimpleShowLayout>
        </Show>
    );
};
