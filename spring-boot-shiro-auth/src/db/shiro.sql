INSERT INTO `user` (`uid`,`username`,`name`,`password`,`salt`,`state`) VALUES ('1', 'admin', 'ADMIN', 'd3c59d25033dbf980d29554025c23a75', '8d78869f470951332959580424d4bf4f', 0);
INSERT INTO `permission` (`id`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (1,0,'用户管理',0,'0/','user:view','menu','user/info');
INSERT INTO `permission` (`id`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (2,0,'用户添加',1,'0/1','user:add','button','user/add');
INSERT INTO `permission` (`id`,`available`,`name`,`parent_id`,`parent_ids`,`permission`,`resource_type`,`url`) VALUES (3,0,'用户删除',1,'0/1','user:del','button','user/del');
INSERT INTO `role` (`id`,`available`,`description`,`role`) VALUES (1,0,'ADMIN','admin');
INSERT INTO `role` (`id`,`available`,`description`,`role`) VALUES (2,0,'VIP','vip');
INSERT INTO `role` (`id`,`available`,`description`,`role`) VALUES (3,1,'USER','user');
INSERT INTO `role_permission` VALUES ('1', '1');
INSERT INTO `role_permission` (`permission_id`,`role_id`) VALUES (1,1);
INSERT INTO `role_permission` (`permission_id`,`role_id`) VALUES (2,1);
INSERT INTO `role_permission` (`permission_id`,`role_id`) VALUES (3,2);
INSERT INTO `user_role` (`role_id`,`uid`) VALUES (1,1);

## select u.uid,u.username,r.role from (user u left join user_role ur on u.uid=ur.uid) left join role r on r.id=ur.role_id;