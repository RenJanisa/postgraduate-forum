create table user(
	id bigint comment '用户id',
	password varchar(20) comment '密码',
	register_time date comment '注册时间',
	email varchar(20) comment '邮箱',
	flag int comment '用户身份',
	user_name varchar(20) comment '用户名'
)comment '用户表';

create table user_info(
	id bigint comment '用户信息id',
	user_id bigint comment '用户id',
	tel varchar(20) comment '电话号码',
	avatar varchar(20) comment '用户头像路径',
	signature varchar(20) comment '用户个性签名',
	school varchar(15) comment '用户所在学校',
	profession varchar(20) comment '用户所读专业',
	grade varchar(10) comment '用户年级',
	goalId bigint comment '目标院校id',
	sex int comment '用户性别',
	age int comment '用户年龄'
)comment '用户信息表';