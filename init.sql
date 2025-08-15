create table Pagamento (
    UUID uuid PRIMARY KEY,
    valor NUMERIC (10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    quem_processou VARCHAR(8) ,
    hora_que_processou TIMESTAMP WITH TIME ZONE NOT NULL
)