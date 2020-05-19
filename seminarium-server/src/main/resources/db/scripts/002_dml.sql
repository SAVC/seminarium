INSERT INTO public.user_entity (id, email, nickname, password, role, username) VALUES (1, null, 'admin', '$argon2id$v=19$m=4096,t=3,p=1$RiMm6s+ndubJn9a/zQ4J8A$l0Qw/1cPNwT753+5nkoalMpz+AerBtre6vQFeWMCJDM', 'ADMIN', 'admin');

ALTER SEQUENCE user_entity_id_seq RESTART WITH 2;
