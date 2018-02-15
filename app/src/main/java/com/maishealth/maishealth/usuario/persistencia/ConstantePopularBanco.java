package com.maishealth.maishealth.usuario.persistencia;

public class ConstantePopularBanco {
    public static final String INSERIR_USUARIO = "INSERT INTO `usuario` (`email`,`senha`) VALUES " +
            "('a@gmail.com','111111')," +
            "('b@gmail.com','111111')," +
            "('c@gmail.com','111111')," +
            "('d@gmail.com','111111')," +
            "('e@gmail.com','111111')," +
            "('f@gmail.com','111111')," +
            "('g@gmail.com','111111')," +
            "('h@gmail.com','111111')," +
            "('i@gmail.com','111111')," +
            "('j@gmail.com','111111')," +
            "('u@gmail.com','111111')," +
            "('v@gmail.com','111111')," +
            "('k@gmail.com','111111')," + //11
            "('l@gmail.com','111111')," + //12
            "('m@gmail.com','111111')," + //13
            "('n@gmail.com','111111')," + //14
            "('o@gmail.com','111111')," + //15
            "('p@gmail.com','111111')," + //16
            "('q@gmail.com','111111')," + //17
            "('r@gmail.com','111111')," + //18
            "('s@gmail.com','111111')," + //19
            "('t@gmail.com','111111');";  //20

    public static final String INSERIR_PESSOA = "INSERT INTO `pessoa` (`nome`,`sexo`,`data_nasc`,`cpf`,`id_est_usuario`) VALUES " +
            "('Maite Stokes','Feminino','19500212','079.476.434-00',1)," +
            "('Kane Kramer','Masculino','19870515','079.476.434-01',2)," +
            "('Jenette Caldwell','Feminino','19881201','079.476.434-02',3)," +
            "('Nomlanga Dennis','Masculino','19900212','079.476.434-03',4)," +
            "('Leigh Day','Masculino','20001120','079.476.434-04',5)," +
            "('Laith Miller','Feminino','19850417','079.476.434-05',6)," +
            "('Amela Anderson','Feminino','19400123','079.476.434-06',7)," +
            "('Lucy Whitehead','Feminino','19730202','079.476.434-07',8)," +
            "('Indigo Ortega','Masculino','19870227','079.476.434-08',9)," +
            "('Akeem Stout','Feminino','19950505','079.476.434-09',10)," +
            "('Aite Stokes','Feminino','19500212','079.476.434-00',11)," +
            "('Ane Kramer','Masculino','19870515','079.476.434-01',12)," +
            "('Nette Caldwell','Feminino','19881201','079.476.434-02',13)," +
            "('Langa Dennis','Masculino','19900212','079.476.434-03',14)," +
            "('Eigh Day','Masculino','20001120','079.476.434-04',15)," +
            "('Aith Miller','Feminino','19850417','079.476.434-05',16)," +
            "('Amel Anderson','Feminino','19400123','079.476.434-06',17)," +
            "('Luc Whitehead','Feminino','19730202','079.476.434-07',18)," +
            "('Lauro Ortega','Masculino','19870227','079.476.434-08',19)," +
            "('Ken Stout','Feminino','19950505','079.476.434-09',20);";


    public static final String INSERIR_PACIENTE = "INSERT INTO `paciente` (`tipo_sangue`,`id_est_usuario`) VALUES " +
            "('A-',1)," +
            "('B+',2)," +
            "('O+',3)," +
            "('B+',4)," +
            "('A+',5)," +
            "('B-',6)," +
            "('A+',7)," +
            "('AB+',8)," +
            "('A-',9)," +
            "('O-',10);";

