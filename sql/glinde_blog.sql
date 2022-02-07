create table blog
(
    id          int auto_increment comment '主键ID'
        primary key,
    title       varchar(60)    not null comment '标题',
    description varchar(150)   not null comment '描述',
    content     varchar(12000) not null comment '正文内容',
    date        datetime       not null comment '上传日期',
    state       int default 1  not null comment '状态'
)
    comment '博客';

create table mail
(
    id      int auto_increment comment '主键ID'
        primary key,
    content varchar(720)  not null comment '内容',
    date    datetime      not null comment '上传日期',
    state   int default 0 not null comment '状态'
)
    comment '信件';

create table user
(
    id       int auto_increment comment '主键ID'
        primary key,
    username varchar(30)   not null comment '用户名',
    password char(60)      not null comment '密码',
    state    int default 0 not null comment '状态',
    constraint user_username_uindex
        unique (username)
)
    comment '用户';


