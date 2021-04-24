import React from 'react';
import {
    Datagrid,
    DateField,
    DateInput,
    EditButton,
    Filter,
    List,
    ReferenceField,
    ReferenceInput,
    SelectInput,
    TextField,
    TextInput
} from 'react-admin';

const Filters = (props) => (
    <Filter {...props}>
        <TextInput source="contactName" allowEmpty alwaysOn resettable/>
        <TextInput source="contactPhoneCode" allowEmpty alwaysOn resettable/>
        <ReferenceInput reference="regions" source="addressId" allowEmpty alwaysOn>
            <SelectInput optionText="name" resettable/>
        </ReferenceInput>
        {/*<TextInput source="addressDetail" allowEmpty alwaysOn resettable/>*/}
        <DateInput source="createdTime.lowerBound" allowEmpty alwaysOn/>
        <DateInput source="createdTime.upperBound" allowEmpty alwaysOn/>
    </Filter>
);

export const ContactAddressList = props => {
    console.info('ContactAddressList:', props);
    return (
        <List {...props} filters={<Filters/>}>
            <Datagrid rowClick="show">
                <TextField source="contactName"/>
                <TextField source="contactPhoneCode"/>
                <ReferenceField reference="regions" source="addressId" link={'show'}>
                    <TextField source="name"/>
                </ReferenceField>
                <ReferenceField reference="members" source="creatorId" link={'show'}>
                    <TextField source="username"/>
                </ReferenceField>
                <DateField source="createdTime" showTime/>
                <EditButton/>
            </Datagrid>
        </List>
    )
};
