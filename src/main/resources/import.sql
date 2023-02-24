INSERT INTO tb_user (username, password) VALUES('usuario','$2a$10$CiFc4GoIjo8eIQQvWpuvyumHwZtM7xurv2xyTfFbrbvYM54P3VKAW');
INSERT INTO tb_user (username, password) VALUES('admin','$2a$10$CiFc4GoIjo8eIQQvWpuvyumHwZtM7xurv2xyTfFbrbvYM54P3VKAW');
INSERT INTO tb_role (role_name) VALUES('ROLE_ADMIN');
INSERT INTO tb_role (role_name) VALUES('ROLE_USER');
INSERT INTO tb_users_roles VALUES (1,2);
INSERT INTO tb_users_roles VALUES (2,1);

