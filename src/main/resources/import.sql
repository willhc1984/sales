INSERT INTO tb_user (username, password) VALUES('usuario','$2a$10$CiFc4GoIjo8eIQQvWpuvyumHwZtM7xurv2xyTfFbrbvYM54P3VKAW');
INSERT INTO tb_user (username, password) VALUES('admin','$2a$10$CiFc4GoIjo8eIQQvWpuvyumHwZtM7xurv2xyTfFbrbvYM54P3VKAW');
INSERT INTO tb_user (username, password) VALUES('maria','$2a$10$CiFc4GoIjo8eIQQvWpuvyumHwZtM7xurv2xyTfFbrbvYM54P3VKAW');

INSERT INTO tb_role (role_name) VALUES('ROLE_ADMIN');
INSERT INTO tb_role (role_name) VALUES('ROLE_USER');

INSERT INTO tb_users_roles (user_id, role_id) VALUES (1,1);
INSERT INTO tb_users_roles (user_id, role_id) VALUES (2,2);
INSERT INTO tb_users_roles (user_id, role_id) VALUES (3,2);

