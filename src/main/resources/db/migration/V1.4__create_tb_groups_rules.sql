CREATE TABLE tb_groups_rules (
  id_group int(11) NOT NULL,
  id_rule int(11) NOT NULL,
  PRIMARY KEY (id_group, id_rule)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;