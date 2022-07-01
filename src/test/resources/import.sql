--
-- PostgreSQL database dump
--

-- Dumped from database version 14.3 (Ubuntu 14.3-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.3 (Ubuntu 14.3-0ubuntu0.22.04.1)

--
-- Data for Name: campeonato; Type: TABLE DATA; Schema: test; Owner: postgres
--

INSERT INTO campeonato (id, finalizado, nome) VALUES
(12,	't',	'Campeonato teste'),
(13,	't',	'Campeonato teste'),
(14,	'f',	'Campeonato teste'),
(15,	'f',	'Campeonato teste'),
(16,	'f',	'Campeonato teste'),
(17,	'f',	'Campeonato teste'),
(18,	'f',	'Campeonato teste'),
(19,	'f',	'Campeonato teste'),
(20,	'f',	'Campeonato teste'),
(21,	'f',	'Campeonato teste'),
(22,	'f',	'campeonato'),
(25,	't',	'Campeonato teste'),
(26,	'f',	'Campeonato teste'),
(27,	'f',	'Campeonato teste'),
(28,	'f',	'Campeonato teste'),
(29,	't',	'Campeonato teste'),
(30,	't',	'Campeonato teste'),
(31,	't',	'Campeonato teste'),
(32,	't',	'Campeonato teste'),
(33,	't',	'Campeonato teste');

--
-- Data for Name: jogo; Type: TABLE DATA; Schema: test; Owner: postgres
--

INSERT INTO jogo (id, campeonato_id, finalizado, pontuacao_time1, pontuacao_time2, time1, time2, vencedor) VALUES
(23,	22,	't'	,12,	10,	1,	2,	1),
(24,	22,	'f'	,0,		0,	1,	2,	null);


--
-- Data for Name: time; Type: TABLE DATA; Schema: test; Owner: postgres
--

INSERT INTO "time" (id, nome) VALUES
(1,	    'time'),
(2,	    'time'),
(3,	    'timeTeste'),
(4,	    'timeTeste'),
(5,	    'timeTeste'),
(6,	    'timeTeste'),
(7,	    'timeTeste'),
(8,	    'timeTeste'),
(9,	    'timeTeste'),
(10,	'time'),
(11,	'timeTeste');


--
-- Data for Name: campeonato_time; Type: TABLE DATA; Schema: test; Owner: postgres
--

INSERT INTO campeonato_time (campeonato_id, time_id) VALUES
(12,	3),
(12,	4),
(13,	3),
(13,	4),
(14,	3),
(14,	4),
(16,	3),
(16,	4),
(18,	3),
(18,	4),
(22,	1),
(22,	2),
(25,	1),
(25,	2),
(26,	1),
(26,	2),
(27,	1),
(27,	2),
(28,	1),
(28,	2),
(29,	1),
(29,	2),
(30,	1),
(30,	2),
(31,	1),
(31,	2),
(32,	1),
(32,	2),
(33,	1),
(33,	2);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: test; Owner: postgres
--

SELECT pg_catalog.setval('hibernate_sequence', 33, true);

--
-- PostgreSQL database dump complete
--

