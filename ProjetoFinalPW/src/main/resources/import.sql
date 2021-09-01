insert into destaque (imagem) values ('twinpeaks-banner.png');
insert into destaque (imagem) values ('thething-banner.jpg');
insert into destaque (imagem) values ('drstrangelove-banner-2.jpg');

insert into homepage (texto) values ('Lorem Ipsum is simply dummy text of the printing and typesetting industry.');

insert into destaque_homepage (homepage_id, destaque_id) values (1, 1);
insert into destaque_homepage (homepage_id, destaque_id) values (1, 2);
insert into destaque_homepage (homepage_id, destaque_id) values (1, 3);

insert into artista (nome, tipo) values ('Cuck Russel', 'DIRETOR');
insert into artista (nome, tipo) values ('John Carpenter', 'DIRETOR');
insert into artista (nome, tipo) values ('David Zucker', 'DIRETOR');
insert into artista (nome, tipo) values ('Jim Abrahams', 'DIRETOR');
insert into artista (nome, tipo) values ('Jerry Zucker', 'DIRETOR');
insert into artista (nome, tipo) values ('Stanley Kubrick', 'DIRETOR');

insert into produto (titulo, sinopse, valor, ano, imagem, genero) values ('A Bolha Assassina', 'Lorem ipsum dolor sit amet.', 19.99, 1988, 'theblob.jpg', 'TERROR');
insert into produto (titulo, sinopse, valor, ano, imagem, genero) values ('The Thing', 'Lorem ipsum dolor sit amet.', 20.90, 1983, 'thething.jpg', 'TERROR');
insert into produto (titulo, sinopse, valor, ano, imagem, genero) values ('Apertem os Cintos... O Piloto Sumiu!', 'Lorem ipsum dolor sit amet.', 9.99, 1980, 'airplane.jpg', 'COMEDIA');
insert into produto (titulo, sinopse, valor, ano, imagem, genero) values ('Dr. Strangelove', 'Lorem ipsum dolor sit amet.', 19.99, 1964, 'drstrangelove.jpg', 'CULT');

insert into diretores_filmes (produto_id, diretor_id) values (1, 1);
insert into diretores_filmes (produto_id, diretor_id) values (2, 2);
insert into diretores_filmes (produto_id, diretor_id) values (3, 3);
insert into diretores_filmes (produto_id, diretor_id) values (3, 4);
insert into diretores_filmes (produto_id, diretor_id) values (3, 5);
insert into diretores_filmes (produto_id, diretor_id) values (4, 6);

insert into fornecedor (nome) values ('Trabalho Final - João Leonardo');

insert into frete (texto, prazo, valor_entrega) values ('Express', '2 a 4 dias úteis', 22.86);
insert into frete (texto, prazo) values ('Grátis', '6 a 8 dias úteis');

insert into produto_detalhe (nota, info_adicional_link, fornecedor_id) values (5, 'https://en.wikipedia.org/wiki/Dr._Strangelove', 1);
insert into produto_detalhe_imagem (nome, produto_detalhe_imagem_id) values ('drstrangelove-banner.jpg', 1);
insert into produto_detalhe_imagem (nome, produto_detalhe_imagem_id) values ('drstrangelove-banner-2.jpg', 1);
insert into produto_detalhe_imagem (nome, produto_detalhe_imagem_id) values ('drstrangelove-banner-3.jpg', 1);


update produto set produto_detalhe_id = 1 where id = 4;