    public static final String INSERIR_MEDICO = "INSERT INTO `medico` (`crm`,`id_est_usuario`,`estado`,`especialidade`) VALUES " +
            "('111.100.',1,'PE','Clínico Geral')," +
            "('111.101.',2,'AC','Pediatria')," +
            "('111.102.',3,'RJ','Clínico Geral')," +
            "('111.103.',4,'SP','Cardiologia')," +
            "('111.104.',5,'BA','Cardiologia')," +
            "('111.105.',11,'PE','Clínico Geral')," +
            "('111.106.',12,'AC','Pediatria')," +
            "('111.107.',13,'RJ','Clínico Geral')," +
            "('111.108.',14,'SP','Cardiologia')," +
            "('111.109.',15,'BA','Cardiologia')," +
            "('111.110.',16,'PE','Clínico Geral')," +
            "('111.111.',17,'AC','Pediatria')," +
            "('111.112.',18,'RJ','Clínico Geral')," +
            "('111.113.',19,'SP','Cardiologia')," +
            "('111.114.',20,'BA','Cardiologia');";

    public static final String INSERIR_HORARIO_MEDICO = "INSERT INTO `horario_medico` (`dia_da_semana`,`vagas`,`turno`,`id_est_medico`) VALUES " +
            "('Segunda',8,'Manhã',4)," +
            "('Segunda',7,'Tarde',4)," +
            "('Segunda',6,'Noite',4)," +
            "('Terça',5,'Manhã',4)," +
            "('Terça',6,'Tarde',4)," +
            "('Terça',4,'Noite',4)," +
            "('Quarta',1,'Manhã',4)," +
            "('Quarta',9,'Tarde',4)," +
            "('Quarta',2,'Noite',4)," +
            "('Quinta',4,'Tarde',4)," +
            "('Quinta',5,'Noite',4)," +
            "('Quinta',7,'Manhã',4)," +
            "('Sexta',6,'Manhã',4)," +
            "('Sexta',2,'Tarde',4)," +
            "('Sexta',3,'Noite',4)," +
            "('Segunda',8,'Manhã',1)," +
            "('Segunda',7,'Tarde',1)," +
            "('Segunda',6,'Noite',1)," +
            "('Terça',5,'Manhã',1)," +
            "('Terça',6,'Tarde',1)," +
            "('Terça',4,'Noite',1)," +
            "('Quarta',1,'Manhã',1)," +
            "('Quarta',9,'Tarde',1)," +
            "('Quarta',2,'Noite',1)," +
            "('Quinta',4,'Tarde',1)," +
            "('Quinta',5,'Noite',1)," +
            "('Quinta',7,'Manhã',1)," +
            "('Sexta',6,'Manhã',1)," +
            "('Sexta',2,'Tarde',1)," +
            "('Sexta',3,'Noite',1)," +
            "('Segunda',8,'Manhã',2)," +
            "('Segunda',7,'Tarde',2)," +
            "('Segunda',6,'Noite',2)," +
            "('Terça',5,'Manhã',2)," +
            "('Terça',6,'Tarde',2)," +
            "('Terça',4,'Noite',2)," +
            "('Quarta',1,'Manhã',2)," +
            "('Quarta',9,'Tarde',2)," +
            "('Quarta',2,'Noite',2)," +
            "('Quinta',4,'Tarde',2)," +
            "('Quinta',5,'Noite',2)," +
            "('Quinta',7,'Manhã',2)," +
            "('Sexta',6,'Manhã',2)," +
            "('Sexta',2,'Tarde',2)," +
            "('Sexta',3,'Noite',2),"+
            "('Segunda',8,'Manhã',3)," +
            "('Segunda',7,'Tarde',3)," +
            "('Segunda',6,'Noite',3)," +
            "('Terça',5,'Manhã',3)," +
            "('Terça',6,'Tarde',3)," +
            "('Terça',4,'Noite',3)," +
            "('Quarta',1,'Manhã',3)," +
            "('Quarta',9,'Tarde',3)," +
            "('Quarta',2,'Noite',3)," +
            "('Quinta',4,'Tarde',3)," +
            "('Quinta',5,'Noite',3)," +
            "('Quinta',7,'Manhã',3)," +
            "('Sexta',6,'Manhã',3)," +
            "('Sexta',2,'Tarde',3)," +
            "('Sexta',3,'Noite',3)," +
            "('Segunda',8,'Manhã',5)," +
            "('Segunda',7,'Tarde',5)," +
            "('Segunda',6,'Noite',5)," +
            "('Terça',5,'Manhã',5)," +
            "('Terça',6,'Tarde',5)," +
            "('Terça',4,'Noite',5)," +
            "('Quarta',1,'Manhã',5)," +
            "('Quarta',9,'Tarde',5)," +
            "('Quarta',2,'Noite',5)," +
            "('Quinta',4,'Tarde',5)," +
            "('Quinta',5,'Noite',5)," +
            "('Quinta',7,'Manhã',5)," +
            "('Sexta',6,'Manhã',5)," +
            "('Sexta',2,'Tarde',5)," +
            "('Sexta',3,'Noite',5)," + // aqui começa
            "('Segunda',8,'Manhã',11)," +
            "('Segunda',7,'Tarde',11)," +
            "('Segunda',6,'Noite',11)," +
            "('Terça',5,'Manhã',11)," +
            "('Terça',6,'Tarde',11)," +
            "('Terça',4,'Noite',11)," +
            "('Quarta',1,'Manhã',11)," +
            "('Quarta',9,'Tarde',11)," +
            "('Quarta',2,'Noite',11)," +
            "('Quinta',4,'Tarde',11)," +
            "('Quinta',5,'Noite',11)," +
            "('Quinta',7,'Manhã',11)," +
            "('Sexta',6,'Manhã',11)," +
            "('Sexta',2,'Tarde',11)," +
            "('Sexta',3,'Noite',11)," +
            "('Segunda',8,'Manhã',12)," +
            "('Segunda',7,'Tarde',12)," +
            "('Segunda',6,'Noite',12)," +
            "('Terça',5,'Manhã',12)," +
            "('Terça',6,'Tarde',12)," +
            "('Terça',4,'Noite',12)," +
            "('Quarta',1,'Manhã',12)," +
            "('Quarta',9,'Tarde',12)," +
            "('Quarta',2,'Noite',12)," +
            "('Quinta',4,'Tarde',12)," +
            "('Quinta',5,'Noite',12)," +
            "('Quinta',7,'Manhã',12)," +
            "('Sexta',6,'Manhã',12)," +
            "('Sexta',2,'Tarde',12)," +
            "('Sexta',3,'Noite',12)," +
            "('Segunda',8,'Manhã',13)," +
            "('Segunda',7,'Tarde',13)," +
            "('Segunda',6,'Noite',13)," +
            "('Terça',5,'Manhã',13)," +
            "('Terça',6,'Tarde',13)," +
            "('Terça',4,'Noite',13)," +
            "('Quarta',1,'Manhã',13)," +
            "('Quarta',9,'Tarde',13)," +
            "('Quarta',2,'Noite',13)," +
            "('Quinta',4,'Tarde',13)," +
            "('Quinta',5,'Noite',13)," +
            "('Quinta',7,'Manhã',13)," +
            "('Sexta',6,'Manhã',13)," +
            "('Sexta',2,'Tarde',13)," +
            "('Sexta',3,'Noite',13),"+
            "('Segunda',8,'Manhã',14)," +
            "('Segunda',7,'Tarde',14)," +
            "('Segunda',6,'Noite',14)," +
            "('Terça',5,'Manhã',14)," +
            "('Terça',6,'Tarde',14)," +
            "('Terça',4,'Noite',14)," +
            "('Quarta',1,'Manhã',14)," +
            "('Quarta',9,'Tarde',14)," +
            "('Quarta',2,'Noite',14)," +
            "('Quinta',4,'Tarde',14)," +
            "('Quinta',5,'Noite',14)," +
            "('Quinta',7,'Manhã',14)," +
            "('Sexta',6,'Manhã',14)," +
            "('Sexta',2,'Tarde',14)," +
            "('Sexta',3,'Noite',14)," +
            "('Segunda',8,'Manhã',15)," +
            "('Segunda',7,'Tarde',15)," +
            "('Segunda',6,'Noite',15)," +
            "('Terça',5,'Manhã',15)," +
            "('Terça',6,'Tarde',15)," +
            "('Terça',4,'Noite',15)," +
            "('Quarta',1,'Manhã',15)," +
            "('Quarta',9,'Tarde',15)," +
            "('Quarta',2,'Noite',15)," +
            "('Quinta',4,'Tarde',15)," +
            "('Quinta',5,'Noite',15)," +
            "('Quinta',7,'Manhã',15)," +
            "('Sexta',6,'Manhã',15)," +
            "('Sexta',2,'Tarde',15)," +
            "('Sexta',3,'Noite',15)," +// aqui termina
            "('Segunda',8,'Manhã',6)," +
            "('Segunda',7,'Tarde',6)," +
            "('Segunda',6,'Noite',6)," +
            "('Terça',5,'Manhã',6)," +
            "('Terça',6,'Tarde',6)," +
            "('Terça',4,'Noite',6)," +
            "('Quarta',1,'Manhã',6)," +
            "('Quarta',9,'Tarde',6)," +
            "('Quarta',2,'Noite',6)," +
            "('Quinta',4,'Tarde',6)," +
            "('Quinta',5,'Noite',6)," +
            "('Quinta',7,'Manhã',6)," +
            "('Sexta',6,'Manhã',6)," +
            "('Sexta',2,'Tarde',6)," +
            "('Sexta',3,'Noite',6)," +
            "('Segunda',8,'Manhã',7)," +
            "('Segunda',7,'Tarde',7)," +
            "('Segunda',6,'Noite',7)," +
            "('Terça',5,'Manhã',7)," +
            "('Terça',6,'Tarde',7)," +
            "('Terça',4,'Noite',7)," +
            "('Quarta',1,'Manhã',7)," +
            "('Quarta',9,'Tarde',7)," +
            "('Quarta',2,'Noite',7)," +
            "('Quinta',4,'Tarde',7)," +
            "('Quinta',5,'Noite',7)," +
            "('Quinta',7,'Manhã',7)," +
            "('Sexta',6,'Manhã',7)," +
            "('Sexta',2,'Tarde',7)," +
            "('Sexta',3,'Noite',7)," +
            "('Segunda',8,'Manhã',8)," +
            "('Segunda',7,'Tarde',8)," +
            "('Segunda',6,'Noite',8)," +
            "('Terça',5,'Manhã',8)," +
            "('Terça',6,'Tarde',8)," +
            "('Terça',4,'Noite',8)," +
            "('Quarta',1,'Manhã',8)," +
            "('Quarta',9,'Tarde',8)," +
            "('Quarta',2,'Noite',8)," +
            "('Quinta',4,'Tarde',8)," +
            "('Quinta',5,'Noite',8)," +
            "('Quinta',7,'Manhã',8)," +
            "('Sexta',6,'Manhã',8)," +
            "('Sexta',2,'Tarde',8)," +
            "('Sexta',3,'Noite',8),"+
            "('Segunda',8,'Manhã',9)," +
            "('Segunda',7,'Tarde',9)," +
            "('Segunda',6,'Noite',9)," +
            "('Terça',5,'Manhã',9)," +
            "('Terça',6,'Tarde',9)," +
            "('Terça',4,'Noite',9)," +
            "('Quarta',1,'Manhã',9)," +
            "('Quarta',9,'Tarde',9)," +
            "('Quarta',2,'Noite',9)," +
            "('Quinta',4,'Tarde',9)," +
            "('Quinta',5,'Noite',9)," +
            "('Quinta',7,'Manhã',9)," +
            "('Sexta',6,'Manhã',9)," +
            "('Sexta',2,'Tarde',9)," +
            "('Sexta',3,'Noite',9)," +
            "('Segunda',8,'Manhã',10)," +
            "('Segunda',7,'Tarde',10)," +
            "('Segunda',6,'Noite',10)," +
            "('Terça',5,'Manhã',10)," +
            "('Terça',6,'Tarde',10)," +
            "('Terça',4,'Noite',10)," +
            "('Quarta',1,'Manhã',10)," +
            "('Quarta',9,'Tarde',10)," +
            "('Quarta',2,'Noite',10)," +
            "('Quinta',4,'Tarde',10)," +
            "('Quinta',5,'Noite',10)," +
            "('Quinta',7,'Manhã',10)," +
            "('Sexta',6,'Manhã',10)," +
            "('Sexta',2,'Tarde',10)," +
            "('Sexta',3,'Noite',10);" ;

