drop table if exists contact_address;
create table contact_address
(
    id                 bigint unsigned primary key auto_increment comment '主键',
    contact_name       VARCHAR(255)    NOT NULL COMMENT '联系人姓名',
    contact_phone_code VARCHAR(255)    NOT NULL COMMENT '联系人手机号',
    address_id         BIGINT unsigned NOT NULL COMMENT '地区. 主键',
    address_detail     VARCHAR(255)    NOT NULL COMMENT '详细地址',
    defaults           bit             NOT NULL COMMENT '默认联系地址',
    source_id          bigint unsigned not null comment '上个联系地址. 0 表示新建，后续修改关联之前的联系地址主键',
    creator_id         bigint unsigned not null comment '创建者. 主键',
    created_time       datetime        not null comment '创建时间',
    modifier_id        bigint unsigned not null comment '最近修改者. 主键',
    modified_time      timestamp       not null comment '最近修改时间'
) comment '联系地址';

