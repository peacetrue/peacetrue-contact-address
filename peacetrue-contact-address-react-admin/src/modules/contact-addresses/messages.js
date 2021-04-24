export const contactAddressMessages = {
    resources: {
        "contact-addresses": {
            name: '联系地址',
            fields: {
                'id': '主键',
                'contactName': '联系人姓名',
                'contactPhoneCode': '联系人手机号',
                'addressId': '地区',
                'addressDetail': '详细地址',
                'sourceId': '上个联系地址',
                'creatorId': '创建者',
                'createdTime': '创建时间',
                'createdTime.lowerBound': "创建时间起始值",
                'createdTime.upperBound': "创建时间结束值",
                'modifierId': '修改者',
                'modifiedTime': '修改时间',
                'modifiedTime.lowerBound': "修改时间起始值",
                'modifiedTime.upperBound': "修改时间结束值",
            },
        }
    }
}

//['id','contactName','contactPhoneCode','addressId','addressDetail','sourceId','creatorId','createdTime','modifierId','modifiedTime',]
//['主键','联系人姓名','联系人手机号','地区','详细地址','上个联系地址','创建者','创建时间','最近修改者','最近修改时间',]

export default contactAddressMessages;
