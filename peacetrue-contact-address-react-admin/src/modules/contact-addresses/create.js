import React from 'react';
import {Create, maxLength, minValue, ReferenceInput, required, SelectInput, SimpleForm, TextInput} from 'react-admin';

export const ContactAddressCreate = (props) => {
    console.info('ContactAddressCreate:', props);
    return (
        <Create {...props}>
            <SimpleForm>
                <TextInput source="contactName" validate={[required(), maxLength(255)]} resettable/>
                <TextInput source="contactPhoneCode" validate={[required(), maxLength(255)]} resettable/>
                <ReferenceInput reference="regions" source="addressId" validate={[required(), minValue(0)]}>
                    <SelectInput optionText="name" resettable/>
                </ReferenceInput>
                <TextInput source="addressDetail" validate={[required(), maxLength(255)]} resettable fullWidth/>
            </SimpleForm>
        </Create>
    );
};
