INSERT INTO tb_estado (nome) VALUES('MT');
INSERT INTO tb_estado (nome) VALUES('RJ');
INSERT INTO tb_estado (nome) VALUES('MA');
INSERT INTO tb_estado (nome) VALUES('MT');
INSERT INTO tb_estado (nome) VALUES('MS');
INSERT INTO tb_estado (nome) VALUES('PA');
INSERT INTO tb_estado (nome) VALUES('RS');
INSERT INTO tb_estado (nome) VALUES('SC');
INSERT INTO tb_estado (nome) VALUES('PR');
INSERT INTO tb_estado (nome) VALUES('PI');
INSERT INTO tb_estado (nome) VALUES('PE');
INSERT INTO tb_estado (nome) VALUES('TO');
INSERT INTO tb_estado (nome) VALUES('DF');
INSERT INTO tb_estado (nome) VALUES('AL');

INSERT INTO tb_user (username, password) VALUES('usuario','$2a$10$CiFc4GoIjo8eIQQvWpuvyumHwZtM7xurv2xyTfFbrbvYM54P3VKAW');
INSERT INTO tb_user (username, password) VALUES('admin','$2a$10$CiFc4GoIjo8eIQQvWpuvyumHwZtM7xurv2xyTfFbrbvYM54P3VKAW');
INSERT INTO tb_user (username, password) VALUES('maria','$2a$10$CiFc4GoIjo8eIQQvWpuvyumHwZtM7xurv2xyTfFbrbvYM54P3VKAW');

INSERT INTO tb_role (role_name) VALUES('ROLE_ADMIN');
INSERT INTO tb_role (role_name) VALUES('ROLE_USER');

INSERT INTO tb_users_roles (user_id, role_id) VALUES (1,1);
INSERT INTO tb_users_roles (user_id, role_id) VALUES (2,2);
INSERT INTO tb_users_roles (user_id, role_id) VALUES (3,2);

