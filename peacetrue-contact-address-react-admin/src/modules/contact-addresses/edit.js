import React from 'react';
import {
    DateField,
    Edit,
    maxLength,
    minValue,
    NumberInput,
    ReferenceField, ReferenceInput,
    required, SelectInput,
    SimpleForm,
    TextField,
    TextInput
} from 'react-admin';

export const ContactAddressEdit = (props) => {
    console.info('ContactAddressEdit:', props);
    return (
        <Edit {...props} undoable={false}>
            <SimpleForm>
                <TextInput source="contactName" validate={[required(), maxLength(255)]} resettable/>
                <TextInput source="contactPhoneCode" validate={[required(), maxLength(255)]} resettable/>
                <ReferenceInput reference="regions" source="addressId" validate={[required(), minValue(0)]}>
                    <SelectInput optionText="name" resettable/>
                </ReferenceInput>
                <TextInput source="addressDetail" validate={[required(), maxLength(255)]} resettable fullWidth/>
                <ReferenceField reference="members" source="creatorId" link={'show'}>
                    <TextField source="username"/>
                </ReferenceField>
                <DateField source="createdTime" showTime/>
            </SimpleForm>
        </Edit>
    );
};