    public static final String INSERIR_POSTO = "INSERT INTO 'posto' ('nome','local') VALUES" +
            "('Posto Maria Lucinda','Rua A')," +
            "('Posto B','Rua B')," +
            "('Posto C','Rua C')," +
            "('Posto D','Rua D')," +
            "('Posto E','Rua E');";

    public static final String INSERIR_MEDICO_POSTO = "INSERT INTO 'medico_posto' ('id_medico','id_posto') VALUES" +
            "(1,1)," +
            "(2,1)," +
            "(3,1)," +
            "(4,1)," +
            "(5,1);";
    public static final String INSERIR_CONSULTA = "INSERT INTO 'consulta' " +
            "('id_medico','id_paciente','data','turno','status_consulta') VALUES " +
            "(4,0,'09/02/2018','Manhã','DISPONIVEL')," +
            "(4,0,'09/02/2018','Manhã','DISPONIVEL')," +
            "(4,0,'09/02/2018','Manhã','DISPONIVEL')," +
            "(4,0,'09/02/2018','Manhã','DISPONIVEL')," +
            "(4,8,'09/02/2018','Manhã','EMANDAMENTO')," +
            "(4,9,'09/02/2018','Manhã','EMANDAMENTO')," +
            "(4,10,'09/02/2018','Manhã','EMANDAMENTO')," +
            "(4,10,'10/02/2018','Manhã','CONCLUIDA')," +
            "(5,10,'12/02/2018','Manhã','EMANDAMENTO')," +
            "(3,10,'11/02/2018','Manhã','CONCLUIDA')," +
            "(2,10,'11/02/2018','Manhã','CONCLUIDA')," +
            "(1,10,'13/02/2018','Manhã','EMANDAMENTO')," +
            "(1,9,'13/02/2018','Manhã','EMANDAMENTO')," +
            "(1,8,'13/02/2018','Manhã','EMANDAMENTO')," +
            "(1,9,'12/03/2018','Manhã','EMANDAMENTO')," +
            "(1,10,'19/02/2018','Manhã','EMANDAMENTO')," +
            "(1,9,'19/02/2018','Manhã','EMANDAMENTO')," +
            "(5,8,'11/02/2018','Manhã','CONCLUIDA')," +
            "(4,7,'09/02/2018','Manhã','EMANDAMENTO');";

