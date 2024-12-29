create table hysa
(
    hysAid    int auto_increment comment '管理员id'
        primary key,
    hysAname  varchar(50)  null comment '管理员姓名',
    hysAphone varchar(11)  null comment '管理员手机号',
    hysAmail  varchar(50)  null comment '管理员邮箱',
    hysApass  varchar(100) null comment '管理员密码'
)
    comment '管理员表';

create table hyst
(
    hysTid   int auto_increment comment '分类ID'
        primary key,
    hysTname varchar(200) null comment '分类名'
)
    comment '模组分类';

create table hysu
(
    hysUid    int auto_increment comment '用户ID'
        primary key,
    hysUname  varchar(200) null comment '用户名',
    hysUphone varchar(11)  null comment '手机号',
    hysUqq    varchar(20)  null comment 'QQ号',
    hysUmile  varchar(50)  null comment '邮箱',
    hysUpass  varchar(50)  null comment '密码'
)
    comment '用户表';

create table hysme
(
    hysMEid   int auto_increment comment '留言ID'
        primary key,
    hysMEtxt  varchar(500) null comment '内容',
    hysMEtime datetime     null comment '时间',
    hysMEtoid int          null comment '被留言ID',
    hysMEidU  int          null comment '用户ID',
    constraint hysME_id_U_fk
        foreign key (hysMEidU) references hysu (hysUid),
    constraint hysME_toid_U_fk
        foreign key (hysMEtoid) references hysu (hysUid)
)
    comment '留言表';

create table hysmo
(
    hysMOid   int auto_increment comment '模组ID'
        primary key,
    hysMOidT  int          null comment '类ID',
    hysMOidU  int          null comment '上传者id',
    hysMOname varchar(255) null comment '模组名称',
    hysMOfile varchar(255) null comment '文件名',
    hysMOtime datetime     null comment '上传时间',
    hysMOtxt  varchar(200) null comment '模组内容',
    hysMOsum  int          null comment '收藏数',
    constraint hysMO_id_T_fk
        foreign key (hysMOidT) references hyst (hysTid),
    constraint hysmo_id_U_fk
        foreign key (hysMOidU) references hysu (hysUid)
)
    comment '模组表';

create table hysc
(
    hysCid   int auto_increment comment '评论表ID'
        primary key,
    hysCidU  int          null comment '用户id',
    hysCidMO int          null comment '模组id',
    hysCtxt  varchar(500) null comment '评论内容',
    hysCtime datetime     null comment '评论时间',
    constraint hysC_id_MO_fk
        foreign key (hysCidMO) references hysmo (hysMOid),
    constraint hysC_id_U_fk
        foreign key (hysCidU) references hysu (hysUid)
);

create table hyss
(
    hysSid   int auto_increment comment '收藏表id'
        primary key,
    hysSidU  int      null comment '用户ID',
    hysSidMO int      null comment '模组id',
    hysStime datetime null comment '收藏时间',
    constraint hysS_id_MO_fk
        foreign key (hysSidMO) references hysmo (hysMOid),
    constraint hysS_id_U_fk
        foreign key (hysSidU) references hysu (hysUid)
)
    comment '收藏表';

