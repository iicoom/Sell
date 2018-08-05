Sell sql 数据库设计

```
create table `product_info` (
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '商品名称',
	`product_price` decimal(8,2) not null comment '单价',
	`product_stock` int not null comment '库存',
	`product_description` varchar(64) comment '描述',
	`product_icon` varchar(512) default null comment '小图',
	`product_status` tinyint(3) default '0' comment '商品状态，0正常1下架',
	`category_type` int not null comment '类目编号',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (`product_id`)
) comment '商品表';


create table `product_category` (
	`category_id` int not null auto_increment,
	`category_name` varchar(64) not null comment '类目名字',
	`category_type` int not null comment '类目编号',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (`category_id`),
	unique key `uqe_category_type` (`category_type`)
) comment '类目表';


create table `order_master` (
	`order_id` varchar(32) not null,
	`buyer_name` varchar(32) not null comment '买家名字',
	`buyer_phone` varchar(32) not null comment '买家电话',
	`buyer_address` varchar(128) not null comment '买家地址',
	`buyer_openid` varchar(64) not null comment '买家微信openid',
	`order_amount` decimal(8,2) not null comment '订单总金额',
	`order_status` tinyint(3) not null default '0' comment '订单状态，默认0新下单',
	`pay_status` tinyint(3) not null default '0' comment '支付状态，默认0未支付',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (`order_id`),
	key `idx_buyer_openid` (`buyer_openid`)
) comment '订单表';

// 以上sql语句创建的表存储数据时会产生如下错误
2018-08-05 21:56:07,879 - Field 'order_id' doesn't have a default value

https://stackoverflow.com/questions/25865104/field-id-doesnt-have-a-default-value
As id is the primary key, you cannot have different rows with the same value. Try to change your table so that the id is auto incremented:

将SQL修改为：
create table `order_master` (
	`order_id` varchar(32) not null auto_increment,
	`buyer_name` varchar(32) not null comment '买家名字',
	`buyer_phone` varchar(32) not null comment '买家电话',
	`buyer_address` varchar(128) not null comment '买家地址',
	`buyer_openid` varchar(64) not null comment '买家微信openid',
	`order_amount` decimal(8,2) not null comment '订单总金额',
	`order_status` tinyint(3) not null default '0' comment '订单状态，默认0新下单',
	`pay_status` tinyint(3) not null default '0' comment '支付状态，默认0未支付',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (`order_id`),
	key `idx_buyer_openid` (`buyer_openid`)
) comment '订单表';

// 又爆出如下错误
Incorrect column specifier for column 'order_id'
// 可见自增id必须为int 类型
create table `order_master` (
	`order_id` int(11) not null auto_increment,
	`buyer_name` varchar(32) not null comment '买家名字',
	`buyer_phone` varchar(32) not null comment '买家电话',
	`buyer_address` varchar(128) not null comment '买家地址',
	`buyer_openid` varchar(64) not null comment '买家微信openid',
	`order_amount` decimal(8,2) not null comment '订单总金额',
	`order_status` tinyint(3) not null default '0' comment '订单状态，默认0新下单',
	`pay_status` tinyint(3) not null default '0' comment '支付状态，默认0未支付',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (`order_id`),
	key `idx_buyer_openid` (`buyer_openid`)
) comment '订单表';

// 由于之前oder_detail表已经存在 且有constraint `order_detail_ibfk_1` foreign key (`order_id`) references `order_master` (`order_id`)限制
// 故又爆出如下错误
Cannot add foreign key constraint
删掉order_detail重新创建 order_master创建成功 但是无法创建order_detail
同样是 Cannot add foreign key constraint
原因是`order_id` varchar(32) not null, 与上边的int(11) 类型不符
故修改order_detail表为
create table `order_detail` (
	`detail_id` varchar(32) not null,
	`order_id` int(11) not null,
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '商品名称',
	`product_price` decimal(8,2) not null comment '商品价格,单位分',
	`product_quantity` int(11) not null comment '数量',
	`product_icon` varchar(512) default null comment '小图',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (`detail_id`),
	key `idx_order_id` (`order_id`),
	constraint `order_detail_ibfk_1` foreign key (`order_id`) references `order_master` (`order_id`)
) comment '订单详情表';

=> 创建成功

create table `order_detail` (
	`detail_id` varchar(32) not null,
	`order_id` varchar(32) not null,
	`product_id` varchar(32) not null,
	`product_name` varchar(64) not null comment '商品名称',
	`product_price` decimal(8,2) not null comment '商品价格,单位分',
	`product_quantity` int(11) not null comment '数量',
	`product_icon` varchar(512) default null comment '小图',
	`create_time` timestamp not null default current_timestamp comment '创建时间',
	`update_time` timestamp not null default current_timestamp on update current_timestamp comment '更新时间',
	primary key (`detail_id`),
	key `idx_order_id` (`order_id`),
	constraint `order_detail_ibfk_1` foreign key (`order_id`) references `order_master` (`order_id`)
) comment '订单详情表';
```