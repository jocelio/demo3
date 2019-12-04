CREATE TABLE tb_users_rules (
  id_user int(11) NOT NULL,
  id_rule int(11) NOT NULL,
  is_grant BOOLEAN NOT NULL,
  PRIMARY KEY (id_user, id_rule)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;