CREATE TABLE tb_groups_users (
  id_group int(11) NOT NULL,
  id_user int(11) NOT NULL,
  PRIMARY KEY (id_group, id_user)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;