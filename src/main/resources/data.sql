insert into autor (id, nome) values (1, 'José de alencar'), (2, 'Machado de Assis'), (3, 'Jeff Kinney'), (4, 'William Shakespeare'),  (5, 'Augusto Cury')


insert into livro (id, nome, quantidade, isbn, autor_id) values (1, 'O Guarani', 10, '123', 1), (2, 'Iracema', 30, '1234', 1), (3, 'Dom Casmurro', 15, '1234', 2), (4, 'Helena', 15, '1234', 2), (5, 'Diário de um banana', 15, '1234', 3), (6, 'Romeu e Julieta', 15, '1234', 4)

insert into cad_cliente (id_cliente, data_nascimento, endereco, nome, observacao) values (1, '1986-08-07', 'rua teste', 'Carla', ''), (2, '1986-08-07', 'rua teste', 'João', ''), (3, '1986-08-07', 'rua teste', 'José', ''),

insert into usuario (id, PASSSWORD, USERNAME) values (1, '123', 'carla'), (2, '1234', 'jose')

insert into emprestimo (id, data_devolucao, data_emprestimo, cliente_id_cliente, livro_id) values (1, '2018-03-18', '2018-03-01', 1, 1), (2, '2018-03-18', '2018-03-01', 2, 2), (3, '2018-03-18',null, 1, 3)