    public static final String ISERIR_RECOMENDACAO = " INSERT INTO 'recomendacao' " +
            "('id_paciente', 'id_medico', 'nota') VALUES " +
            "(1,1,4)," +
            "(1,2,4)," +
            "(1,3,4)," +
            "(1,4,4)," +
            "(1,5,4)," +
            "(1,6,4)," +
            "(1,7,4)," +
            "(1,8,4)," +
            "(1,9,4)," +
            "(1,10,4)," +
            "(1,11,4)," +
            "(1,12,4)," +
            "(1,13,4)," +
            "(1,14,4)," +
            "(1,15,4)," +
            "(2,1,0)," +
            "(2,2,0)," +
            "(2,3,3)," +
            "(2,4,4)," +
            "(2,5,5)," +
            "(2,6,0)," +
            "(2,7,1)," +
            "(2,8,2)," +
            "(2,9,2)," +
            "(2,10,3)," +
            "(2,11,4)," +
            "(2,12,5)," +
            "(2,13,3)," +
            "(2,14,3)," +
            "(2,15,3)," +
            "(3,1,4)," +
            "(3,2,4)," +
            "(3,3,5)," +
            "(3,4,5)," +
            "(3,5,5)," +
            "(3,6,5)," +
            "(3,7,5)," +
            "(3,8,5)," +
            "(3,9,5)," +
            "(3,10,5)," +
            "(3,11,5)," +
            "(3,12,5)," +
            "(3,13,5)," +
            "(3,14,5)," +
            "(3,15,5)," +
            "(4,1,4)," +
            "(4,2,4)," +
            "(4,3,4)," +
            "(4,4,4)," +
            "(4,5,4)," +
            "(4,6,4)," +
            "(4,7,4)," +
            "(4,8,4)," +
            "(4,9,4)," +
            "(4,10,4)," +
            "(4,11,4)," +
            "(4,12,4)," +
            "(4,13,4)," +
            "(4,14,4)," +
            "(4,15,4)," +
            "(5,1,2)," +
            "(5,2,2)," +
            "(5,3,2)," +
            "(5,4,2)," +
            "(5,5,2)," +
            "(5,6,2)," +
            "(5,7,2)," +
            "(5,8,2)," +
            "(5,9,2)," +
            "(5,10,2)," +
            "(5,11,2)," +
            "(5,12,2)," +
            "(5,13,2)," +
            "(5,14,2)," +
            "(5,15,2)," +
            "(6,1,4)," +
            "(6,2,4)," +
            "(6,3,4)," +
            "(6,4,4)," +
            "(6,5,4)," +
            "(6,6,4)," +
            "(6,7,4)," +
            "(6,8,4)," +
            "(6,9,4)," +
            "(6,10,4)," +
            "(6,11,4)," +
            "(6,12,4)," +
            "(6,13,4)," +
            "(6,14,4)," +
            "(6,15,4)," +
            "(7,1,0)," +
            "(7,2,0)," +
            "(7,3,3)," +
            "(7,4,4)," +
            "(7,5,5)," +
            "(7,6,0)," +
            "(7,7,1)," +
            "(7,8,2)," +
            "(7,9,2)," +
            "(7,10,3)," +
            "(7,11,4)," +
            "(7,12,5)," +
            "(7,13,3)," +
            "(7,14,3)," +
            "(7,15,3)," +
            "(8,1,4)," +
            "(8,2,4)," +
            "(8,3,5)," +
            "(8,4,5)," +
            "(8,5,5)," +
            "(8,6,5)," +
            "(8,7,5)," +
            "(8,8,5)," +
            "(8,9,5)," +
            "(8,10,5)," +
            "(8,11,5)," +
            "(8,12,5)," +
            "(8,13,5)," +
            "(8,14,5)," +
            "(8,15,5)," +
            "(9,1,4)," +
            "(9,2,4)," +
            "(9,3,4)," +
            "(9,4,4)," +
            "(9,5,4)," +
            "(9,6,4)," +
            "(9,7,4)," +
            "(9,8,4)," +
            "(9,9,4)," +
            "(9,10,4)," +
            "(9,11,4)," +
            "(9,12,4)," +
            "(9,13,4)," +
            "(9,14,4)," +
            "(9,15,4)," +
            "(10,1,2)," +
            "(10,2,2)," +
            "(10,3,2)," +
            "(10,4,2)," +
            "(10,5,2)," +
            "(10,6,2)," +
            "(10,7,2)," +
            "(10,8,2)," +
            "(10,9,2)," +
            "(10,10,2)," +
            "(10,11,2)," +
            "(10,12,2)," +
            "(10,13,2)," +
            "(10,14,2)," +
            "(10,15,2)," +
            "(11,1,4)," +
            "(11,2,4)," +
            "(11,2,4)," +
            "(11,3,4)," +
            "(11,4,4)," +
            "(11,5,4)," +
            "(11,6,4)," +
            "(11,7,4)," +
            "(11,8,4)," +
            "(11,9,4)," +
            "(11,10,4)," +
            "(11,11,4)," +
            "(11,12,4)," +
            "(11,13,4)," +
            "(11,14,4)," +
            "(11,15,4)," +
            "(12,1,0)," +
            "(12,2,0)," +
            "(12,3,3)," +
            "(12,4,4)," +
            "(12,5,5)," +
            "(12,6,0)," +
            "(12,7,1)," +
            "(12,8,2)," +
            "(12,9,2)," +
            "(12,10,3)," +
            "(12,11,4)," +
            "(12,12,5)," +
            "(12,13,3)," +
            "(12,14,3)," +
            "(12,15,3)," +
            "(13,1,4)," +
            "(13,2,4)," +
            "(13,3,5)," +
            "(13,4,5)," +
            "(13,5,5)," +
            "(13,6,5)," +
            "(13,7,5)," +
            "(13,8,5)," +
            "(13,9,5)," +
            "(13,10,5)," +
            "(13,11,5)," +
            "(13,12,5)," +
            "(13,13,5)," +
            "(13,14,5)," +
            "(13,15,5)," +
            "(14,1,4)," +
            "(14,2,4)," +
            "(14,3,4)," +
            "(14,4,4)," +
            "(14,5,4)," +
            "(14,6,4)," +
            "(14,7,4)," +
            "(14,8,4)," +
            "(14,9,4)," +
            "(14,10,4)," +
            "(14,11,4)," +
            "(14,12,4)," +
            "(14,13,4)," +
            "(14,14,4)," +
            "(14,15,4)," +
            "(15,1,2)," +
            "(15,2,2)," +
            "(15,3,2)," +
            "(15,4,2)," +
            "(15,5,2)," +
            "(15,6,2)," +
            "(15,7,2)," +
            "(15,8,2)," +
            "(15,9,2)," +
            "(15,10,2)," +
            "(15,11,2)," +
            "(15,12,2)," +
            "(15,13,2)," +
            "(15,14,2)," +
            "(15,15,2)," +
            "(16,1,4)," +
            "(16,2,4)," +
            "(16,3,4)," +
            "(16,4,4)," +
            "(16,5,4)," +
            "(16,6,4)," +
            "(16,7,4)," +
            "(16,8,4)," +
            "(16,9,4)," +
            "(16,10,4)," +
            "(16,11,4)," +
            "(16,12,4)," +
            "(16,13,4)," +
            "(16,14,4)," +
            "(16,15,4)," +
            "(17,1,0)," +
            "(17,2,0)," +
            "(17,3,3)," +
            "(17,4,4)," +
            "(17,5,5)," +
            "(17,6,0)," +
            "(17,7,1)," +
            "(17,8,2)," +
            "(17,9,2)," +
            "(17,10,3)," +
            "(17,11,4)," +
            "(17,12,5)," +
            "(17,13,3)," +
            "(17,14,3)," +
            "(17,15,3)," +
            "(18,1,4)," +
            "(18,2,4)," +
            "(18,3,5)," +
            "(18,4,5)," +
            "(18,5,5)," +
            "(18,6,5)," +
            "(18,7,5)," +
            "(18,8,5)," +
            "(18,9,5)," +
            "(18,10,5)," +
            "(18,11,5)," +
            "(18,12,5)," +
            "(18,13,5)," +
            "(18,14,5)," +
            "(18,15,5)," +
            "(19,1,4)," +
            "(19,2,4)," +
            "(19,3,4)," +
            "(19,4,4)," +
            "(19,5,4)," +
            "(19,6,4)," +
            "(19,7,4)," +
            "(19,8,4)," +
            "(19,9,4)," +
            "(19,10,4)," +
            "(19,11,4)," +
            "(19,12,4)," +
            "(19,13,4)," +
            "(19,14,4)," +
            "(19,15,4)," +
            "(20,1,2)," +
            "(20,2,2)," +
            "(20,3,2)," +
            "(20,4,2)," +
            "(20,5,2)," +
            "(20,6,2)," +
            "(20,7,2)," +
            "(20,8,2)," +
            "(20,9,2)," +
            "(20,10,2)," +
            "(20,11,2)," +
            "(20,12,2)," +
            "(20,13,2)," +
            "(20,14,2)," +
            "(20,15,2);" ;

}
