drop table if exists `ebook`;
create table `ebook`
(
    `id`           bigint not null comment 'id',
    `name`         varchar(50) comment '名称',
    `category1_id` bigint comment '分类1',
    `category2_id` bigint comment '分类2',
    `description`  varchar(200) comment '描述',
    `cover`        varchar(200) comment '封面',
    `doc_count`    int comment '文档数',
    `view_count`   int comment '阅读数',
    `vote_count`   int comment '点赞数',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='电子书';
insert into `ebook`(id, name, description) values (1, 'Spring boot 入门教程', '零基础入门Java开发，企业级应用开发首选框架');
insert into `ebook`(id, name, description)
values (2, 'vue 入门教程', '零基础入门vue开发，企业级应用开发首选框架');
insert into `ebook`(id, name, description)
values (3, 'Python 入门教程', '零基础入门Python开发，企业级应用开发首选框架');
insert into `ebook`(id, name, description)
values (4, 'Mysql 入门教程', '零基础入门Mysql开发，企业级应用开发首选框架');
insert into `ebook`(id, name, description)
values (5, 'Oracle 入门教程', '零基础入门Oracle开发，企业级应用开发首选框架');


drop table if exists `test`;
create table `test`
(
    `id`       bigint not null comment 'id',
    `name`     varchar(50) comment '名称',
    `password` varchar(50) comment '密码',
    primary key (`id`)
)engine innodb default charset=utf8mb4 comment ='测试';

insert into `test`(id, name, password)
values (1, '测试', 'password');

drop table if exists `demo`;
create table `demo`
(
    `id`   bigint not null comment 'id',
    `name` varchar(50) comment '名称',

    primary key (`id`)
)engine innodb default charset=utf8mb4 comment ='测试';

insert into `demo`(id, name)
values (1, '测试');


drop table if exists `Categorys`;
create table `Categorys`
(
    `id`           bigint not null comment 'id',
    `parent`         varchar(50) comment '父id',
    `name`         varchar(50) comment '名称',
    `sort`         varchar(50) comment '顺序',
    primary key (`id`)
) engine=innodb default charset=utf8mb4 comment='分类';
insert into `Categorys`(id, parent, name,sort) values (100, 000, '前端开发',100);
insert into `Categorys`(id, parent, name,sort) values (101, 100, 'Vue',101);
insert into `Categorys`(id, parent, name,sort) values (102, 100, 'HTML',102);
insert into `Categorys`(id, parent, name,sort) values (200, 000, 'Java',200);
insert into `Categorys`(id, parent, name,sort) values (201, 200, '基础应用',201);
insert into `Categorys`(id, parent, name,sort) values (202, 200, '框架应用',202);
insert into `Categorys`(id, parent, name,sort) values (300, 000, 'Python',300);
insert into `Categorys`(id, parent, name,sort) values (301, 300, '基础应用',301);
insert into `Categorys`(id, parent, name,sort) values (302, 300, '进阶方向应用',302);
insert into `Categorys`(id, parent, name,sort) values (400, 000, '数据库',400);
insert into `Categorys`(id, parent, name,sort) values (401, 400, 'MySQL',401);
insert into `Categorys`(id, parent, name,sort) values (500, 000, '其他',500);
insert into `Categorys`(id, parent, name,sort) values (501, 500, '服务器',501);
insert into `Categorys`(id, parent, name,sort) values (502, 500, '开发工具',502);
insert into `Categorys`(id, parent, name,sort) values (503, 500, '热门服务端语言',503);


-- 文档表
drop table if exists `doc`;
create table `doc`
(
    `id`         bigint not null comment 'id',
    `ebook_id`   bigint not null default 0 comment '电子书id',
    `parent`     varchar(50) comment '父id',
    `name`       varchar(50) comment '名称',
    `sort`       varchar(50) comment '顺序',
    `view_count` varchar(50) comment '阅读数',
    `vote_count` varchar(50) comment '点赞数',
    primary key (`id`)
) engine = innodb
  default charset = utf8mb4 comment ='文档';

insert into `doc`(id, ebook_id, parent, name,sort,view_count,vote_count) values (1,1,0, '文档1',1,0,0);
insert into `doc`(id, ebook_id, parent, name,sort,view_count,vote_count) values (2,1,1, '文档1.1',1,0,0);
insert into `doc`(id, ebook_id, parent, name,sort,view_count,vote_count) values (3,1,0, '文档2',2,0,0);
insert into `doc`(id, ebook_id, parent, name,sort,view_count,vote_count) values (4,1,3, '文档2.1',1,0,0);
insert into `doc`(id, ebook_id, parent, name,sort,view_count,vote_count) values (5,1,3, '文档2.2',2,0,0);
insert into `doc`(id, ebook_id, parent, name,sort,view_count,vote_count) values (6,1,5, '文档2.2.1',1,0,0);



